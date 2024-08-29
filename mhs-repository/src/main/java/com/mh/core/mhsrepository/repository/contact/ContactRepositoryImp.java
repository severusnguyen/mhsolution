package com.mh.core.mhsrepository.repository.contact;

import static com.mh.core.mhscommons.data.Tables.CONTACT;
import com.mh.core.mhscommons.data.tables.pojos.Contact;
import com.mh.core.mhscommons.data.tables.records.ContactRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryImp extends AbsRepository<ContactRecord, Contact, Integer> implements IContactRepository {
    @Override
    protected TableImpl<ContactRecord> getTable() {
        return CONTACT;
    }
}
