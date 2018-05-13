package com.ngagerrard.model.response;

import java.util.Date;

public class CommentResponse {
    private int feedId;
    //private int creatorId;
    private String username;
    private String avatar;
    private String comment;
    private String attchmentThumbUrl;
    private Date createdDate;
    private Date updatedDate;
    private byte isLike;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }


//    public int getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(int creatorId) {
//        this.creatorId = creatorId;
//    }


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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAttchmentThumbUrl() {
        return attchmentThumbUrl;
    }

    public void setAttchmentThumbUrl(String attchmentThumbUrl) {
        this.attchmentThumbUrl = attchmentThumbUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

//    public Date getDeletedDate() {
//        return deletedDate;
//    }
//
//    public void setDeletedDate(Date deletedDate) {
//        this.deletedDate = deletedDate;
//    }

    public byte getIsLike() {
        return isLike;
    }

    public void setIsLike(byte isLike) {
        this.isLike = isLike;
    }
}
