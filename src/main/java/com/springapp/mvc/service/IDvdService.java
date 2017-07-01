package com.springapp.mvc.service;

import com.springapp.mvc.dto.Dvd;

import java.util.List;

public interface IDvdService {
    public void addDvd(Dvd dvd);

    public List<Dvd> getDvdForOwner(Integer userId);

    public List<Dvd> getTakenDvd(Integer userId);

    public List<Dvd> getGivenDvd(Integer userId);

    public Dvd get(Integer id);

    public List<Dvd> getFree();

}
