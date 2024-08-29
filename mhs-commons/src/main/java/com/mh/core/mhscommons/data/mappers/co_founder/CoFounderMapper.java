package com.mh.core.mhscommons.data.mappers.co_founder;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.Social;
import com.mh.core.mhscommons.data.request.co_founder.CoFounderRequest;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;
import com.mh.core.mhscommons.data.tables.pojos.CoFounder;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.config.JsonMapper.*;
import static com.mh.core.mhscommons.utils.SeoUtils.*;

@Mapper(componentModel = "spring")
public abstract class CoFounderMapper extends BaseMap<CoFounderRequest, CoFounderResponse, CoFounder> {
    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "social", ignore = true),
    })
    public abstract CoFounder toPOJO(CoFounderRequest request);

    @IterableMapping(qualifiedByName = "toPOJO")
    public abstract List<CoFounder> toPOJOs(List<CoFounderRequest> requests);

    @Override
    @Mappings({
            @Mapping(target = "socials", ignore = true),
    })
    @Named("toResponse")
    public abstract CoFounderResponse toResponse(CoFounder pojo);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<CoFounderResponse> toResponses(List<CoFounder> pojos);

    @SneakyThrows
    @AfterMapping
    protected void afterToPojo(@MappingTarget CoFounder coFounder, CoFounderRequest request) {
        List<Social> socials = request.getSocials();
        if (!socials.isEmpty()) {
            final String info = getObjectMapper().writeValueAsString(socials);
            coFounder.setSocial(JSON.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    protected void afterToResponse(@MappingTarget CoFounderResponse response, CoFounder coFounder) {
        if (coFounder.getSocial() != null) {
            CollectionType collectionType = getObjectMapper().getTypeFactory()
                    .constructCollectionType(List.class, Social.class);
            List<Social> socialResponses = getObjectMapper()
                    .readValue(coFounder.getSocial().data(), collectionType);
            response.setSocials(socialResponses);
        } else {
            response.setSocials(new ArrayList<>());
        }
    }

    @AfterMapping
    protected void afterResponse(CoFounder po, @MappingTarget CoFounderResponse rp) {
        String name = rp.getName();
        String id = String.valueOf(rp.getId());
        rp.setSeoId(generateSeoId(name, id));
    }
}
