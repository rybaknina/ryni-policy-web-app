package com.helmes.spring.service;

import com.helmes.spring.dao.RoleDAO;
import com.helmes.spring.dao.UserDAO;
import com.helmes.spring.model.Role;
import com.helmes.spring.model.User;
import com.helmes.spring.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try{
        user.setRole(this.roleDAO.findAll().get(1));} // криво
        catch (Exception e){
            Role role = new Role();
            role.setName(UserRole.USER.name());  // криво. Как лучше работать с ролями?
            user.setRole(role);
        }
        this.userDAO.save(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }
}
