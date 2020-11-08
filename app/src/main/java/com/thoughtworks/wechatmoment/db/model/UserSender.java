package com.thoughtworks.wechatmoment.db.model;

public class UserSender {

    private String avatar;
    private String username;
    private String nick;

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

    public UserSender(String avatar, String username, String nick) {
        this.avatar = avatar;
        this.username = username;
        this.nick = nick;
    }
}
