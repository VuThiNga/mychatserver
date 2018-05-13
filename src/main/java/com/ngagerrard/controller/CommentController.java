package com.ngagerrard.controller;

import com.ngagerrard.manager.CommentManager;
import com.ngagerrard.model.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentManager manager;

    @GetMapping(value = "api/commment/feed")
    public ResponseEntity getAllComment(
            @RequestParam("feedId") int feedId
    ) {
        return new ResponseEntity(manager.getAllComment(feedId), HttpStatus.OK);
    }

    @PostMapping(value = "api/comment/create")
    public ResponseEntity createComment(
            @RequestBody CommentRequest request
            ) {
        return new ResponseEntity(manager.createComment(request), HttpStatus.OK);
    }

    @PutMapping(value = "api/comment/like")
    public ResponseEntity likeComment(
            @RequestParam("idComment") Integer idComment
    ){
        return new ResponseEntity(manager.likeComment(idComment), HttpStatus.OK);
    }

    @PutMapping(value = "api/comment/dislike")
    public ResponseEntity dislikeComment(
            @RequestParam("idComment") Integer idComment
    ){
        return new ResponseEntity(manager.dislikeComment(idComment), HttpStatus.OK);
    }

    @PutMapping(value = "api/comment/update")
    public ResponseEntity updateComment(
            @RequestBody CommentRequest request
    ){
        return new ResponseEntity(manager.updateComment(request), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String heelo(){
                return "hello";
    }
}
