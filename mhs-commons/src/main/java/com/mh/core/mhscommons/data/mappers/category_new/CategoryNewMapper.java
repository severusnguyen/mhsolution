package com.mh.core.mhscommons.data.mappers.category_new;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.category_new.CategoryNewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static com.mh.core.mhscommons.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public abstract class CategoryNewMapper extends BaseMap<CategoryNewRequest, CategoryNewResponse, CategoryNew> {

    @AfterMapping
    protected void afterResponse(@MappingTarget CategoryNewResponse rp) {
        rp.setSeoId(generateSeoId(rp.getTitle(), String.valueOf(rp.getId())));
    }
}
