package com.mh.core.mhscommons.data.mappers.introduction;


import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.Competitive;
import com.mh.core.mhscommons.data.model.CoreValue;
import com.mh.core.mhscommons.data.model.Mission;
import com.mh.core.mhscommons.data.model.Vision;
import com.mh.core.mhscommons.data.request.introduction.IntroductionRequest;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;
import com.mh.core.mhscommons.data.tables.pojos.Introduction;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.config.JsonMapper.*;
import static com.mh.core.mhscommons.utils.CollectionUtils.*;

@Mapper(componentModel = "spring")
public abstract class IntroductionMapper extends BaseMap<IntroductionRequest, IntroductionResponse, Introduction> {

    @Override
    @Mappings({
            @Mapping(target = "vision", ignore = true),
            @Mapping(target = "mission", ignore = true),
            @Mapping(target = "coreValue", ignore = true),
            @Mapping(target = "competitive", ignore = true),
    })
    public abstract Introduction toPOJO(IntroductionRequest request);

    @Override
    public abstract List<Introduction> toPOJOs(List<IntroductionRequest> requests);

    @Override
    @Mappings({
            @Mapping(target = "visions", ignore = true),
            @Mapping(target = "missions", ignore = true),
            @Mapping(target = "coreValues", ignore = true),
            @Mapping(target = "competitives", ignore = true),
    })
    @Named("toResponse")
    public abstract IntroductionResponse toResponse(Introduction pojo);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<IntroductionResponse> toResponses(List<Introduction> pojos);

    @SneakyThrows
    @AfterMapping
    protected void afterToPojo(@MappingTarget Introduction introduction, IntroductionRequest request) {
        if (request.getVisions() != null) {
            final String vision = getObjectMapper().writeValueAsString(request.getVisions());
            introduction.setVision(JSON.valueOf(vision));
        }
        if (request.getMissions() != null) {
            final String mission = getObjectMapper().writeValueAsString(request.getMissions());
            introduction.setMission(JSON.valueOf(mission));
        }
        if (request.getCoreValues() != null) {
            final String coreValue = getObjectMapper().writeValueAsString(request.getCoreValues());
            introduction.setCoreValue(JSON.valueOf(coreValue));
        }
        if (request.getCompetitives() != null) {
            final String competitive = getObjectMapper().writeValueAsString(request.getCompetitives());
            introduction.setCompetitive(JSON.valueOf(competitive));
        }
    }

    @SneakyThrows
    @AfterMapping
    protected void afterToResponse(@MappingTarget IntroductionResponse response, Introduction introduction) {

        response.setVisions(getOrDefaultList(Vision.class, introduction.getVision(), new ArrayList<>()));
        response.setMissions(getOrDefaultList(Mission.class, introduction.getMission(), new ArrayList<>()));
        response.setCoreValues(getOrDefaultList(CoreValue.class, introduction.getCoreValue(), new ArrayList<>()));
        response.setCompetitives(getOrDefaultList(Competitive.class, introduction.getCompetitive(), new ArrayList<>()));

    }

}
