package com.ngagerrard.model.request;

import com.ngagerrard.mysql.enums.MessagesMessageType;

public class MessageRequest {
    private int conversationId;
    private String message;
    private String attachmentUrl;
    private String attachmentThumbUrl;
    private Enum<MessagesMessageType> messageType;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentThumbUrl() {
        return attachmentThumbUrl;
    }

    public void setAttachmentThumbUrl(String attachmentThumbUrl) {
        this.attachmentThumbUrl = attachmentThumbUrl;
    }

    public Enum<MessagesMessageType> getMessageType() {
        return messageType;
    }

    public void setMessageType(Enum<MessagesMessageType> messageType) {
        this.messageType = messageType;
    }
}
