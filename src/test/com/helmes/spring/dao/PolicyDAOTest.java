package com.helmes.spring.dao;


import com.helmes.spring.model.Policy;
import com.helmes.spring.model.Type;
import com.helmes.spring.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ContextConfiguration(locations = "classpath:appconfig-root-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyDAOTest {
    @Autowired PolicyDAOImpl policyDAO;
    @Autowired SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback(true)
    public void addPolicyTest(){
        Policy policy = new Policy();
        Type type = new Type();
        type.setName("KASKO");
        Session session = this.sessionFactory.getCurrentSession();  // надо сохранять в таблицу для тестов Type - у нас нет добавления типа в функциональности
        session.persist(type);

        policy.setType(type);
        policy.setActive(true);
        policy.setDelete(false);
        policy.setPrice(new BigDecimal(547.0));
        policyDAO.addPolicy(policy);

        List<Policy> policyList = policyDAO.listPolicys();
        Assert.assertEquals(new BigDecimal(547), policyList.get(0).getPrice());
    }

}
