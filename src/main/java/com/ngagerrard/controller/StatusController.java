package com.ngagerrard.controller;

import com.ngagerrard.manager.StatusManager;
import com.ngagerrard.model.request.FeedRequest;
import com.ngagerrard.model.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {
    @Autowired
    StatusManager manager;

    @PostMapping(value = "api/feed/create")
    public ResponseEntity createFeed(
            @RequestBody FeedRequest request
    ) {
        return new ResponseEntity(manager.createFeed(request), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed")
    public ResponseEntity getFeedById(
            @RequestParam("feedId") int feedId
    ) {
        return new ResponseEntity(manager.getFeedById(feedId), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed/getallfeed")
    public ResponseEntity getListFeed() {
        return new ResponseEntity(manager.getListFeed(), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed/allfeed")
    public ResponseEntity getListFeedById(
            @RequestParam("name") String name
    ) {
        return new ResponseEntity(manager.getListFeedById(name), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed/getallimage")
    public ResponseEntity getListFeedWithImage() {
        return new ResponseEntity(manager.getListFeedWithImage(), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed/getallimagebyid")
    public ResponseEntity getListFeedWithImageById(
            @RequestParam("name") String name
    ){
        return new ResponseEntity(manager.getListFeedWithImageById(name), HttpStatus.OK);
    }

    @DeleteMapping(value = "api/feed/delete")
    public ResponseEntity deleteFeed(
            @RequestParam("id") Integer feedId
    ){
                return new ResponseEntity(manager.deleteFeed(feedId), HttpStatus.OK);
    }

    @GetMapping(value = "api/feed/feedofallfriend")
    public ResponseEntity getNewFeed(
    ){
        return new ResponseEntity(manager.getNewFeed(), HttpStatus.OK);
    }
}
