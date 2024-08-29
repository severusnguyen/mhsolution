package com.mh.core.mhsrepository.repository.cooperation_contact;

import com.mh.core.mhscommons.data.tables.pojos.CooperationContact;
import com.mh.core.mhscommons.data.tables.records.CooperationContactRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.COOPERATION_CONTACT;

@Repository
public class CooperationContactRepositoryImp extends AbsRepository<CooperationContactRecord, CooperationContact, Integer> implements ICooperationContactRepository {

    @Override
    protected TableImpl<CooperationContactRecord> getTable() {
        return COOPERATION_CONTACT;
    }
}
