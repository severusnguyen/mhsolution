package com.mh.core.mhsrepository.repository.category_new;

import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICategoryNewRepository {
    List<CategoryNew> getCategoryNewByIds(Collection<Integer> ids);
    Optional<CategoryNew> findById (Integer id);
}
