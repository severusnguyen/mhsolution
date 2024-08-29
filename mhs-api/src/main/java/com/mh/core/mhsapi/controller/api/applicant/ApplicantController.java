package com.mh.core.mhsapi.controller.api.applicant;

import com.mh.core.mhsapi.service.api.applicant.IApplicantService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/applicant/")
public class ApplicantController {

    private IApplicantService service;

    @Autowired
    public ApplicantController(IApplicantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<ApplicantResponse>> detail(
            @PathVariable("id") int id
    ) {
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<ApplicantResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<ApplicantResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ){
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

    @PostMapping("/insert")
    public ResponseEntity<DfResponse<ApplicantResponse>> insert(
            @RequestBody ApplicantRequest request
    ){
        return DfResponse
                .okEntity(service.insert(request));
    }

}
