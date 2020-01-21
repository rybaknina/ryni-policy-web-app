package com.helmes.spring.service;


import com.helmes.spring.dao.UserDAO;
import com.helmes.spring.model.Role;
import com.helmes.spring.model.User;
import com.helmes.spring.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDAO.findByUsername(username);
        if (user == null) {  // надо, не надо
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    AuthorityUtils
                            .commaSeparatedStringToAuthorityList(UserRole.USER.name()));
        }
   //     List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList(user.getRole().getName());
       // for (Role role : user.getRoles()){
       //     grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auth);

    }
}
