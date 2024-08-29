package com.mh.core.mhsapi.controller.cms.news;

import com.mh.core.mhsapi.service.cms.news.NewsCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.news.NewRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.news.NewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/news")
public class NewsCmsController {

    @Autowired
    private NewsCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<NewResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<NewResponse>> insert(
            @RequestBody NewRequest newRequest,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(newRequest, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<NewResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody NewRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.update(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }


    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<NewResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
