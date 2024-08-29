package com.mh.core.mhsapi.controller.api.category_new;

import com.mh.core.mhsapi.service.api.category_new.CategoryNewService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/category-new")
public class CategoryNewController {

    @Autowired
    private CategoryNewService categoryNewService;

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CategoryNewResponse>>> search(@RequestBody SearchRequest searchRequest){
        return DfResponse
                .okEntity(categoryNewService.search(searchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CategoryNewResponse>> detail(@PathVariable("id") Integer id){
        return DfResponse
                .okEntity(categoryNewService.findById(id));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<CategoryNewResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(categoryNewService.select(searchRequest));
    }

}
