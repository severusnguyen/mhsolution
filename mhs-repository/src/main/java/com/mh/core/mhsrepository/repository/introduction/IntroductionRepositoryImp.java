package com.mh.core.mhsrepository.repository.introduction;

import com.mh.core.mhscommons.data.tables.pojos.Introduction;
import com.mh.core.mhscommons.data.tables.records.IntroductionRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.INTRODUCTION;

@Repository
public class IntroductionRepositoryImp extends AbsRepository<IntroductionRecord, Introduction, Integer> implements IIntroductionRepository{
    @Override
    protected TableImpl<IntroductionRecord> getTable() {
        return INTRODUCTION;
    }
}
