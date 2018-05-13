package com.ngagerrard.manager;

import com.ngagerrard.model.response.FriendResponse;
import com.ngagerrard.model.response.LikeResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.mysql.tables.Friend;
import com.ngagerrard.mysql.tables.Like;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.LikeRecord;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeManager extends BaseManager {
    @Autowired
    DSLContext dslContext;
    public Object createLike(int feedId){
        int id = getIdFromToken();
        LikeRecord record = dslContext.insertInto(Like.LIKE,
                Like.LIKE.FEED_ID, Like.LIKE.CREATOR_ID, Like.LIKE.IS_LIKE)
                .values(feedId, id, (byte)1)
                .returning().fetchOne();
        return ResponseUtils.getBaseResponse(feedId);
    }

    public Object getAllLikeOfFeed(int feedId){
        List<LikeResponse> responses = dslContext.select()
                .from(Like.LIKE)
                .join(Users.USERS).on(Like.LIKE.CREATOR_ID.eq(Users.USERS.ID))
                .where(Like.LIKE.FEED_ID.eq(feedId).and(Like.LIKE.IS_LIKE.eq((byte)1)))
                .fetch()
                .map(re -> {
                    LikeResponse response = new LikeResponse();
                    response.setFeedId(re.get(Like.LIKE.FEED_ID));
                    response.setUsername(re.get(Users.USERS.USER_NAME));
                    response.setCreatedDate(re.get(Like.LIKE.CREATED_DATE));
                    return response;
                });

        return ResponseUtils.getBaseResponse(responses);
    }

    public Object updateLike(int feedId){
        int id = getIdFromToken();
        dslContext.update(Like.LIKE)
                .set(Like.LIKE.IS_LIKE, Like.LIKE.IS_LIKE.bitNot())
                .where(Like.LIKE.FEED_ID.eq(feedId).and(Like.LIKE.CREATOR_ID.eq(id)))
                .execute();
        return ResponseUtils.getBaseResponse(feedId);
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
