package com.mh.core.mhsrepository.repository;

import com.mh.core.mhscommons.core.exception.DBException;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhsrepository.repository.utils.PostgresUtil;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.impl.TableRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.partition;
import static com.mh.core.mhsrepository.repository.utils.PostgresUtil.buildFilterQueries;
import static com.mh.core.mhsrepository.repository.utils.PostgresUtil.toInsertQueries;
import static com.mh.core.mhsrepository.repository.utils.PostgresUtil.toSortField;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.ID_MUST_NOT_BE_NULL;

@Log4j2
public abstract class AbsRepository<R extends TableRecordImpl<R>, P, ID> implements IBaseRepository<P, ID> {

    @Autowired
    protected DSLContext dslContext;
    protected R record;
    private Class<P> pojoClass;
    protected TableField<R, ID> fieldID;

    protected abstract TableImpl<R> getTable();

    @SneakyThrows
    @PostConstruct
    public void init() {
        log.info("init class {}", this.getClass().getSimpleName());
        this.record = ((Class<R>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0])
                .getDeclaredConstructor()
                .newInstance();
        this.pojoClass = ((Class<P>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        this.fieldID = (TableField<R, ID>) Arrays.stream(getTable().fields())
                .filter(field -> field.getName().equalsIgnoreCase("id"))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Optional<P> insertReturning(P pojo) {
        return Optional.ofNullable(dslContext.insertInto(getTable())
                .set(toInsertQueries(record, pojo))
                .returning()
                .fetchOneInto(pojoClass));
    }


    @Override
    public Integer update(P pojo, ID id) {
        if (fieldID != null){
            return dslContext.update(getTable())
                    .set(toInsertQueries(record, pojo))
                    .where(fieldID.eq(id))
                    .execute();
        }else{
            throw new DBException(ID_MUST_NOT_BE_NULL);
        }
    }

    @Override
    public Integer delete(ID id) {
        if (fieldID != null){
            return dslContext.update(getTable())
                    .set(DSL.field("deleted_at"), LocalDateTime.now())
                    .where(fieldID.eq(id))
                    .execute();
        }else{
            throw new DBException(ID_MUST_NOT_BE_NULL);
        }

    }

    @Override
    public Optional<P> findById(ID id) {
        if (fieldID == null){
            return null;
        }
        return Optional.ofNullable(dslContext.selectFrom(getTable())
                .where(fieldID.eq(id))
                .fetchOneInto(pojoClass));
    }

    @Override
    public List<Integer> insert(Collection<P> pojos) {
        final List<InsertSetMoreStep<R>> insertSetMoreSteps = pojos.stream()
                .map(p -> PostgresUtil.toInsertQueries(record, p))
                .map(fieldObjectMap -> dslContext
                        .insertInto(getTable())
                        .set(fieldObjectMap))
                .collect(Collectors.toList());

        return partition(insertSetMoreSteps, 100)
                .stream()
                .flatMap(lists -> Arrays.stream(dslContext.batch(lists).execute()).boxed())
                .collect(Collectors.toList());
    }

    @Override
    public List<P> getAll() {
        return dslContext.select()
                .from(getTable())
                .fetchInto(pojoClass);
    }

    @Override
    public List<P> search(SearchRequest searchRequest) {
        return dslContext.selectFrom(getTable())
                .where(buildFilterQueries(getTable(), searchRequest.getFilters())
                        .and(filterActive())
                        .and(buildSearchQueries(getTable(), searchRequest.getKeyword())))
                .orderBy(toSortField(searchRequest.getSorts(), getTable().fields()))
                .offset(searchRequest.getOffset())
                .limit(searchRequest.getPageSize())
                .fetchInto(pojoClass);
    }

    protected Condition filterActive() {
        // check co truong deleted_at ?
        Field<?> deletedAt = getTable().field("deleted_at");
        if (deletedAt != null) {
            return deletedAt.isNull();
        }
        return DSL.trueCondition();
    }

    public static <R extends Record> Condition buildSearchQueries(TableImpl<R> table, String keyword) {
        if (isEmpty(keyword)) return DSL.noCondition();
        final Condition[] condition = {DSL.noCondition()};
        Arrays.stream(table.fields())
                .filter(field -> String.class.isAssignableFrom(field.getType()) || Long.class.isAssignableFrom(field.getType()))
                .forEach(field -> condition[0] = condition[0].or(field.cast(String.class).likeRegex(keyword)));
        return condition[0];
    }

    public Long count(SearchRequest searchRequest) {
        return dslContext
                .selectCount()
                .from(getTable())
                .where(buildFilterQueries(getTable(), searchRequest.getFilters())
                        .and(filterActive())
                        .and(buildSearchQueries(getTable(), searchRequest.getKeyword())))
                .fetchOneInto(Long.class);
    }
}
