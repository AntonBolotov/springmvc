package com.springapp.mvc.service;

import com.springapp.mvc.dto.TakenItem;

import java.util.List;

public interface ITakenItemService {
    public void add(TakenItem takenItem);

    public void remove(TakenItem takenItem);

    public TakenItem getByDvd(Integer id);

    public List<TakenItem> getByUser(Integer id);

    public List<TakenItem> getFromUser(Integer id);

    public TakenItem get(Integer id);
}
