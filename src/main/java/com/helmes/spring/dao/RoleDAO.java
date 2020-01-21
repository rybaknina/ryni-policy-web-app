package com.helmes.spring.dao;

import com.helmes.spring.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> findAll();
}
