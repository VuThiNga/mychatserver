package com.ngagerrard.model.response;

import com.ngagerrard.mysql.enums.MessagesMessageType;

import java.util.Date;

public class MessageResponse {
    private int conversationId;
    private int sendId;
    private String message;
//    private String attachmentUrl;
//    private String attachmentThumbUrl;
    //private Enum<MessagesMessageType> messageType;
    private Date createDate;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getAttachmentUrl() {
//        return attachmentUrl;
//    }
//
//    public void setAttachmentUrl(String attachmentUrl) {
//        this.attachmentUrl = attachmentUrl;
//    }
//
//    public String getAttachmentThumbUrl() {
//        return attachmentThumbUrl;
//    }
//
//    public void setAttachmentThumbUrl(String attachmentThumbUrl) {
//        this.attachmentThumbUrl = attachmentThumbUrl;
//    }

//    public Enum<MessagesMessageType> getMessageType() {
//        return messageType;
//    }
//
//    public void setMessageType(Enum<MessagesMessageType> messageType) {
//        this.messageType = messageType;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
