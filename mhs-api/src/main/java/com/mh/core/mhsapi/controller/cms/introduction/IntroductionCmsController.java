package com.mh.core.mhsapi.controller.cms.introduction;

import com.mh.core.mhsapi.service.cms.introduction.IntroductionCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.introduction.IntroductionRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/introduction")
public class IntroductionCmsController {

    @Autowired
    IntroductionCmsServiceImp introductionServiceImp;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<IntroductionResponse>> detainl(
            @PathVariable int id, Authentication authentication) {
        return DfResponse
                .okEntity(introductionServiceImp.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<IntroductionResponse>> insert(
            @RequestBody IntroductionRequest request, Authentication authentication) {
        return DfResponse
                .okEntity(introductionServiceImp.insert(request, authentication));
    }

    @DeleteMapping("/cms/introduction/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(@PathVariable("id") Integer id,
                                                              Authentication authentication) {
        return DfResponse
                .okEntity(introductionServiceImp.deleteById(id, authentication));
    }

    @PutMapping("/cms/introduction/{id}")
    public ResponseEntity<DfResponse<IntroductionResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody IntroductionRequest request,
            Authentication authentication) {
        return DfResponse
                .okEntity(introductionServiceImp.update(id, request, authentication));
    }

    @PostMapping("/cms/introduction/search")
    public ResponseEntity<DfResponse<Page<IntroductionResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(introductionServiceImp.search(searchRequest));
    }
}
