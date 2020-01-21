package com.helmes.spring.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;

public interface PolicyService {

    void addPolicy(Policy p);
    void updatePolicy(Policy p);
    PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage);
    List<Policy> listPolicys();
    Policy getPolicyById(UUID id);
    void removePolicy(UUID id);
    List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef);

}