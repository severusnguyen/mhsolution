package com.mh.core.mhsrepository.repository.home_page;

import com.mh.core.mhscommons.data.tables.pojos.HomePage;
import com.mh.core.mhscommons.data.tables.records.HomePageRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.HOME_PAGE;

@Repository
public class HomePageRepositoryImp extends AbsRepository<HomePageRecord, HomePage, Integer> implements IHomePageRepository {
    @Override
    protected TableImpl<HomePageRecord> getTable() {
        return HOME_PAGE;
    }
}
