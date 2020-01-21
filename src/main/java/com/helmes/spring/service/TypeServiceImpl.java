package com.helmes.spring.service;

import com.helmes.spring.dao.TypeDAO;
import com.helmes.spring.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDAO typeDAO;
    public void setTypeDAO(TypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Type> getAllTypes() {
        return this.typeDAO.getAllTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Type getTypeById(UUID id) {
        return this.typeDAO.getTypeById(id);
    }
 //   @Override
 //   @Transactional(readOnly = true)
 //   public Map<String, String> getMapTypes() {
 //       return this.typeDAO.getMapTypes();
 //   }
}