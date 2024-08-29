package com.mh.core.mhsapi.service.api.news;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhsapi.service.api.category_new.CategoryNewService;
import com.mh.core.mhscommons.data.mappers.news.NewMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.news.NewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.response.news.NewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhscommons.data.tables.pojos.New;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.category_new.CategoryNewRepository;
import com.mh.core.mhsrepository.repository.news.NewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mh.core.mhscommons.data.constant.message.news.NewMessageConstant.*;
import static com.mh.core.mhscommons.utils.CollectionUtils.extractField;

@Service
@Slf4j
public class NewService extends AbsService<NewRequest, NewResponse,
        New, Integer, NewRepository, NewMapper> implements INewService {


    private CategoryNewService categoryNewService;

    private CategoryNewRepository categoryNewRepository;

    @Autowired
    public NewService(NewRepository repository, NewMapper mapper, CategoryNewService categoryNewService, CategoryNewRepository categoryNewRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.categoryNewService = categoryNewService;
        this.categoryNewRepository = categoryNewRepository;
    }


    @Override
    public Page<NewResponse> searchNew(SearchRequest searchRequest) {
        try {
            List<New> newList = repository.search(searchRequest);
            Long total = repository.count(searchRequest);

            List<Integer> categoryIds = extractField(newList, New::getCategoryNewId);
            Map<Integer, CategoryNew> categoryNewMap = categoryNewService.getMap(categoryIds);
            Map<Integer, CategoryNewResponse> categoryNewResponseMap =
                    categoryNewService.getMapCategoryNewResponseByIds(categoryIds);
            List<NewResponse> newResponses = mapper.toResponses(newList, categoryNewResponseMap);
            return new Page<NewResponse>()
                    .setTotal(total)
                    .setItems(newResponses);
        } catch (Exception exception) {
            log.error("[ERROR] searchNew {} " + exception.getMessage());
            return new Page<NewResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }

    }

    @Override
    public NewResponse findById(Integer integer) {
        try {
            New newPojo = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(NEWS_NOT_FOUND, 404));
            Integer categoryNewId = newPojo.getCategoryNewId();
            CategoryNewResponse categoryNew = categoryNewService.getCategoryNewResponseById(categoryNewId);
            NewResponse newResponse = mapper.toResponse(newPojo, categoryNew);
            return newResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<NewResponse> select(SearchRequest searchRequest) {
        try {
            List<New> news = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<Integer> categoryIds = extractField(news, New::getCategoryNewId);
            Map<Integer, CategoryNewResponse> categoryNewMap = categoryNewService.getMapCategoryNewResponseByIds(categoryIds);
            List<NewResponse> newResponses = mapper.toResponses(news, categoryNewMap);
            return new Page<>(total, searchRequest, newResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }

}
