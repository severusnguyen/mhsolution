package com.mh.core.mhsapi.service.cms.category_new;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.category_new.CategoryNewMapper;
import com.mh.core.mhscommons.data.request.category_new.CategoryNewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;
import com.mh.core.mhsrepository.repository.category_new.CategoryNewRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryNewCmsServiceImp extends AbsCmsService<CategoryNewRequest, CategoryNewResponse, CategoryNew, Integer, CategoryNewRepository, CategoryNewMapper> {

    @Override
    protected String getPermissionCode() {
        return "category_new";
    }

    public CategoryNewCmsServiceImp(CategoryNewRepository repository, CategoryNewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
