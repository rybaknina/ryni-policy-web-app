package com.helmes.spring.dao;

import com.helmes.spring.model.User;

public interface UserDAO {
    void save(User user);

    User findByUsername(String username);
}
