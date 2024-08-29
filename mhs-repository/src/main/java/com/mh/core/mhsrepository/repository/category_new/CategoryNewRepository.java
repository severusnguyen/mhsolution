package com.mh.core.mhsrepository.repository.category_new;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhscommons.data.tables.records.CategoryNewRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.mh.core.mhscommons.data.tables.CategoryNew.CATEGORY_NEW;

@Repository
public class CategoryNewRepository extends AbsRepository<CategoryNewRecord, CategoryNew, Integer>
        implements ICategoryNewRepository{

    @Override
    protected TableImpl<CategoryNewRecord> getTable() {
        return CATEGORY_NEW;
    }


    @Override
    public List<CategoryNew> getCategoryNewByIds(Collection<Integer> ids) {
        return dslContext.selectFrom(CATEGORY_NEW)
                .where(CATEGORY_NEW.ID.in(ids))
                .fetchInto(CategoryNew.class);
    }

    @Override
    public Optional<CategoryNew> findById(Integer integer) {
        return dslContext.selectFrom(Tables.CATEGORY_NEW)
                .where(Tables.CATEGORY_NEW.ID.eq(integer))
                .fetchOptionalInto(CategoryNew.class);
    }
}
