package com.ngagerrard.manager;

import com.ngagerrard.Constants;
import com.ngagerrard.model.request.FeedRequest;
import com.ngagerrard.model.response.FeedResponse;
import com.ngagerrard.model.response.FeedResponseBonus;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.mysql.tables.*;
import com.ngagerrard.mysql.tables.records.FeedRecord;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StatusManager extends BaseManager {
    @Autowired
    DSLContext dslContext;

    public Object createFeed(FeedRequest feedRequest) {
        int id = getIdFromToken();
        FeedRecord feedRecord = dslContext.insertInto(Feed.FEED,
                Feed.FEED.CREATOR_ID, Feed.FEED.STATUS, Feed.FEED.ATTACHMENT_URL)
                .values(id, feedRequest.getStatus(), feedRequest.getAttachmentUrl())
                .returning().fetchOne();
        return ResponseUtils.getBaseResponse(feedRequest);
    }

    public Object getFeedById(int feedId) {
        int id = getIdFromToken();
        List<FeedResponse> responses = dslContext.selectFrom(Feed.FEED)
                .where(Feed.FEED.ID.eq(feedId).and(Feed.FEED.DELETED_DATE.isNull()))
                .fetch().sortDesc(Feed.FEED.ID)
                .map(feedRecord -> {
                    FeedResponse response = new FeedResponse();
                    response.setFeedId(feedRecord.getId());
                    response.setStatus(feedRecord.getStatus());
                    response.setAttachmentUrl(feedRecord.getAttachmentUrl());
                    response.setCreatedDate(feedRecord.getCreatedDate());
                    int index = feedRecord.get(Feed.FEED.ID);
                    int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                            .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                    int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                            .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                    response.setCountLike(countLike);
                    response.setCounComment(countComment);
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
    }

    public Object getListFeed() {
        int id = getIdFromToken();
        String name = dslContext.select(Users.USERS.USER_NAME).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        String avatar = dslContext.select(Users.USERS.AVATAR).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        List<FeedResponseBonus> responses = dslContext.selectFrom(Feed.FEED)
                .where(Feed.FEED.CREATOR_ID.eq(id).and(Feed.FEED.DELETED_DATE.isNull()))
                .fetch().sortDesc(Feed.FEED.ID)
                .map(feedRecord -> {
                    FeedResponseBonus response = new FeedResponseBonus();
                    response.setFeedId(feedRecord.getId());
                    response.setStatus(feedRecord.getStatus());
                    response.setAttachmentUrl(feedRecord.getAttachmentUrl());
                    response.setCreatedDate(feedRecord.getCreatedDate());
                    response.setUsername(name);
                    response.setAvatar(avatar);
                    int index = feedRecord.get(Feed.FEED.ID);
                    int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                            .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                    int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                            .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                    response.setCountLike(countLike);
                    response.setCounComment(countComment);
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
    }

    public Object getListFeedWithImage() {
        int id = getIdFromToken();
        String name = dslContext.select(Users.USERS.USER_NAME).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        String avatar = dslContext.select(Users.USERS.AVATAR).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        List<FeedResponseBonus> responses = dslContext.selectFrom(Feed.FEED)
                .where(Feed.FEED.CREATOR_ID.eq(id).and(Feed.FEED.DELETED_DATE.isNull())
                        .and(Feed.FEED.ATTACHMENT_URL.isNotNull()))
                .fetch().sortDesc(Feed.FEED.ID)
                .map(feedRecord -> {
                    FeedResponseBonus response = new FeedResponseBonus();
                    response.setFeedId(feedRecord.getId());
                    response.setStatus(feedRecord.getStatus());
                    response.setAttachmentUrl(feedRecord.getAttachmentUrl());
                    response.setCreatedDate(feedRecord.getCreatedDate());
                    response.setUsername(name);
                    response.setAvatar(avatar);
                    int index = feedRecord.get(Feed.FEED.ID);
                    int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                            .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                    int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                            .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                    response.setCountLike(countLike);
                    response.setCounComment(countComment);
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
    }

    public Object getListFeedWithImageById(String name) {
        int id = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(name))
                .fetchAny()
                .value1();
        String avatar = dslContext.select(Users.USERS.AVATAR).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        List<FeedResponseBonus> responses = dslContext.selectFrom(Feed.FEED)
                .where(Feed.FEED.CREATOR_ID.eq(id).and(Feed.FEED.DELETED_DATE.isNull())
                        .and(Feed.FEED.ATTACHMENT_URL.isNotNull()))
                .fetch().sortDesc(Feed.FEED.ID)
                .map(feedRecord -> {
                    FeedResponseBonus response = new FeedResponseBonus();
                    response.setFeedId(feedRecord.getId());
                    response.setStatus(feedRecord.getStatus());
                    response.setAttachmentUrl(feedRecord.getAttachmentUrl());
                    response.setCreatedDate(feedRecord.getCreatedDate());
                    response.setUsername(name);
                    response.setAvatar(avatar);
                    int index = feedRecord.get(Feed.FEED.ID);
                    int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                            .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                    int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                            .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                    response.setCountLike(countLike);
                    response.setCounComment(countComment);
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);
    }

    //status, like, comment
    public Object getListFeedById(String name) {
        int id = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(name))
                .fetchAny()
                .value1();
        String avatar = dslContext.select(Users.USERS.AVATAR).from(Users.USERS)
                .where(Users.USERS.ID.eq(id)).fetchAny().value1();
        List<FeedResponseBonus> responses = dslContext.selectFrom(Feed.FEED)
                .where(Feed.FEED.CREATOR_ID.eq(id).and(Feed.FEED.DELETED_DATE.isNull()))
                .fetch()
                .map(feedRecord -> {
                    FeedResponseBonus response = new FeedResponseBonus();
                    response.setFeedId(feedRecord.getId());
                    response.setStatus(feedRecord.getStatus());
                    response.setAttachmentUrl(feedRecord.getAttachmentUrl());
                    response.setCreatedDate(feedRecord.getCreatedDate());
                    response.setUsername(name);
                    response.setAvatar(avatar);
                    int index = feedRecord.get(Feed.FEED.ID);
                    int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                            .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                    int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                            .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                    response.setCountLike(countLike);
                    response.setCounComment(countComment);
                    return response;
                });
        return ResponseUtils.getBaseResponse(responses);

    }

    public Object getNewFeed(){
        int id = getIdFromToken();
        List<Integer> listFriend = dslContext.select()
                .from(Friend.FRIEND)
                .where(Friend.FRIEND.PERSON_TWO.eq(id). and(Friend.FRIEND.IS_FRIEND.eq((byte)1)))
                .fetch().getValues(Friend.FRIEND.PERSON_ONE, Integer.class);
        List<Integer> listFriend2 = dslContext.select()
                .from(Friend.FRIEND)
                .where(Friend.FRIEND.PERSON_ONE.eq(id). and(Friend.FRIEND.IS_FRIEND.eq((byte)1)))
                .fetch().getValues(Friend.FRIEND.PERSON_TWO, Integer.class);
        for (Integer friendId: listFriend2){
            listFriend.add(friendId);
        }
        List<FeedResponseBonus> list = new ArrayList<FeedResponseBonus>();
        for(Integer idFriend: listFriend){
            List<FeedResponseBonus> responses = dslContext.select()
                    .from(Feed.FEED)
                    .join(Users.USERS).on(Feed.FEED.CREATOR_ID.eq(Users.USERS.ID))
                    .where(Feed.FEED.CREATOR_ID.eq(idFriend).and(Feed.FEED.DELETED_DATE.isNull()))
                    .fetch()
                    .map(feedRecord -> {
                        FeedResponseBonus response = new FeedResponseBonus();
                        response.setFeedId(feedRecord.get(Feed.FEED.ID));
                        response.setStatus(feedRecord.get(Feed.FEED.STATUS));
                        response.setAttachmentUrl(feedRecord.get(Feed.FEED.ATTACHMENT_URL));
                        response.setCreatedDate(feedRecord.get(Feed.FEED.CREATED_DATE));
                        response.setAvatar(feedRecord.get(Users.USERS.AVATAR));
                        response.setUsername(feedRecord.get(Users.USERS.USER_NAME));
                        int index = feedRecord.get(Feed.FEED.ID);
                        int countLike = dslContext.selectCount().from(Like.LIKE).where(Like.LIKE.FEED_ID.eq(index)
                                .and(Like.LIKE.IS_LIKE.eq((byte) 1))).fetchAny().value1();
                        int countComment = dslContext.selectCount().from(Comment.COMMENT).where(Comment.COMMENT.FEED_ID.eq(index)
                                .and(Comment.COMMENT.DELETED_DATE.isNull())).fetchAny().value1();
                        response.setCountLike(countLike);
                        response.setCounComment(countComment);
                        return response;
                    });
            for(FeedResponseBonus feedResponse: responses){
                list.add(feedResponse);
            }
        }
        Collections.sort(list, new Comparator<FeedResponseBonus>() {
            @Override
            public int compare(FeedResponseBonus o1, FeedResponseBonus o2) {
                return (o2.getCreatedDate().compareTo(o1.getCreatedDate()));
            }
        });
        if(list.size() >= Constants.MAX_FEED){
            for(int i = Constants.MAX_FEED; i < list.size(); i++){
                list.remove(i);
            }
        }
        return ResponseUtils.getBaseResponse(list);
    }
    //    public Object updateFeed(int feedId){
//
//    }
    public Object deleteFeed(Integer feedId) {
        int id = getIdFromToken();
        dslContext.delete(Feed.FEED)
                .where(Feed.FEED.CREATOR_ID.eq(id).and(Feed.FEED.ID.eq(feedId)))
                .execute();
        return ResponseUtils.getBaseResponse(feedId);
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
