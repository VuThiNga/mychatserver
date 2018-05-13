package com.ngagerrard.manager;

import com.ngagerrard.model.request.ProfileRequest;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.model.response.UserResponse;
import com.ngagerrard.mysql.enums.UsersGender;
import com.ngagerrard.mysql.tables.Friend;
import com.ngagerrard.mysql.tables.Users;
import com.ngagerrard.security.withoutloginregister.JwtAuthenticationToken;
import org.jooq.DSLContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.ngagerrard.mysql.tables.Users.USERS;

@Component
public class ProfileManager extends BaseManager{
    @Autowired
    DSLContext dslContext;
    public Object getProfileMySelf(){
        int id = getIdFromToken();

        UserResponse response = dslContext.select().
                from(USERS)
                .where(USERS.ID.eq(id)).fetchOne()
                .map(usersRecord -> {
                    UserResponse response1 = new UserResponse();
                    response1.setUsername(usersRecord.get(USERS.USER_NAME));
                    response1.setBirthday(usersRecord.get(USERS.BIRTHDAY));
                    response1.setAvatar(usersRecord.get(USERS.AVATAR));
                    response1.setImgCover(usersRecord.get(USERS.IMG_COVER));
                    response1.setGender(usersRecord.get(USERS.GENDER));
                    response1.setPhone(usersRecord.get(USERS.PHONE));
                    return response1;
                });
        return ResponseUtils.getBaseResponse(response);
    }
    public Object getProfileByName(String usernameFriend){
        UserResponse response = dslContext.select().
                from(USERS)
                .where(USERS.USER_NAME.eq(usernameFriend)).fetchOne()
                .map(usersRecord -> {
                    UserResponse response1 = new UserResponse();
                    response1.setUsername(usersRecord.get(USERS.USER_NAME));
                    response1.setBirthday(usersRecord.get(USERS.BIRTHDAY));
                    response1.setAvatar(usersRecord.get(USERS.AVATAR));
                    response1.setImgCover(usersRecord.get(USERS.IMG_COVER));
                    response1.setGender(usersRecord.get(USERS.GENDER));
                    response1.setPhone(usersRecord.get(USERS.PHONE));
                    return response1;
                });
        return ResponseUtils.getBaseResponse(response);
    }

    public int getIdFromToken(){
        JwtAuthenticationToken token =
                (JwtAuthenticationToken)
                        SecurityContextHolder.getContext()
                                .getAuthentication();

        String username = token.getCredentials().getUsername();
        int id = dslContext.select(USERS.ID)
                .from(USERS)
                .where(USERS.USER_NAME.eq(username))
                .fetchAny()
                .value1();
        return id;
    }

    public Object updateProfile(ProfileRequest request){
        int id = getIdFromToken();
        dslContext.update(USERS)
                .set(Users.USERS.USER_NAME, request.getUsename())
                .set(Users.USERS.PHONE, request.getPhone())
                .set(Users.USERS.BIRTHDAY, Timestamp.valueOf(request.getBirthday()))
                .set(Users.USERS.GENDER, UsersGender.valueOf(request.getGender()))
                .where(USERS.ID.eq(id))
                .execute();
        UserResponse response = dslContext.select().
                from(USERS)
                .where(USERS.ID.eq(id)).fetchOne()
                .map(usersRecord -> {
                    UserResponse response1 = new UserResponse();
                    response1.setUsername(usersRecord.get(USERS.USER_NAME));
                    response1.setBirthday(usersRecord.get(USERS.BIRTHDAY));
                    response1.setAvatar(usersRecord.get(USERS.AVATAR));
                    response1.setImgCover(usersRecord.get(USERS.IMG_COVER));
                    response1.setGender(usersRecord.get(USERS.GENDER));
                    response1.setPhone(usersRecord.get(USERS.PHONE));
                    return response1;
                });
        return ResponseUtils.getBaseResponse(response);
    }


    public Object updateAvatar(String path){
        int id = getIdFromToken();
        dslContext.update(USERS)
                .set(USERS.AVATAR, path)
                .where(USERS.ID.eq(id))
                .execute();
        return ResponseUtils.getBaseResponse(path);
    }

