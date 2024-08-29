package com.mh.core.mhscommons.data.mappers.home_page;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.home_page.HomePageRequest;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;
import com.mh.core.mhscommons.data.tables.pojos.HomePage;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static com.mh.core.mhscommons.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public abstract class HomePageMapper extends BaseMap<HomePageRequest, HomePageResponse, HomePage> {
    @AfterMapping
    protected void afterResponse(HomePage po, @MappingTarget HomePageResponse rp) {
        final String title = rp.getTitle();
        final String id = String.valueOf(rp.getId());
        rp.setSeoId(generateSeoId(title, id));
    }
}
