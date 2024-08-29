package com.mh.core.mhsapi.controller.api.product;

import com.mh.core.mhsapi.service.api.product.IProductService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/product")
public class ProductController {

    private IProductService service;

    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<ProductResponse>> detail(
            @PathVariable("id") int id
    ) {
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ProductResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<ProductResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