    public Object updateImgCover(String path){
        int id = getIdFromToken();
        dslContext.update(USERS)
                .set(USERS.IMG_COVER, path)
                .where(USERS.ID.eq(id))
                .execute();
        return ResponseUtils.getBaseResponse(path);
    }
    public Object getAllFriendByKey(String keywword) {
        int id = getIdFromToken();

        List<UserResponse> responsess = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_TWO.eq(Users.USERS.ID))
                .where(Users.USERS.USER_NAME.contains(keywword)
                        .or(Users.USERS.PHONE.contains(keywword))
                        .and(Friend.FRIEND.PERSON_ONE.eq(id))
                        .and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
                .where(Users.USERS.USER_NAME.contains(keywword)
                        .or(Users.USERS.PHONE.contains(keywword)).and(Friend.FRIEND.PERSON_TWO.eq(id))
                        .and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
    public Object searchFriend(String keyword){
        int id = getIdFromToken();
        List<UserResponse> responsess = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_TWO.eq(Users.USERS.ID))
                .where(Users.USERS.USER_NAME.contains(keyword)
                        .or(Users.USERS.PHONE.contains(keyword))
                        .and(Friend.FRIEND.PERSON_ONE.eq(id)))
                        //.and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
                .where(Users.USERS.USER_NAME.contains(keyword)
                        .or(Users.USERS.PHONE.contains(keyword)).and(Friend.FRIEND.PERSON_TWO.eq(id)))
                       // .and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
        List<UserResponse> responseAll = dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.ID.notEqual(id).and(Users.USERS.USER_NAME.contains(keyword)).
                        or(Users.USERS.PHONE.contains(keyword)))
                .fetch()

                .map(usersRecord -> {
                    UserResponse response = new UserResponse();
                    response.setUsername(usersRecord.getUserName());
                    response.setBirthday(usersRecord.getBirthday());
                    response.setAvatar(usersRecord.getAvatar());
                    response.setImgCover(usersRecord.getImgCover());
                    response.setGender(usersRecord.getGender());
                    response.setPhone(usersRecord.getPhone());
                    return response;
                });

        for(int i = 0; i < responsess.size(); i++){
            for(int j = 0; j < responseAll.size(); j++){
                if(responseAll.get(j).getUsername().equals(responsess.get(i).getUsername())){
                    responseAll.remove(j);
                    continue;
                }
            }
        }
        return ResponseUtils.getBaseResponse(responseAll);
    }

    public Object randomFriend(){
        int id = getIdFromToken();
        List<UserResponse> responsesRandom = dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.ID.notEqual(id))
                .limit(dslContext.fetchCount(Users.USERS) - 5)
                .fetch()
                .map(usersRecord -> {
                    UserResponse response = new UserResponse();
                    response.setUsername(usersRecord.getUserName());
                    response.setBirthday(usersRecord.getBirthday());
                    response.setAvatar(usersRecord.getAvatar());
                    response.setImgCover(usersRecord.getImgCover());
                    response.setGender(usersRecord.getGender());
                    response.setPhone(usersRecord.getPhone());
                    return response;
                });

        List<UserResponse> responsess = dslContext.select(Users.USERS.fields())
                .from(Friend.FRIEND)
                .join(Users.USERS)
                .on(Friend.FRIEND.PERSON_TWO.eq(Users.USERS.ID))
                .where(Friend.FRIEND.PERSON_ONE.eq(id))
                       // .and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
                .where(Friend.FRIEND.PERSON_TWO.eq(id))
                        //.and(Friend.FRIEND.IS_FRIEND.eq((byte) 1)))
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
        for(int i = 0; i < responsess.size(); i++){
            for(int j = 0; j < responsesRandom.size(); j++){
                if(responsesRandom.get(j).getUsername().equals(responsess.get(i).getUsername())){
                    responsesRandom.remove(j);
                    continue;
                }
            }

        }
        return ResponseUtils.getBaseResponse(responsesRandom);
    }

}
