package com.ngagerrard.model.response;

import java.util.Date;

public class LikeResponse {
    private int feedId;
    //private int creatorId;
    private String username;
    private Date createdDate;
    //private byte isLike;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    public int getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(int creatorId) {
//        this.creatorId = creatorId;
//    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    public byte getIsLike() {
//        return isLike;
//    }
//
//    public void setIsLike(byte isLike) {
//        this.isLike = isLike;
//    }
}
