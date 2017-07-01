package com.springapp.mvc.service;

import com.springapp.mvc.dao.DvdDAO;
import com.springapp.mvc.dto.Dvd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DvdService implements IDvdService {
    @Autowired
    private DvdDAO dvdDAO;

    @Override
    @Transactional
    public void addDvd(Dvd dvd) {
        dvdDAO.addDvd(dvd);
    }

    @Override
    @Transactional
    public List<Dvd> getDvdForOwner(Integer userId) {
        return dvdDAO.getDvdForOwner(userId);
    }

    @Transactional
    public List<Dvd> getTakenDvd(Integer userId){
        return dvdDAO.getTakenDvd(userId);
    }

    @Transactional
    public List<Dvd> getGivenDvd(Integer userId){
        return dvdDAO.getGivenDvd(userId);
    }

    @Transactional
    public Dvd get(Integer id){
        return dvdDAO.get(id);
    }

    @Transactional
    public List<Dvd> getFree() {
        return dvdDAO.getFree();
    }
}
