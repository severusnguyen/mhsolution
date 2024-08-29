package com.mh.core.mhscommons.data.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public abstract class BaseMap<Rq, Rp, Po> {

    @Named("toPOJO")
    public abstract Po toPOJO(Rq request);

    @IterableMapping(qualifiedByName = "toPOJO")
    public abstract List<Po> toPOJOs(List<Rq> requests);

    @Named("toResponse")
    public abstract Rp toResponse(Po pojo);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<Rp> toResponses(List<Po> pojos);

    protected Long map(LocalDateTime localDateTime) {
        return localDateTime == null ? null :
                localDateTime.atZone(ZoneId.systemDefault()).getLong(INSTANT_SECONDS) * 1000;
    }

    protected LocalDateTime map(Long time) {
        return time == null ? null :
                Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
