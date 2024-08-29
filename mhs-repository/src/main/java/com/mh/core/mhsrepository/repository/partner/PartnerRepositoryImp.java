package com.mh.core.mhsrepository.repository.partner;

import com.mh.core.mhscommons.data.tables.pojos.Partner;
import com.mh.core.mhscommons.data.tables.records.PartnerRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.PARTNER;

@Repository
public class PartnerRepositoryImp extends AbsRepository<PartnerRecord, Partner, Integer>
        implements IPartnerRepository {
    @Override
    protected TableImpl<PartnerRecord> getTable() {
        return PARTNER;
    }
}
