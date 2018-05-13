package com.ngagerrard.manager;

import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.model.response.UserResponse;
import com.ngagerrard.mysql.tables.Friend;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class FriendManager extends BaseManager {
    @Autowired
    DSLContext dslContext;

    public Object getAllFriend() {
        int id = getIdFromToken();

//        }
        List<UserResponse> responsess = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_TWO.eq(Users.USERS.ID))
                .where(Friend.FRIEND.PERSON_ONE.eq(id).and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
                .fetch()
                .map(record -> {
                    UserResponse response = new UserResponse();
                    response.setUsername(record.into(Users.USERS).getUserName());
                    response.setPhone(record.into(Users.USERS).getPhone());
                    response.setGender(record.into(Users.USERS).getGender());
                    response.setBirthday(record.into(Users.USERS).getBirthday());
                    response.setAvatar(record.into(Users.USERS).getAvatar());
                    response.setImgCover(record.into(Users.USERS).getImgCover());
                    return response;
                });
        List<UserResponse> responses = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_ONE.eq(Users.USERS.ID))
                .where(Friend.FRIEND.PERSON_TWO.eq(id).and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
                .fetch()
                .map(record -> {
                    UserResponse response = new UserResponse();
                    response.setUsername(record.into(Users.USERS).getUserName());
                    response.setPhone(record.into(Users.USERS).getPhone());
                    response.setGender(record.into(Users.USERS).getGender());
                    response.setBirthday(record.into(Users.USERS).getBirthday());
                    response.setAvatar(record.into(Users.USERS).getAvatar());
                    response.setImgCover(record.into(Users.USERS).getImgCover());
                    return response;
                });

        for(int i = 0; i < responses.size(); i++){
            responsess.add(responses.get(i));
        }
        return ResponseUtils.getBaseResponse(responsess);
    }

    public Object getAllNewFriend() {
        int id = getIdFromToken();

//        }
        List<UserResponse> responsess = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_ONE.eq(Users.USERS.ID))
                .where(Friend.FRIEND.PERSON_TWO.eq(id).and(Friend.FRIEND.IS_FRIEND.eq((byte) 0)))
                .fetch()
                .map(record -> {
                    UserResponse response = new UserResponse();
                    response.setUsername(record.into(Users.USERS).getUserName());
                    response.setPhone(record.into(Users.USERS).getPhone());
                    response.setGender(record.into(Users.USERS).getGender());
                    response.setBirthday(record.into(Users.USERS).getBirthday());
                    response.setAvatar(record.into(Users.USERS).getAvatar());
                    response.setImgCover(record.into(Users.USERS).getImgCover());
                    return response;
                });
        return ResponseUtils.getBaseResponse(responsess);
    }

    public Object acceptFriendRequest(String name) {
        int id = getIdFromToken();
        int idFriend = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(name))
                .fetchAny()
                .value1();
        dslContext.update(Friend.FRIEND)
                .set(Friend.FRIEND.IS_FRIEND, (byte) 1)
                .where(Friend.FRIEND.PERSON_TWO.eq(id)
                        .and(Friend.FRIEND.PERSON_ONE.eq(idFriend))
                        .and(Friend.FRIEND.IS_FRIEND.eq((byte) 0)))
                .execute();

        return ResponseUtils.getBaseResponse(name);
    }

    public Object deleteFriend(String name) {
        int id = getIdFromToken();
        int idFriend = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(name))
                .fetchAny()
                .value1();
        dslContext.delete(Friend.FRIEND)
                .where(Friend.FRIEND.PERSON_TWO.eq(id)
                        .and(Friend.FRIEND.PERSON_ONE.eq(idFriend)))
                .execute();
        return ResponseUtils.getBaseResponse(name);
    }

    public Object createFriend(String  request) {
        int idPersonOne = getIdFromToken();
        int idFriend = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(Users.USERS.USER_NAME.eq(request))
                .fetchAny()
                .value1();
        dslContext.insertInto(Friend.FRIEND,
                Friend.FRIEND.PERSON_ONE,
                Friend.FRIEND.PERSON_TWO,
                Friend.FRIEND.IS_FRIEND)
                .values(idPersonOne, idFriend, (byte) 0)
                .execute();
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
