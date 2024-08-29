package com.mh.core.mhsrepository.repository.news;

import com.mh.core.mhscommons.data.tables.pojos.New;
import com.mh.core.mhscommons.data.tables.records.NewRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.tables.New.NEW;

@Repository
public class NewRepository extends AbsRepository<NewRecord, New, Integer> {

    @Override
    protected TableImpl<NewRecord> getTable() {
        return NEW;
    }
}
