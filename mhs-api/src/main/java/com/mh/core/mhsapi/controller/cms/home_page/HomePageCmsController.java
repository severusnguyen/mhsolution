package com.mh.core.mhsapi.controller.cms.home_page;

import com.mh.core.mhsapi.service.cms.home_page.HomePageCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.home_page.HomePageRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/home-page")
public class HomePageCmsController {

    @Autowired
    private HomePageCmsServiceImp service;

    @PostMapping("")
    public ResponseEntity<DfResponse<HomePageResponse>> insert(
            @RequestBody HomePageRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(request, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<HomePageResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }


    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<HomePageResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody HomePageRequest request,
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
    public ResponseEntity<DfResponse<Page<HomePageResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
