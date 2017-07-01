package com.springapp.mvc.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.dto.Profile;

public class BaseResponse {
    @JsonProperty
    public Profile currentProfile;
    @JsonProperty
    public String message;

    public BaseResponse(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public BaseResponse(Profile currentProfile, String message) {
        this.currentProfile = currentProfile;
        this.message = message;
    }
}
