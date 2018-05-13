package com.ngagerrard.model.request;


import java.sql.Date;

public class FeedRequest {
    private String status;
    private String attachmentUrl;
    //private Date deletedDate;

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

}
