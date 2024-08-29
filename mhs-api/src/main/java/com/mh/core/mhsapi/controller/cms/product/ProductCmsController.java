package com.mh.core.mhsapi.controller.cms.product;

import com.mh.core.mhsapi.service.cms.product.ProductCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.product.ProductRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/product")
public class ProductCmsController {

    @Autowired
    private ProductCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<ProductResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("/new")
    public ResponseEntity<DfResponse<ProductResponse>> insert(
            @RequestBody ProductRequest productRequest,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(productRequest,authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ProductResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody ProductRequest rq,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, rq, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }


    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ProductResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
