package com.mh.core.mhsapi.controller.cms.category_new;

import com.mh.core.mhsapi.service.cms.category_new.CategoryNewCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.category_new.CategoryNewRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/category-new")
public class CategoryNewCmsController {

    @Autowired
    private CategoryNewCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CategoryNewResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable Integer id, Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<CategoryNewResponse>> update(
            @PathVariable Integer id,
            @RequestBody CategoryNewRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }
    @PostMapping("")
    public ResponseEntity<DfResponse<CategoryNewResponse>> insert(
            @RequestBody CategoryNewRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }
    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CategoryNewResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
