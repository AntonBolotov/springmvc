package com.springapp.mvc.service;

import com.springapp.mvc.dto.Profile;

import java.util.List;

public interface IProfileService {

    public Profile getByName(String name);

    public Profile getById(Integer id);

    public List<Profile> getAll();
}
