package com.mh.core.mhsapi.controller.api.introduction;

import com.mh.core.mhsapi.service.api.introduction.IIntroductionService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/introduction")
public class IntroductionController {

    IIntroductionService service;

    @Autowired
    public IntroductionController(IIntroductionService iIntroductionService) {
        this.service = iIntroductionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<IntroductionResponse>> detail(
            @PathVariable("id") Integer id
    ){
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<IntroductionResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<IntroductionResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
