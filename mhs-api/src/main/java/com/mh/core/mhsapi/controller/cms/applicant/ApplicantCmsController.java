package com.mh.core.mhsapi.controller.cms.applicant;

import com.mh.core.mhsapi.service.cms.applicant.ApplicantCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/applicant")
public class ApplicantCmsController {

    @Autowired
    ApplicantCmsServiceImp serviceImp;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<ApplicantResponse>> detail(
            @PathVariable int id, Authentication authentication){
        return DfResponse
                .okEntity(serviceImp.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<ApplicantResponse>> insert(
            @RequestBody ApplicantRequest request, Authentication authentication){
        return DfResponse
                .okEntity(serviceImp.insert(request, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<ApplicantResponse>> update(
            @PathVariable int id, @RequestBody ApplicantRequest request, Authentication authentication){
        return DfResponse
                .okEntity(serviceImp.update(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DfResponse<MessageResponse>> delete(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(serviceImp.deleteById(id, authentication));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ApplicantResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(serviceImp.search(searchRequest));
    }
}
