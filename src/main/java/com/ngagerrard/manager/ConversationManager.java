package com.ngagerrard.manager;

import com.ngagerrard.model.request.ConversationRequest;
import com.ngagerrard.model.response.ConversationResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.mysql.enums.ConversationType;
import com.ngagerrard.mysql.tables.*;
import com.ngagerrard.mysql.tables.records.ConversationRecord;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversationManager {
    @Autowired
    DSLContext dslContext;

    public Object getConversationById(int conversationId) {
        List<ConversationResponse> responses = dslContext.selectFrom(Conversation.CONVERSATION)
                .where(Conversation.CONVERSATION.ID.eq(conversationId))
                .fetch()
                .map(conversationRecord -> {
                    ConversationResponse response = new ConversationResponse();
                    response.setTitle(conversationRecord.getTitle());
                    response.setCreatorId(conversationRecord.getCreatorId());
                    response.setCreatedDate(conversationRecord.getCreatedDate());
                    response.setDeletedDate(conversationRecord.getDeletedDate());
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
    }

    public Object getGroupConversations() {
        int id = getIdFromToken();
        ConversationType type = ConversationType.group;
        List<ConversationResponse> list = dslContext.selectDistinct(
                Conversation.CONVERSATION.CREATOR_ID,
                Users.USERS.AVATAR,
                Conversation.CONVERSATION.TYPE,
                Users.USERS.USER_NAME,
                Conversation.CONVERSATION.CREATED_DATE,
                Conversation.CONVERSATION.TITLE
        )
                .from(Conversation.CONVERSATION
                        .join(Participants.PARTICIPANTS).on(Conversation.CONVERSATION.ID.eq(Participants.PARTICIPANTS.CONVERSATION_ID))
                        .join(Users.USERS).on(Conversation.CONVERSATION.CREATOR_ID.eq(Users.USERS.ID))
                )
                .where(Participants.PARTICIPANTS.USER_ID.eq(id)
                        .and(Conversation.CONVERSATION.DELETED_DATE.isNull())
                        .and(Conversation.CONVERSATION.TYPE.eq(type))
                )
                .fetch()
                .map(record -> {
                    ConversationResponse response = new ConversationResponse();
                    response.setCreatorId(record.get(Conversation.CONVERSATION.CREATOR_ID));
                    response.setAvatar(record.get(Users.USERS.AVATAR));
                    response.setType(record.get(Conversation.CONVERSATION.TYPE));
                    response.setUsername(record.get(Users.USERS.USER_NAME));
                    response.setTitle(record.get(Conversation.CONVERSATION.TITLE));
                    response.setCreatedDate(record.get(Conversation.CONVERSATION.CREATED_DATE));
                    return response;
                });
        return ResponseUtils.getBaseResponse(list);
    }

    public Object getSingleConversations() {

        ConversationType type = ConversationType.single;
        int id = getIdFromToken();
        List<ConversationResponse> list = dslContext.selectDistinct(
                Conversation.CONVERSATION.CREATOR_ID,
                Users.USERS.AVATAR,
                Conversation.CONVERSATION.TYPE,
                Users.USERS.USER_NAME,
                Conversation.CONVERSATION.CREATED_DATE,
                Conversation.CONVERSATION.TITLE
                )
                .from(Conversation.CONVERSATION
                        .join(Participants.PARTICIPANTS).on(Conversation.CONVERSATION.ID.eq(Participants.PARTICIPANTS.CONVERSATION_ID))
                        .join(Users.USERS).on(Conversation.CONVERSATION.CREATOR_ID.eq(Users.USERS.ID))
                )
                .where(Participants.PARTICIPANTS.USER_ID.eq(id)
                        .and(Conversation.CONVERSATION.DELETED_DATE.isNull())
                        .and(Conversation.CONVERSATION.TYPE.eq(type))
                )
                .fetch()
                .map(record -> {
                    ConversationResponse response = new ConversationResponse();
                    response.setCreatorId(record.get(Conversation.CONVERSATION.CREATOR_ID));
                    response.setAvatar(record.get(Users.USERS.AVATAR));
                    response.setType(record.get(Conversation.CONVERSATION.TYPE));
                    response.setUsername(record.get(Users.USERS.USER_NAME));
                    response.setTitle(record.get(Conversation.CONVERSATION.TITLE));
                    response.setCreatedDate(record.get(Conversation.CONVERSATION.CREATED_DATE));
                    return response;
                });
        return ResponseUtils.getBaseResponse(list);
    }

//    public Object deleleConversation(){
//
//    }
//
//    public Object leaveConversation(){
//
//    }
    public Object createConversation(ConversationRequest request) {
        int id = getIdFromToken();
        ConversationType type = ConversationType.single;
        List<String> listId = request.getUsername();

        if(listId.size() > 1){
            type = ConversationType.group;
        }else {
            type = ConversationType.single;

        }

        int idConversation = dslContext.insertInto(Conversation.CONVERSATION, Conversation.CONVERSATION.CREATOR_ID,
                Conversation.CONVERSATION.TITLE, Conversation.CONVERSATION.TYPE)
                .values(id, request.getTitle(), type)
                .returning().fetchOne()
                .value1();

        dslContext.insertInto(Participants.PARTICIPANTS, Participants.PARTICIPANTS.CONVERSATION_ID, Participants.PARTICIPANTS.USER_ID)
                .values(idConversation, id).execute();
        for(int i = 0; i < listId.size(); i++){
            int idUser = dslContext.select(Users.USERS.ID)
                    .from(Users.USERS)
                    .where(Users.USERS.USER_NAME.eq(listId.get(i)))
                    .fetchAny()
                    .value1();
            dslContext.insertInto(Participants.PARTICIPANTS, Participants.PARTICIPANTS.CONVERSATION_ID, Participants.PARTICIPANTS.USER_ID)
                    .values(idConversation, idUser).execute();
        }
        return ResponseUtils.getBaseResponse(request);
    }

    public int getIdFromToken() {
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
