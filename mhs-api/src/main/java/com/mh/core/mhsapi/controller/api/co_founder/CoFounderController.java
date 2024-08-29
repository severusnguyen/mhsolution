package com.mh.core.mhsapi.controller.api.co_founder;

import com.mh.core.mhsapi.service.api.co_founder.ICoFounderService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/co-founder")
public class CoFounderController {

    private ICoFounderService service;

    @Autowired
    public CoFounderController(ICoFounderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<CoFounderResponse>> detail(
            @PathVariable("id") int id
    ){
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<CoFounderResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<CoFounderResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
