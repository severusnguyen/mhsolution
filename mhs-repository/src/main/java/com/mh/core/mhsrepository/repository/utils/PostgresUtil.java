package com.mh.core.mhsrepository.repository.utils;

import com.mh.core.mhscommons.core.json.JsonArray;
import com.mh.core.mhscommons.core.json.JsonObject;
import com.mh.core.mhscommons.data.model.Filter;
import com.mh.core.mhscommons.data.model.paging.Order;
import com.mh.core.mhscommons.data.model.query.Operator;
import com.mh.core.mhscommons.utils.TimeUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.impl.TableRecordImpl;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.createLong;
import static org.jooq.impl.DSL.trueCondition;

public class PostgresUtil {

    public static <T extends TableRecordImpl<T>> Map<Field<?>, Object>
    toInsertQueries(T record, Object o) {
        record.from(o);
        return Arrays.stream(record.fields())
                .filter(f -> record.getValue(f) != null)
                .collect(Collectors.toMap(field -> field, record::getValue));
    }

    public static List<SortField<Object>> toSortField(List<Order> orderProperties, Field<?>[] fields) {
        if (CollectionUtils.isEmpty(orderProperties)) return new ArrayList<>();
        Set<String> fieldNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toSet());
        return orderProperties
                .stream()
                .filter(order -> fieldNames.contains(order.getProperty()))
                .map(order -> {
                    if (order.getDirection().equals(Order.Direction.asc.name()))
                        return DSL.field(order.getProperty()).asc();
                    else return DSL.field(order.getProperty()).desc();
                })
                .collect(Collectors.toList());
    }

    public static <R extends Record> Condition buildFilterQueries(TableImpl<R> table, List<Filter> fieldFilters) {
        if (isEmpty(fieldFilters)) return DSL.noCondition();
        final Condition[] condition = {DSL.noCondition()};
        fieldFilters
                .forEach(fieldFilter -> {
                    final Field field = table.field(fieldFilter.getName());
                    if (field != null) {
                        final Object valueByClass = castValueByClass(fieldFilter.getOperation(), fieldFilter.getValue(), field.getType());
                        if (valueByClass != null) {
                            condition[0] = condition[0].and(buildCondition(fieldFilter.getOperation(), field, valueByClass));

                        } else {
                            condition[0] = condition[0].and(field.isNull());
                        }
                    }
                });
        return condition[0];
    }

    public static <V> Object castValueByClass(String operation, Object value, Class<V> classValue) {
        if (operation != null && Arrays.asList(Operator.IN.getOperator(), Operator.NIN.getOperator()).contains(operation)) {
            final JsonArray array = new JsonArray(value.toString());
            return array.stream()
                    .map(object -> castValueByClass(null, object, classValue))
                    .collect(Collectors.toList());
        }
        try {
            if (classValue.getSimpleName().equalsIgnoreCase(LocalDateTime.class.getSimpleName()))
                return TimeUtils.longToLocalDateTime(createLong(value.toString()));
            if (classValue.getSimpleName().equalsIgnoreCase(Timestamp.class.getSimpleName()))
                return new Timestamp(createLong(value.toString()));
            if (String.class.isAssignableFrom(classValue)) return value;
            if (Integer.class.isAssignableFrom(classValue)) return Integer.valueOf(value.toString());
            if (Double.class.isAssignableFrom(classValue)) return Double.valueOf(value.toString());
            return JsonObject.mapFrom(value).mapTo(classValue);
        } catch (Exception e) {
            return null;
        }
    }

    private static Condition buildCondition(String operation, Field<Object> field, Object value) {

        var operator = Operator.from(operation);
        switch (operator) {
            case IN: {
                return field.in(value);
            }
            case NIN: {
                return field.notIn(value);
            }
            case EQUAL: {
                return field.eq(value);
            }
            case LIKE: {
                if (value == null) return trueCondition();
                return field.likeRegex(value.toString());
            }
            case LIKE_IGNORE: {
                if (value == null) return trueCondition();
                return field.likeIgnoreCase("%" + value.toString() + "%");
            }
            case NOT_EQUAL: {
                return field.ne(value);
            }
            case GREATER_THAN: {
                return field.gt(value);
            }
            case LESS_THAN: {
                return field.lt(value);
            }
            case GREATER_THAN_OR_EQUAL: {
                return field.greaterOrEqual(value);
            }
            case LESS_THAN_OR_EQUAL: {
                return field.lessOrEqual(value);
            }
            default:
                return field.eq(value);
        }
    }


}
