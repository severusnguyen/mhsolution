package com.mh.core.mhsrepository.repository.co_founder;

import com.mh.core.mhscommons.data.tables.pojos.CoFounder;
import com.mh.core.mhscommons.data.tables.records.CoFounderRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.CO_FOUNDER;

@Repository
public class CoFounderRepositoryImp extends AbsRepository<CoFounderRecord, CoFounder, Integer> implements ICoFounderRepository {
    @Override
    protected TableImpl<CoFounderRecord> getTable() {
        return CO_FOUNDER;
    }
}
