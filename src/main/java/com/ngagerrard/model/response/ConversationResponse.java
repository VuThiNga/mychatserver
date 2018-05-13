package com.ngagerrard.model.response;

import com.ngagerrard.mysql.enums.ConversationType;

import java.util.Date;

public class ConversationResponse {
    private int creatorId;
    private String title;
    private String username;
    private String avatar;
    private Date createdDate;
    private Date deletedDate;
    private Enum<ConversationType> type;
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Enum<ConversationType> getType() {
        return type;
    }

    public void setType(Enum<ConversationType> type) {
        this.type = type;
    }
}
