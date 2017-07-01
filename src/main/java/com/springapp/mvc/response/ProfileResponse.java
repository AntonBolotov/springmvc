package com.springapp.mvc.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapp.mvc.dto.Profile;

import java.util.List;

/**
 * Created by tovnah on 30.06.17.
 */
public class ProfileResponse extends BaseResponse {
    @JsonProperty
    public List<Profile> profiles;
    public ProfileResponse(Profile currentProfile) {
        super(currentProfile);
    }

    public ProfileResponse(Profile currentProfile, String errorMessage) {
        super(currentProfile, errorMessage);
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
