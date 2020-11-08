package com.thoughtworks.wechatmoment.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity(String profileImage, String avatar, String username, String nick) {
        this.profileImage = profileImage;
        this.avatar = avatar;
        this.username = username;
        this.nick = nick;
    }

    public UserEntity(UserEntity userEntity) {
        this.profileImage = userEntity.profileImage;
        this.avatar = userEntity.avatar;
        this.username = userEntity.username;
        this.nick = userEntity.nick;
    }
}
