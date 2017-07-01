package com.springapp.mvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "T_TAKEN_ITEM")
public class TakenItem {


    private Integer id;
//    @Column(name = "DVD_ID")
//    private Integer dvdId;
//    @Column(name = "USER_ID")
//    private Integer userId;

    private Profile profile;

    private Dvd dvd;


    @ManyToOne(targetEntity = Dvd.class)
    @JoinColumn(name = "DVD_ID", nullable = false)
    public Dvd getDvd() {
        return dvd;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    @JsonProperty
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "USER_ID", nullable = false)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }

    //    public Integer getDvdId() {
//        return dvdId;
//    }
//
//    public void setDvdId(Integer dvdId) {
//        this.dvdId = dvdId;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    @Override
    public String toString() {
        return "TakenItem{" +
                "id=" + id +
                ", profile=" + profile +
                ", dvd=" + dvd +
                '}';
    }
}
