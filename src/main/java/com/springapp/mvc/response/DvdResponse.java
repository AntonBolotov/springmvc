package com.springapp.mvc.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.dto.Dvd;
import com.springapp.mvc.dto.Profile;

import java.util.List;

public class DvdResponse extends BaseResponse {
    @JsonProperty
    public List<Dvd> dvds;

    public DvdResponse(Profile currentProfile) {
        super(currentProfile);
    }

    public DvdResponse(Profile currentProfile, String errorMessage) {
        super(currentProfile, errorMessage);
    }

    public List<Dvd> getDvds() {
        return dvds;
    }

    public void setDvds(List<Dvd> dvds) {
        this.dvds = dvds;
    }
}
