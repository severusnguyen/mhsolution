package com.mh.core.mhsapi.service.api.job_recruitment;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.job_recruitment.JobRecruitmentMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.job_recruitment.JobRecruitmentRequest;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import com.mh.core.mhscommons.data.tables.pojos.JobRecruitment;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.job_recruitment.JobRecruitmentRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.job_recruitment.JobRecruitmentMessageConstant.*;
import static com.mh.core.mhscommons.data.constant.message.MessageResponse.SERVER_ERROR;

@Service
@Slf4j
public class JobRecruitmentServiceImp extends AbsService<JobRecruitmentRequest, JobRecruitmentResponse, JobRecruitment, Integer, JobRecruitmentRepositoryImp, JobRecruitmentMapper>
        implements IJobRecruitmentService {

    @Autowired
    public JobRecruitmentServiceImp(JobRecruitmentRepositoryImp repository, JobRecruitmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JobRecruitmentResponse findById(Integer integer) {
        try {
            JobRecruitment jobRecruitment = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(JOB_NOT_FOUND, 404));
            JobRecruitmentResponse jobRecruitmentResponse = mapper.toResponse(jobRecruitment);
            return jobRecruitmentResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<JobRecruitmentResponse> search(SearchRequest searchRequest) {
        try {
            List<JobRecruitment> jobRecruitments = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<JobRecruitmentResponse> jobRecruitmentResponses = mapper.toResponses(jobRecruitments);
            return new Page<JobRecruitmentResponse>()
                    .setTotal(total)
                    .setItems(jobRecruitmentResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<JobRecruitmentResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }


    @Override
    public Page<JobRecruitmentResponse> select(SearchRequest searchRequest) {
        try {
            List<JobRecruitment> jobRecruitments = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<JobRecruitmentResponse> jobRecruitmentResponses = mapper.toResponses(jobRecruitments);
            return new Page<>(total, searchRequest, jobRecruitmentResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }
}
