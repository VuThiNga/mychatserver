package com.ngagerrard.manager;

import com.ngagerrard.model.response.ParticipantsResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.mysql.tables.Participants;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantsManager {
    @Autowired
    DSLContext dslContext;
    public Object getAllConversationOfUser(){
        int id = getIdFromToken();
        List<ParticipantsResponse> responses = dslContext.selectFrom(Participants.PARTICIPANTS)
                .where(Participants.PARTICIPANTS.USER_ID.eq(id))
                .fetch()
                .map(participantsRecord -> {
                    ParticipantsResponse response = new ParticipantsResponse();
                    response.setConversationId(participantsRecord.getConversationId());
                    response.setUserId(participantsRecord.getUserId());
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
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
