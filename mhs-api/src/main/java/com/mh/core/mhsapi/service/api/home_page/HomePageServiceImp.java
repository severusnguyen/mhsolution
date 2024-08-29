package com.mh.core.mhsapi.service.api.home_page;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.home_page.HomePageMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.home_page.HomePageRequest;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;
import com.mh.core.mhscommons.data.tables.pojos.HomePage;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.home_page.HomePageRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.home_page.HomePageMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;

@Service
@Slf4j
public class HomePageServiceImp extends AbsService<HomePageRequest, HomePageResponse, HomePage, Integer, HomePageRepositoryImp, HomePageMapper>
        implements IHomePageService {

    @Autowired
    public HomePageServiceImp(HomePageRepositoryImp repository, HomePageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HomePageResponse findById(Integer integer) {
        try {
            HomePage homePage = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(HOME_PAGE_NOT_FOUND, 404));
            HomePageResponse homePageResponse = mapper.toResponse(homePage);
            return homePageResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<HomePageResponse> search(SearchRequest searchRequest) {
        try {
            List<HomePage> homePages = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<HomePageResponse> homePageResponses = mapper.toResponses(homePages);
            return new Page<HomePageResponse>()
                    .setTotal(total)
                    .setItems(homePageResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<HomePageResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<HomePageResponse> select(SearchRequest searchRequest) {
        try {
            List<HomePage> homePages = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<HomePageResponse> homePageResponses = mapper.toResponses(homePages);
            return new Page<>(total, searchRequest, homePageResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<HomePageResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }
}
