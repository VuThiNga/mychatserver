package com.ngagerrard.controller;

import com.ngagerrard.manager.MessageManager;
import com.ngagerrard.model.request.MessageRequest;
import com.ngagerrard.model.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    MessageManager manager;

    @PostMapping(value = "api/message/create")
    public ResponseEntity createMessage(
            @RequestBody MessageRequest request
    ) {
        return new ResponseEntity(manager.createMessage(request), HttpStatus.OK);
    }

    @GetMapping(value = "api/message/listconversation")
    public ResponseEntity getListConversation() {
        return new ResponseEntity(manager.getListConversation(), HttpStatus.OK);
    }

    @GetMapping(value = "api/message/list")
    public ResponseEntity getAllMessage(
            @RequestParam("idconversation") int id
    ) {
        return new ResponseEntity(manager.getAllMessage(id), HttpStatus.OK);
    }

    @PutMapping(value = "api/message/delete")
    public ResponseEntity deleteMessage(
            @RequestParam("idconversation") int id
    ) {
        return new ResponseEntity(manager.deletedMessage(id), HttpStatus.OK);
    }
}
