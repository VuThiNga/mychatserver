package com.ngagerrard.manager;

import com.ngagerrard.model.request.MessageRequest;
import com.ngagerrard.model.response.LikeResponse;
import com.ngagerrard.model.response.MessageResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.model.response.UserResponse;
import com.ngagerrard.mysql.enums.MessagesMessageType;
import com.ngagerrard.mysql.tables.Friend;
import com.ngagerrard.mysql.tables.Like;
import com.ngagerrard.mysql.tables.Messages;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.MessagesRecord;
import com.ngagerrard.security.Utils;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.jooq.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class MessageManager {
    @Autowired
    DSLContext dslContext;
    public Object createMessage(MessageRequest request){
        int id = getIdFromToken();
        MessagesRecord record= dslContext.insertInto(Messages.MESSAGES,
                Messages.MESSAGES.CONVERSATION_ID, Messages.MESSAGES.SENDER_ID,
                Messages.MESSAGES.MESSAGE_TYPE,
                Messages.MESSAGES.MESSAGE, Messages.MESSAGES.ATTACHMENT_URL,
                Messages.MESSAGES.ATTACHMENT_URL)
                .values(request.getConversationId(), id,
                        (MessagesMessageType) request.getMessageType(),
                        request.getMessage(), request.getAttachmentUrl(),
                        request.getAttachmentThumbUrl())
                .returning().fetchOne();

        return ResponseUtils.getBaseResponse(request);
    }

//    public Object deletedMessage(int id){
//
//    }

    public Object getListConversation(){
        int id = getIdFromToken();
        List<Integer> conversationId = dslContext
                .selectDistinct(Messages.MESSAGES.CONVERSATION_ID)
                .from(Messages.MESSAGES)
                .where(Messages.MESSAGES.SENDER_ID.eq(id))
                .groupBy(Messages.MESSAGES.SENDER_ID)
                .fetch(Messages.MESSAGES.CONVERSATION_ID);

        return ResponseUtils.getBaseResponse(conversationId);
    }

    public Object getAllMessage(int conversationId){
        int id = getIdFromToken();
        List<MessageResponse> responses = dslContext.selectFrom(Messages.MESSAGES)
                .where(Messages.MESSAGES.CONVERSATION_ID.eq(conversationId)
                        .and(Messages.MESSAGES.DELETED_DATE.isNull()))
                .fetch()
                .map(messagesRecord -> {
                    MessageResponse response = new MessageResponse();
                    response.setConversationId(conversationId);
                    response.setSendId(messagesRecord.getSenderId());
                    response.setMessage(messagesRecord.getMessage());
//                    response.setAttachmentUrl(messagesRecord.getAttachmentUrl());
//                    response.setAttachmentThumbUrl(messagesRecord.getAttachmentThumbUrl());
                    response.setCreateDate(messagesRecord.getCreatedDate());
                    return response;
                });

        return ResponseUtils.getBaseResponse(conversationId);
    }

    public Object deletedMessage(int conversationId){
        int id = getIdFromToken();

        dslContext.update(Messages.MESSAGES)
                .set(Messages.MESSAGES.DELETED_DATE, (Timestamp)Utils.getCurrentTimeUsingCalendar())
                .where(Messages.MESSAGES.CONVERSATION_ID.eq(conversationId)
                        .and(Messages.MESSAGES.DELETED_DATE.isNull()))
                .execute();


        return ResponseUtils.getBaseResponse(conversationId);
    }
    public int getIdFromToken(){
        JwtAuthenticationToken token =
                (JwtAuthenticationToken)
                        SecurityContextHolder.getContext()
                                .getAuthentication();

        String username = token.getCredentials().getUsername();
        int id = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(username))
                .fetchAny()
                .value1();
        return id;
    }


}
