package com.mh.core.mhsapi.service.cms.news;

import com.mh.core.mhsapi.service.api.category_new.CategoryNewService;
import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.news.NewMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.news.NewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.response.news.NewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhscommons.data.tables.pojos.New;
import com.mh.core.mhscommons.utils.CollectionUtils;
import com.mh.core.mhsrepository.repository.category_new.CategoryNewRepository;
import com.mh.core.mhsrepository.repository.news.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewsCmsServiceImp extends AbsCmsService<NewRequest, NewResponse, New, Integer, NewRepository, NewMapper>
                                implements INewsCmsService{

    @Override
    protected String getPermissionCode() {
        return "new";
    }

    public NewsCmsServiceImp(CategoryNewRepository categoryNewRepository, NewRepository repository, NewMapper mapper) {
        this.categoryNewRepository = categoryNewRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Autowired
    private final CategoryNewRepository categoryNewRepository;

    @Autowired
    private CategoryNewService categoryNewService;

    @Override
    public NewResponse addExtraInfo(NewResponse newResponse) {
        categoryNewRepository.findById(newResponse.getCategoryNewId()).ifPresent(categoryNew -> {
            mapper.addCategory(newResponse, categoryNew);
        });
        return newResponse;
    }

    @Override
    public Page<NewResponse> search(SearchRequest searchRequest) {
        List<New> news = repository.search(searchRequest);
        Long totalCount = repository.count(searchRequest);

        List<Integer> categoryNewIds = CollectionUtils.extractField(news, New::getCategoryNewId);
        Map<Integer, CategoryNew> categoryNewMap = categoryNewService.getMap(categoryNewIds);
        Map<Integer, CategoryNewResponse> categoryNewResponseMap =
                categoryNewService.getMapCategoryNewResponseByIds(categoryNewIds);
//        List<CategoryNew> categoryNews = categoryNewRepository.getCategoryNewByIds(categoryNewIds);
//        categoryNewMap = categoryNews.stream()
//                                        .collect(Collectors.toMap(CategoryNew::getId, Function.identity()));
        List<NewResponse> newResponses = mapper.toResponses(news, categoryNewResponseMap);

        return new Page<NewResponse>()
                .setTotal(totalCount)
                .setItems(newResponses);    }
}
