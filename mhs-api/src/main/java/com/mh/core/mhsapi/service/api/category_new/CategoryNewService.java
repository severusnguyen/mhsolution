package com.mh.core.mhsapi.service.api.category_new;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.core.exception.DBException;
import com.mh.core.mhscommons.data.mappers.category_new.CategoryNewMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.category_new.CategoryNewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhsrepository.repository.category_new.CategoryNewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryNewService extends AbsService<CategoryNewRequest, CategoryNewResponse,
        CategoryNew, Integer, CategoryNewRepository, CategoryNewMapper> implements ICategoryNewService {

    public CategoryNewService(CategoryNewRepository repository, CategoryNewMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Page<CategoryNewResponse> select(SearchRequest searchRequest) {
        try {
            List<CategoryNew> categoryNews = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<CategoryNewResponse> categoryNewResponses = mapper.toResponses(categoryNews);
            return new Page<>(total, searchRequest, categoryNewResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }

    @Override
    public Map<Integer, CategoryNew> getMap(List<Integer> categoryNewIds) {
        List<CategoryNew> categoryNews = repository.getCategoryNewByIds(categoryNewIds);
        return categoryNews.stream()
                .collect(Collectors.toMap(CategoryNew::getId, Function.identity()));
    }

    @Override
    public Map<Integer, CategoryNewResponse> getMapCategoryNewResponseByIds(List<Integer> categoryNewIds) {
        List<CategoryNew> categoryNews = repository.getCategoryNewByIds(categoryNewIds);
        List<CategoryNewResponse> categoryNewResponses = mapper.toResponses(categoryNews);
        return categoryNewResponses.stream()
                .collect(Collectors.toMap(CategoryNewResponse::getId, Function.identity()));
    }

    @Override
    public CategoryNewResponse getCategoryNewResponseById(Integer id) {
        Optional<CategoryNew> categoryNew = repository.findById(id);
        if (categoryNew.isEmpty()) {
            throw new DBException("CategoryNew not found");
        }
        CategoryNewResponse categoryNewResponse = mapper.toResponse(categoryNew.get());
        return categoryNewResponse;
    }
}
