package com.mh.core.mhsapi.controller.cms.co_founder_cms;

import com.mh.core.mhsapi.service.cms.co_founder_cms.ICoFounderCmsService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.co_founder.CoFounderRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/co-founder")
public class CoFounderCmsController {

    private ICoFounderCmsService service;

    @Autowired
    public CoFounderCmsController(ICoFounderCmsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DfResponse<CoFounderResponse>> insert(
            @RequestBody CoFounderRequest coFounderRequest,
            Authentication authentication
    ) {
        return DfResponse
                .okEntity(service.insert(coFounderRequest, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<CoFounderResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody CoFounderRequest coFounderRequest,
            Authentication authentication
    ) {
        return DfResponse
                .okEntity(service.update(id, coFounderRequest, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CoFounderResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication
    ) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication
    ) {
        return DfResponse
                .okEntity(service.deleteById(id, authentication));
    }

    @GetMapping("/search")
    public ResponseEntity<DfResponse<Page<CoFounderResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

}
