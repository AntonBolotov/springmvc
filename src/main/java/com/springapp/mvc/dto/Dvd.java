package com.springapp.mvc.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "T_DVD")
public class Dvd {

    private Integer id;


    private String name;


    private String description;


    private String poster;


    private String posterName;


//    private Integer ownerId;

    private Profile profile;


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
    @Column(name = "NAME")
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "DESCRIPTION")
    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "POSTER")
    @JsonProperty
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    @Column(name = "POSTER_NAME")
    @JsonProperty
    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }
//    @Column(name = "OWNER")
//    @JsonProperty
//    public Integer getOwnerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(Integer ownerId) {
//        this.ownerId = ownerId;
//    }

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "OWNER", nullable = false)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", posterName='" + posterName + '\'' +
                ", profile=" + profile +
                '}';
    }
}
