package com.mh.core.mhsapi.controller.cms.job_recruiment;

import com.mh.core.mhsapi.service.cms.job_recruiment.JobRecruimentCmsServiceImp;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.job_recruitment.JobRecruitmentRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/job-recruitment")
public class JobRecruimentCmsController {

    @Autowired
    private JobRecruimentCmsServiceImp service;

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<JobRecruitmentResponse>> detail(
            @PathVariable("id") Integer id,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.findById(id, authentication));
    }

    @PostMapping("")
    public ResponseEntity<DfResponse<JobRecruitmentResponse>> insert(
            @RequestBody JobRecruitmentRequest jobRecruitmentRequest,
            Authentication authentication) {
        return DfResponse
                .okEntity(service.insert(jobRecruitmentRequest, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DfResponse<JobRecruitmentResponse>> update(
            @PathVariable("id") Integer id,
            @RequestBody JobRecruitmentRequest request,
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
    public ResponseEntity<DfResponse<Page<JobRecruitmentResponse>>> search(
            @RequestBody SearchRequest searchRequest) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }
}
