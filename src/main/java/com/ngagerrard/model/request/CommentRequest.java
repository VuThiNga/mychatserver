package com.ngagerrard.model.request;

public class CommentRequest {
    private int feedId;
    private String comment;
    private String attchmentThumbUrl;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
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
}
