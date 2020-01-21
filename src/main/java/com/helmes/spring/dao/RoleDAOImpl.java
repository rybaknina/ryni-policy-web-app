package com.helmes.spring.dao;

import com.helmes.spring.model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
//    @Transactional(readOnly = true)
    public List<Role> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role order by name");
        List<Role> roleList = query.list();

        for(Role r : roleList){
            logger.info("Role List::"+r);
        }
        return roleList;
    }


}
