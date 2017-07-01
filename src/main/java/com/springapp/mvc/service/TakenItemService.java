package com.springapp.mvc.service;

import com.springapp.mvc.dao.TakenItemDAO;
import com.springapp.mvc.dto.TakenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TakenItemService {

    @Autowired
    private TakenItemDAO takenItemDAO;

    @Transactional
    public void add(TakenItem takenItem) {
        takenItemDAO.add(takenItem);
    }

    @Transactional
    public void remove(TakenItem takenItem) {
        takenItemDAO.remove(takenItem);
    }

    @Transactional
    public TakenItem getByDvd(Integer id) {
        return takenItemDAO.getByDvd(id);
    }

    @Transactional
    public List<TakenItem> getByUser(Integer id) {
        return takenItemDAO.getByUser(id);
    }

    @Transactional
    public List<TakenItem> getFromUser(Integer id) {
        return takenItemDAO.getFromUser(id);
    }

    @Transactional
    public TakenItem get(Integer id) {
        return takenItemDAO.get(id);
    }
}
