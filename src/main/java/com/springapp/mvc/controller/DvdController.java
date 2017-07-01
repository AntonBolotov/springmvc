package com.springapp.mvc.controller;

import com.springapp.mvc.dto.Dvd;
import com.springapp.mvc.dto.Profile;
import com.springapp.mvc.dto.TakenItem;
import com.springapp.mvc.response.BaseResponse;
import com.springapp.mvc.response.DvdResponse;
import com.springapp.mvc.response.SimpleResponse;
import com.springapp.mvc.response.TakenItemsResponse;
import com.springapp.mvc.service.IDvdService;
import com.springapp.mvc.service.ProfileService;
import com.springapp.mvc.service.TakenItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
public class DvdController {
    Logger logger = LoggerFactory.getLogger(DvdController.class);

    @Autowired(required = true)
    IDvdService dvdService;
    @Autowired(required = true)
    ProfileService profileService;
    @Autowired(required = true)
    TakenItemService takenItemService;

    @RequestMapping(value = "/dvd/new", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse addDvd(@ModelAttribute("dvd") NewDvdRequest billingandrecon) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile profile = profileService.getByName(user.getUsername());

            Dvd dvd = new Dvd();
            dvd.setName(billingandrecon.name);
            dvd.setDescription(billingandrecon.description);
            dvd.setProfile(profile);
            dvd.setPosterName(billingandrecon.poster.getOriginalFilename());
            dvd.setPoster(Base64.getEncoder().encodeToString(billingandrecon.poster.getBytes()));

            dvdService.addDvd(dvd);

            return null;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Ошибка при добавлении нового диска");
        }
    }

    @RequestMapping(value = "/dvd/my", method = {RequestMethod.GET})
    @ResponseBody
    public BaseResponse getMyDvd() throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile profile = profileService.getByName(user.getUsername());

            List<Dvd> dvds = dvdService.getDvdForOwner(profile.getId());

            DvdResponse response = new DvdResponse(profile);
            response.setDvds(dvds);

            return response;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    @RequestMapping(value = "/dvd/give", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse giveDvd(@RequestParam(name = "dvdId") final Integer dvdId,
                                @RequestParam(name = "userId") Integer userId) {
        try {
            //проверяем входящие параметры
            if (dvdId == null || userId == null) {
                return new SimpleResponse("Переданы не верные параметры");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            Dvd dvd = dvdService.get(dvdId);

            if (dvd.getProfile().getId().equals(userId)) {
                //не можем отдать диск его же владельцу
                return new SimpleResponse("Выбранный диск уже принадлежит этому пользователю");
            }

            if (!dvd.getProfile().getId().equals(currentUser.getId())) {
                //не можем отдавать не свой диск
                return new SimpleResponse("Вы не являетесь владельцем данного диска");
            }

            if (takenItemService.getByDvd(dvdId) != null) {
                //кому то уже отдали диск
                return new SimpleResponse("Выбранный диск уже отдан другому пользователю");
            }

            Profile profile = profileService.getById(userId);
            TakenItem takenItem = new TakenItem();
            takenItem.setDvd(dvd);
            takenItem.setProfile(profile);

            takenItemService.add(takenItem);
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
        return null;
    }

    @RequestMapping(value = "/dvd/take", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse takeDvd(@RequestParam(name = "dvdId") final Integer dvdId) {
        try {
            //проверяем входящие параметры
            if (dvdId == null) {
                return new SimpleResponse("Переданы не верные параметры");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            Dvd dvd = dvdService.get(dvdId);

            if (dvd.getProfile().getId().equals(currentUser.getId())) {
                //не можем взять собственный диск
                return new SimpleResponse("Данный диск вам принадлежит");
            }

            if (takenItemService.getByDvd(dvdId) != null) {
                //диск уже кем то взят
                return new SimpleResponse("Диск отдан другому пользователю");
            }

            TakenItem takenItem = new TakenItem();
            takenItem.setDvd(dvd);
            takenItem.setProfile(currentUser);

            takenItemService.add(takenItem);
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
        return null;
    }

    @RequestMapping(value = "/dvd/get/taken", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getTakenDvds() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            List<TakenItem> takenItems = takenItemService.getByUser(currentUser.getId());

            TakenItemsResponse response = new TakenItemsResponse(currentUser);
            response.setTakenItems(takenItems);

            return response;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    @RequestMapping(value = "/dvd/get/given", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getGivenDvds() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            List<TakenItem> takenItems = takenItemService.getFromUser(currentUser.getId());

            TakenItemsResponse response = new TakenItemsResponse(currentUser);
            response.setTakenItems(takenItems);

            return response;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    @RequestMapping(value = "/dvd/get/free", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getFree() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            List<Dvd> dvds = dvdService.getFree();
            DvdResponse dvdResponse = new DvdResponse(currentUser);
            dvdResponse.setDvds(dvds);

            return dvdResponse;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    @RequestMapping(value = "/dvd/retrieve", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse retrieveDvd(@RequestParam(name = "id") Integer id) {
        try {
            //проверяем входящие параметры
            if (id == null) {
                return new SimpleResponse("Переданы не верные параметры");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            TakenItem takenItem = takenItemService.get(id);

            if (takenItem == null) {
                return new SimpleResponse("Данный диск никем не взят");
            }

            if (!takenItem.getProfile().getId().equals(currentUser.getId())) {
                return new SimpleResponse("Данный диск взят не вами");
            }

            takenItemService.remove(takenItem);

            return null;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    @RequestMapping(value = "/dvd/pickup", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse pickupDvd(@RequestParam(name = "id") Integer id) {
        try {
            //проверяем входящие параметры
            if (id == null) {
                return new SimpleResponse("Переданы не верные параметры");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof String) {
                return new SimpleResponse("Сеанс пользователя закончен");
            }

            User user = (User) authentication.getPrincipal();
            Profile currentUser = profileService.getByName(user.getUsername());

            TakenItem takenItem = takenItemService.get(id);

            if (takenItem == null) {
                return new SimpleResponse("Данный диск никем не взят");
            }

            if (!takenItem.getDvd().getProfile().getId().equals(currentUser.getId())) {
                return new SimpleResponse("Данный вам не принадлежит");
            }

            takenItemService.remove(takenItem);

            return null;
        } catch (Exception e) {
            logger.error("giveDvd", e);

            return new SimpleResponse("Неизвестная ошибка");
        }
    }

    public static class NewDvdRequest {
        private MultipartFile poster;
        private String name;
        private String description;

        public NewDvdRequest() {
        }

        public MultipartFile getPoster() {
            return poster;
        }

        public void setPoster(MultipartFile poster) {
            this.poster = poster;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
