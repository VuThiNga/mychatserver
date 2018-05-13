package com.ngagerrard.controller;

import com.ngagerrard.manager.ParticipantsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParticipantsController {
    @Autowired
    ParticipantsManager manager;

    @GetMapping(value = "api/participants/getall")
    public ResponseEntity getAllConversationOfUser(){
        return new ResponseEntity(manager.getAllConversationOfUser(), HttpStatus.OK);
    }
}
