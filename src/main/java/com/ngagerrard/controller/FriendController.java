package com.ngagerrard.controller;

import com.ngagerrard.manager.FriendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {
    @Autowired
    FriendManager manager;
    @GetMapping(value = "api/friend/getall")
    public ResponseEntity getAllFriend(){
        return new ResponseEntity(manager.getAllFriend(), HttpStatus.OK);
    }

    @GetMapping(value = "api/friend/newfriend")
    public ResponseEntity getAllNewFriend(){
        return new ResponseEntity(manager.getAllNewFriend(), HttpStatus.OK);
    }

    @PutMapping(value = "api/friend/accept")
    public ResponseEntity acceptFriendRequest(
            @RequestParam("name") String name
    ){
        return new ResponseEntity(manager.acceptFriendRequest(name), HttpStatus.OK);
    }

    @DeleteMapping(value = "api/friend/delete")
    public ResponseEntity deleteFriend(
            @RequestParam("name") String name
    ){
        return new ResponseEntity(manager.deleteFriend(name), HttpStatus.OK);
    }

    @PostMapping(value = "api/friend/create")
    public ResponseEntity createFriend(
            @RequestParam("name") String request
            ){
                return new ResponseEntity(manager.createFriend(request), HttpStatus.OK);
    }
}
