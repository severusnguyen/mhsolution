package com.mh.core.mhscommons.data.mappers.job_recruitment;

import com.mh.core.mhscommons.core.json.JsonArray;
import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.Info;
import com.mh.core.mhscommons.data.request.job_recruitment.JobRecruitmentRequest;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import com.mh.core.mhscommons.data.tables.pojos.JobRecruitment;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.config.JsonMapper.*;
import static java.time.temporal.ChronoField.INSTANT_SECONDS;
import static com.mh.core.mhscommons.utils.SeoUtils.*;

@Mapper(componentModel = "spring")
public abstract class JobRecruitmentMapper extends BaseMap<JobRecruitmentRequest, JobRecruitmentResponse, JobRecruitment> {
    @Named("toPOJO")
    @Mapping(
            target = "info", ignore = true
    )
    public abstract JobRecruitment toPOJO(JobRecruitmentRequest request);

    @Named("toResponse")
    @Mapping(
            target = "infos", ignore = true
    )
    public abstract JobRecruitmentResponse toResponse(JobRecruitment pojo);

    @SneakyThrows
    @AfterMapping
    protected void afterToPojo(@MappingTarget JobRecruitment jobRecruitment, JobRecruitmentRequest request) {
        if (request.getInfos() != null) {
            final String info = getObjectMapper().writeValueAsString(request.getInfos());
            jobRecruitment.setInfo(JSON.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    protected void afterToResponse(@MappingTarget JobRecruitmentResponse response, JobRecruitment jobRecruitment) {
        if (jobRecruitment.getInfo() != null) {
            List<Info> infoResponses = new JsonArray(jobRecruitment.getInfo().data()).mapTo(Info.class);
            response.setInfos(infoResponses);
        } else {
            response.setInfos(new ArrayList<>());
        }
    }
    @AfterMapping
    protected void afterResponse(JobRecruitment jobRecruitment, @MappingTarget JobRecruitmentResponse response) {
        response.setSeoId(generateSeoId(response.getTitle(), String.valueOf(response.getId())));
    }

    protected Long map(LocalDateTime localDateTime) {
        return localDateTime == null ? null :
                localDateTime.atZone(ZoneId.systemDefault()).getLong(INSTANT_SECONDS) * 1000;
    }

    protected LocalDateTime map(Long time) {
        return time == null ? null :
                Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
