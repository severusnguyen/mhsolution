package com.mh.core.mhsapi.controller.api.job_recruitment;

import com.mh.core.mhsapi.service.api.job_recruitment.IJobRecruitmentService;
import com.mh.core.mhscommons.data.model.DfResponse;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/job-recruitment")
public class JobRecruitmentController {

    private IJobRecruitmentService service;

    @Autowired
    public JobRecruitmentController(IJobRecruitmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DfResponse<JobRecruitmentResponse>> detail(
            @PathVariable("id") int id
    ) {
        return DfResponse
                .okEntity(service.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<DfResponse<Page<JobRecruitmentResponse>>> search(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.search(searchRequest));
    }

    @PostMapping("/select")
    public ResponseEntity<DfResponse<Page<JobRecruitmentResponse>>> select(
            @RequestBody SearchRequest searchRequest
    ) {
        return DfResponse
                .okEntity(service.select(searchRequest));
    }

}
