package com.ngagerrard.model.response;

import com.ngagerrard.mysql.enums.UsersGender;

import java.util.Date;

public class UserResponse {
    private String username;
    private String phone;
    private String avatar;
    private String imgCover;
    private Enum<UsersGender> gender;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImgCover() {
        return imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public Enum<UsersGender> getGender() {
        return gender;
    }

    public void setGender(Enum<UsersGender> gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
