package com.mh.core.mhscommons.data.mappers.news;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.mappers.category_new.CategoryNewMapper;
import com.mh.core.mhscommons.data.model.Seo;
import com.mh.core.mhscommons.data.request.news.NewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.response.news.NewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhscommons.data.tables.pojos.New;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mh.core.mhscommons.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public abstract class NewMapper extends BaseMap<NewRequest, NewResponse, New> {

    @Autowired
    private CategoryNewMapper categoryNewMapper;

    public abstract NewResponse toResponse(New po, @Context CategoryNewResponse categoryNewResponse);

    public List<NewResponse> toResponses(List<New> news, Map<Integer, CategoryNewResponse> categoryNewMap) {
        return news.stream()
                .map(n -> {
                    NewResponse response = toResponse(n, categoryNewMap.get(n.getCategoryNewId()));
                    return response;
                }).collect(Collectors.toList());
    }

    @AfterMapping
    protected void afterResponse(@MappingTarget NewResponse newResponse, New po, @Context CategoryNewResponse categoryNewResponse) {
        newResponse.setSeoId(generateSeoId(newResponse.getTitle(), String.valueOf(newResponse.getId())));
        newResponse.setCategory(categoryNewResponse);
        Seo seo = new Seo()
                .setKeywords(po.getKeywords())
                .setDescription(po.getDescription());
        newResponse.setSeo(seo);
    }

    public void addCategory(NewResponse rp, CategoryNew categoryNew) {
        rp.setCategory(categoryNewMapper.toResponse(categoryNew));
    }
}
