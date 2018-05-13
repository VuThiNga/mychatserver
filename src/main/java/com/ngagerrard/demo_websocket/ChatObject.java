package com.ngagerrard.demo_websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ChatObject {

    private String userName;
    private String message;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ChatObject() {
    }
    @JsonCreator
    public ChatObject(@JsonProperty(value = "userName") String userName,
                      @JsonProperty(value = "message")String message,
                      @JsonProperty(value = "createdDate")Date createdDate) {
        super();
        this.userName = userName;
        this.message = message;
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
