package com.springapp.mvc.dao;

import com.springapp.mvc.dto.Dvd;

import java.util.List;

public interface IDvdDAO {
    public void addDvd(Dvd dvd);

    public List<Dvd> getDvdForOwner(Integer userId);


}
