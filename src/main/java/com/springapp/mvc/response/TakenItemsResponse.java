package com.springapp.mvc.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.dto.Profile;
import com.springapp.mvc.dto.TakenItem;

import java.util.List;

public class TakenItemsResponse extends BaseResponse{

    @JsonProperty
    public List<TakenItem> takenItems;

    public TakenItemsResponse(Profile currentProfile) {
        super(currentProfile);
    }

    public TakenItemsResponse(Profile currentProfile, String errorMessage) {
        super(currentProfile, errorMessage);
    }

    public List<TakenItem> getTakenItems() {
        return takenItems;
    }

    public void setTakenItems(List<TakenItem> takenItems) {
        this.takenItems = takenItems;
    }
}
