package com.ngagerrard.model.response;

public class FriendResponse {
    private int friendId;
    private int userId;
    private byte isFriend;

    public byte getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(byte isFriend) {
        this.isFriend = isFriend;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
