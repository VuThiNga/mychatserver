package com.ngagerrard.controller;

import com.ngagerrard.manager.ConversationManager;
import com.ngagerrard.model.request.ConversationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConversationController {
    @Autowired
    ConversationManager manager;

    @GetMapping(value = "api/conversation")
    public ResponseEntity getConversationById(
            @RequestParam("conversationId") int conversationId
    ) {
        return new ResponseEntity(manager.getConversationById(conversationId), HttpStatus.OK);
    }

    @PostMapping(value = "api/conversation/create", consumes = "application/json")
    public ResponseEntity createConversation(
            @RequestBody ConversationRequest request
    ) {
        return new ResponseEntity(manager.createConversation(request), HttpStatus.OK);
    }
    @GetMapping(value = "api/conversation/allsingleconversation")
    public ResponseEntity getSingleConversations(){
        return new ResponseEntity(manager.getSingleConversations(), HttpStatus.OK);
    }

    @GetMapping(value = "api/conversation/allgroupconversation")
    public ResponseEntity getGroupConversations(){
        return new ResponseEntity(manager.getGroupConversations(), HttpStatus.OK);
    }

}
