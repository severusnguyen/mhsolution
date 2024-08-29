package com.mh.core.mhscommons.data.mappers.product;


import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.model.Info;
import com.mh.core.mhscommons.data.request.product.ProductRequest;
import com.mh.core.mhscommons.data.response.product.ProductResponse;
import com.mh.core.mhscommons.data.tables.pojos.Product;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.config.JsonMapper.*;
import static com.mh.core.mhscommons.utils.SeoUtils.generateSeoId;
import static com.mh.core.mhscommons.utils.CollectionUtils.getOrDefaultList;


@Mapper(componentModel = "spring")
public abstract class ProductMapper extends BaseMap<ProductRequest, ProductResponse, Product> {
    @Override
    @Mappings({
            @Mapping(target = "info", ignore = true),
            @Mapping(target = "content", ignore = true),
    })
    public abstract Product toPOJO(ProductRequest request);

    @Override
    public abstract List<Product> toPOJOs(List<ProductRequest> requests);

    @Override
    @Mappings({
            @Mapping(target = "infos", ignore = true),
            @Mapping(target = "contents", ignore = true),
    })
    @Named("toResponse")
    public abstract ProductResponse toResponse(Product pojo);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<ProductResponse> toResponses(List<Product> pojos);

    @SneakyThrows
    @AfterMapping
    protected void afterToPojo(@MappingTarget Product product, ProductRequest request) {
        if (request.getInfos() != null) {
            final String info = getObjectMapper().writeValueAsString(request.getInfos());
            product.setInfo(JSON.valueOf(info));
        }
        if (request.getContents() != null) {
            final String info = getObjectMapper().writeValueAsString(request.getContents());
            product.setContent(JSON.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    protected void afterToResponse(@MappingTarget ProductResponse response, Product product) {
        response.setInfos(getOrDefaultList(Info.class, product.getInfo(), new ArrayList<>()));
        response.setContents(getOrDefaultList(Info.class, product.getContent(), new ArrayList<>()));
    }

    @AfterMapping
    protected void afterResponse(Product po, @MappingTarget ProductResponse response) {
        final String title = response.getTitle();
        final String id = String.valueOf(response.getId());
        response.setSeoId(generateSeoId(title, id));
    }
}
