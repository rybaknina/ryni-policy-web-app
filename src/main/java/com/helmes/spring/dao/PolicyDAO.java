package com.helmes.spring.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;

public interface PolicyDAO {

    void addPolicy(Policy p);
    void updatePolicy(Policy p);
    List<Policy> listPolicys();
    PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage);
    Policy getPolicyById(UUID id);
    void removePolicy(UUID id);
    List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef);

}
