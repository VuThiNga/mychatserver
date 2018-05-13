package com.ngagerrard.controller;

import com.ngagerrard.manager.ProfileManager;
import com.ngagerrard.model.request.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    @Autowired
    ProfileManager manager;

    @GetMapping(value = "/api/profile")
    public ResponseEntity getProfileMySelf() {
        return new ResponseEntity(manager.getProfileMySelf(),
                HttpStatus.OK);
    }

    @PutMapping(value = "api/profile/update")
    public ResponseEntity updateProfile(
            @RequestBody ProfileRequest request
            ) {
        return new ResponseEntity(manager.updateProfile(request), HttpStatus.OK);
    }

    @GetMapping(value = "api/friend/profile")
    public ResponseEntity getProfileByName(
            @RequestParam("username") String name
    ) {
        return new ResponseEntity(manager.getProfileByName(name), HttpStatus.OK);
    }

    @PutMapping(value = "api/profile/avatar/update")
    public ResponseEntity updateAvatar(
            @RequestParam("path") String path
    ) {
        return new ResponseEntity(manager.updateAvatar(path), HttpStatus.OK);
    }

    @PutMapping(value = "api/profile/imgcover/update")
    public ResponseEntity updateImgCover(
            @RequestParam("path") String path
    ) {
        return new ResponseEntity(manager.updateImgCover(path), HttpStatus.OK);
    }

    @GetMapping(value = "api/profile/search")
    public ResponseEntity searchFriend(
            @RequestParam("keyword") String keyword
    ){
        return new ResponseEntity(manager.searchFriend(keyword), HttpStatus.OK);
    }

    @GetMapping(value = "api/profile/searchfriend")
    public ResponseEntity getAllFriendByKey(
            @RequestParam("keyword") String keyword
    ){
        return new ResponseEntity(manager.getAllFriendByKey(keyword), HttpStatus.OK);
    }

    @GetMapping(value = "api/profile/random")
    public ResponseEntity randomFriend(
    ){
        return new ResponseEntity(manager.randomFriend(), HttpStatus.OK);
    }
}
