package com.mh.core.mhsapi.service.cms.product;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.product.ProductMapper;
import com.mh.core.mhscommons.data.request.product.ProductRequest;
import com.mh.core.mhscommons.data.response.product.ProductResponse;
import com.mh.core.mhscommons.data.tables.pojos.Product;
import com.mh.core.mhsrepository.repository.product.ProductRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class ProductCmsServiceImp extends AbsCmsService<ProductRequest, ProductResponse, Product, Integer, ProductRepositoryImp, ProductMapper> {

    @Override
    protected String getPermissionCode() {
        return "product";
    }

    public ProductCmsServiceImp(ProductRepositoryImp repositoryImp, ProductMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
