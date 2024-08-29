package com.mh.core.mhsrepository.repository.applicant;

import com.mh.core.mhscommons.data.tables.pojos.Applicant;
import com.mh.core.mhscommons.data.tables.records.ApplicantRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.APPLICANT;


@Repository
public class ApplicantRepositoryImp extends AbsRepository<ApplicantRecord, Applicant, Integer> implements IApplicantRepository {
    @Override
    protected TableImpl<ApplicantRecord> getTable() {
        return APPLICANT;
    }
}
