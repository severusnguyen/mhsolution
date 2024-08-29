package com.mh.core.mhsapi.service.api.product;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.product.ProductMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.product.ProductRequest;
import com.mh.core.mhscommons.data.response.product.ProductResponse;
import com.mh.core.mhscommons.data.tables.pojos.Product;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.product.ProductRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.product.ProductMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;


@Service
@Slf4j
public class ProductServiceImp extends AbsService<ProductRequest, ProductResponse, Product, Integer, ProductRepositoryImp, ProductMapper>
        implements IProductService {

    @Autowired
    public ProductServiceImp(ProductRepositoryImp repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse findById(Integer integer) {
        try {
            Product product = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND, 404));
            ProductResponse productResponse = mapper.toResponse(product);
            return productResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<ProductResponse> search(SearchRequest searchRequest) {
        try {
            List<Product> products = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ProductResponse> productResponses = mapper.toResponses(products);
            return new Page<ProductResponse>()
                    .setTotal(total)
                    .setItems(productResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<ProductResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<ProductResponse> select(SearchRequest searchRequest) {
        try {
            List<Product> products = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ProductResponse> productResponses = mapper.toResponses(products);
            return new Page<>(total, searchRequest, productResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }
}
