package com.helmes.spring.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PolicyDAOImpl implements PolicyDAO {

    private static final Logger logger = LoggerFactory.getLogger(PolicyDAOImpl.class);
    private static final int limitResultsPerPage = 5;
    @Autowired
    private SessionFactory sessionFactory;
    private static final String SQL_GET_ALL = "from Policy where is_delete =false or is_delete is null";
    private static final String SQL_RMV = "update Policy set is_delete = true, is_active = false where id = :id ";
    private static final String SQL_FIND = "from Policy where (is_delete =false or is_delete is null) and price >= :pricef and type = :typef and active = :activef";
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addPolicy(Policy p) {
        Session session = this.sessionFactory.getCurrentSession();

        session.persist(p);
        logger.info("Policy saved successfully, Policy Details="+p);
    }

    @Override
    public void updatePolicy(Policy p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);  // можно просто так, без HQL и не merge()?
        logger.info("Policy updated successfully, Policy Details="+p);
    }

    @Override
    public List<Policy> listPolicys() {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(SQL_GET_ALL);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(SQL_GET_ALL);

        return new PaginationResult<Policy>(query, page, maxResult, maxNavigationPage);
    }


    @Override
    public Policy getPolicyById(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Policy where id = :id ");
        query.setParameter("id", id);
        Policy p = (Policy) query.uniqueResult();
        logger.info("Policy loaded successfully, Policy details="+p);
        return p;
    }

    @Override
    public void removePolicy(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(SQL_RMV);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        logger.info("Policy deleted successfully, policy details="+id);
    }

    @Override
    public List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(SQL_FIND);

        query.setParameter("pricef", pricef);
        query.setParameter("typef", typef);
        query.setParameter("activef", activef);

        List<Policy> policysList = query.list();

        for(Policy p : policysList){
            logger.info("Policy List::"+p);
        }
        return policysList;
    }

}