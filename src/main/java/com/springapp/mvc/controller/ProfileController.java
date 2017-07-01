package com.springapp.mvc.controller;

import com.springapp.mvc.dto.Profile;
import com.springapp.mvc.response.BaseResponse;
import com.springapp.mvc.response.ProfileResponse;
import com.springapp.mvc.response.SimpleResponse;
import com.springapp.mvc.service.IProfileService;
import com.springapp.mvc.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/profile/all", method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponse getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof String) {
            return new SimpleResponse("Сеанс пользователя закончен");
        }

        User user = (User) authentication.getPrincipal();
        Profile profile = profileService.getByName(user.getUsername());

        ProfileResponse response = new ProfileResponse(profile);

        response.setProfiles(profileService.getAll());
        return response;
    }
}
