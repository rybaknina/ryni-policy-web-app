package com.helmes.spring.dao;

import com.helmes.spring.model.Type;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TypeDAO {

    Type getTypeById(UUID id);
    Type getTypeByName(String typename);
    List<Type> getAllTypes();
 //   public Map<String, String> getMapTypes();
}
