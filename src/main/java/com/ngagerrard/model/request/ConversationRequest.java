package com.ngagerrard.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.catalina.LifecycleState;

import java.util.List;

public class ConversationRequest {
    private String title;
    private List<String> username;

    @JsonCreator
       public ConversationRequest(@JsonProperty("title") String title, @JsonProperty("username") List<String> username) {
        this.title = title;
        this.username = username;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
