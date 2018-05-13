package com.ngagerrard.controller;

import com.ngagerrard.manager.LikeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {
    @Autowired
    LikeManager manager;

    @GetMapping(value = "api/getalllike/feed")
    public ResponseEntity getAllLikeOfFeed(
            @RequestParam("feedId") int feedId
    ) {
        return new ResponseEntity(manager.getAllLikeOfFeed(feedId), HttpStatus.OK);
    }

    @PostMapping(value = "api/createlike/feed")
    public ResponseEntity createLike(
            @RequestParam("feedId") int feedId
    ) {
        return new ResponseEntity(manager.createLike(feedId), HttpStatus.OK);
    }

    @PutMapping(value = "api/updatelike/feed")
    public ResponseEntity updateLike(
            @RequestParam("feedId") int feedId
    ){
        return new ResponseEntity(manager.updateLike(feedId), HttpStatus.OK);
    }
}
