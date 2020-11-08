package com.thoughtworks.wechatmoment.db.model;

public class User {

    private String profileImage;
    private String avatar;
    private String username;
    private String nick;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public User(String profileImage, String avatar, String username, String nick) {
        this.profileImage = profileImage;
        this.avatar = avatar;
        this.username = username;
        this.nick = nick;
    }
}
