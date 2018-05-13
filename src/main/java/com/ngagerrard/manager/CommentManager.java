package com.ngagerrard.manager;

import com.ngagerrard.model.request.CommentRequest;
import com.ngagerrard.model.response.CommentResponse;
import com.ngagerrard.model.response.FriendResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.mysql.tables.Comment;
import com.ngagerrard.mysql.tables.Friend;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.mysql.tables.records.CommentRecord;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentManager extends BaseManager {
    @Autowired
    DSLContext dslContext;
    public Object getAllComment(int feedId){
        int id = getIdFromToken();
        List<CommentResponse> responses = dslContext.select()
                .from(Comment.COMMENT)
                .join(Users.USERS).on(Comment.COMMENT.CREATOR_ID.eq(Users.USERS.ID))
                .where(Comment.COMMENT.FEED_ID.eq(feedId).and(Comment.COMMENT.DELETED_DATE.isNull()))
                .fetch()
                .map(re -> {
                    CommentResponse response = new CommentResponse();
                    response.setFeedId(re.get(Comment.COMMENT.FEED_ID));
                    response.setUsername(re.get(Users.USERS.USER_NAME));
                    response.setAvatar(re.get(Users.USERS.AVATAR));
                    response.setComment(re.get(Comment.COMMENT.COMMENT_));
                    response.setAttchmentThumbUrl(re.get(Comment.COMMENT.ATTACHMENT_THUMB_URL));
                    response.setCreatedDate(re.get(Comment.COMMENT.CREATED_DATE));
                    response.setUpdatedDate(re.get(Comment.COMMENT.UPDATED_DATE));
                    return response;
                });

        return ResponseUtils.getBaseResponse(responses);
    }

    public Object createComment(CommentRequest request){
        int id = getIdFromToken();
        CommentRecord record = dslContext.insertInto(Comment.COMMENT,
                Comment.COMMENT.CREATOR_ID, Comment.COMMENT.FEED_ID,
                Comment.COMMENT.COMMENT_, Comment.COMMENT.ATTACHMENT_THUMB_URL)
                .values(id, request.getFeedId(), request.getComment(), request.getAttchmentThumbUrl())
                .returning().fetchOne();
        return ResponseUtils.getBaseResponse(request);
    }

    public Object updateComment(CommentRequest request){
        int id = getIdFromToken();
        dslContext.update(Comment.COMMENT)
                .set(Comment.COMMENT.COMMENT_,request.getComment())
                .set(Comment.COMMENT.ATTACHMENT_THUMB_URL, request.getAttchmentThumbUrl())
                .where(Comment.COMMENT.CREATOR_ID.eq(id).and(Comment.COMMENT.FEED_ID.eq(request.getFeedId())))
                .execute();
        return ResponseUtils.getBaseResponse(request);
    }

    public Object likeComment(Integer commentId){
        int id = getIdFromToken();
        dslContext.update(Comment.COMMENT)
                .set(Comment.COMMENT.IS_LIKE, (byte)1)
                .where(Comment.COMMENT.ID.eq(commentId).and(Comment.COMMENT.IS_LIKE.eq((byte)0)))
                .execute();
        return ResponseUtils.getBaseResponse(commentId);
    }

    public Object dislikeComment(Integer commentId){
        int id = getIdFromToken();
        dslContext.update(Comment.COMMENT)
                .set(Comment.COMMENT.IS_LIKE, (byte)0)
                .where(Comment.COMMENT.ID.eq(commentId).and(Comment.COMMENT.IS_LIKE.eq((byte)1)))
                .execute();
        return ResponseUtils.getBaseResponse(commentId);
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
