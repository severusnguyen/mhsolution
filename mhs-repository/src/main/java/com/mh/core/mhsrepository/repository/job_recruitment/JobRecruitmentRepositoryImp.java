package com.mh.core.mhsrepository.repository.job_recruitment;


import com.mh.core.mhscommons.data.tables.pojos.JobRecruitment;
import com.mh.core.mhscommons.data.tables.records.JobRecruitmentRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.JOB_RECRUITMENT;

@Repository
public class JobRecruitmentRepositoryImp extends AbsRepository<JobRecruitmentRecord, JobRecruitment, Integer> implements IJobRecruitmentRepository {
    @Override
    protected TableImpl<JobRecruitmentRecord> getTable() {
        return JOB_RECRUITMENT;
    }
}
