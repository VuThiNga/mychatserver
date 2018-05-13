package com.ngagerrard.model.response;

import java.util.Date;

public class OutputMessage {
    private String from;
    private String message;
    private String topic;
    private Date time = new Date();

    public OutputMessage(String from, String text, String topic) {
        this.from = from;
        this.message = text;
        this.topic = topic;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
