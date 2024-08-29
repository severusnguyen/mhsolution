package com.mh.core.mhsapi.service.cms.home_page;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.home_page.HomePageMapper;
import com.mh.core.mhscommons.data.request.home_page.HomePageRequest;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;
import com.mh.core.mhscommons.data.tables.pojos.HomePage;
import com.mh.core.mhsrepository.repository.home_page.HomePageRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class HomePageCmsServiceImp extends AbsCmsService<HomePageRequest, HomePageResponse, HomePage, Integer,
        HomePageRepositoryImp, HomePageMapper> {

    @Override
    protected String getPermissionCode() {
        return "home_page";
    }

    public HomePageCmsServiceImp(HomePageRepositoryImp repositoryImp, HomePageMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
