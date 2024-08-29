package com.mh.core.mhsrepository.repository.product;

import com.mh.core.mhscommons.data.tables.pojos.Product;
import com.mh.core.mhscommons.data.tables.records.ProductRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.mh.core.mhscommons.data.Tables.PRODUCT;

@Repository
public class ProductRepositoryImp extends AbsRepository<ProductRecord, Product, Integer> implements IProductRepository {
    @Override
    protected TableImpl<ProductRecord> getTable() {
        return PRODUCT;
    }
}
