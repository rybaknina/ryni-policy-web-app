package com.helmes.spring.service;

import com.helmes.spring.model.Type;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TypeService {

    List<Type> getAllTypes();
    Type getTypeById(UUID id);
  //  public Map<String, String> getMapTypes();
}