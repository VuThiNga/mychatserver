package com.ngagerrard.model.response;

import java.util.Date;
import java.util.List;

public class FeedResponse {
    private int feedId;
    private String status;
    private String attachmentUrl;
    private Date createdDate;
    private int countLike;
    private int counComment;

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCounComment() {
        return counComment;
    }

    public void setCounComment(int counComment) {
        this.counComment = counComment;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }
    //private Date deletedDate;
//    private List<CommentResponse> commentResponses;
//    private List<LikeResponse> likeResponses;

//    public List<CommentResponse> getCommentResponses() {
//        return commentResponses;
//    }
//
//    public void setCommentResponses(List<CommentResponse> commentResponses) {
//        this.commentResponses = commentResponses;
//    }
//
//    public List<LikeResponse> getLikeResponses() {
//        return likeResponses;
//    }
//
//    public void setLikeResponses(List<LikeResponse> likeResponses) {
//        this.likeResponses = likeResponses;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    public Date getDeletedDate() {
//        return deletedDate;
//    }
//
//    public void setDeletedDate(Date deletedDate) {
//        this.deletedDate = deletedDate;
//    }
}
