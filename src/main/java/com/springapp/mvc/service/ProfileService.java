package com.springapp.mvc.service;

import com.springapp.mvc.dao.ProfileDAO;
import com.springapp.mvc.dto.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    @Transactional
    public Profile getByName(String name) {
       return profileDAO.getByName(name);
    }

    @Transactional
    public Profile getById(Integer id) {
        return profileDAO.getById(id);
    }

    @Transactional
    public List<Profile> getAll(){
        return profileDAO.getAll();
    }
}
