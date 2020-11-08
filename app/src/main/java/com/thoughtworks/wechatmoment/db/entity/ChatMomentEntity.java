package com.thoughtworks.wechatmoment.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "chat_moments")
public class ChatMomentEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;

    private String avatar;

    private String username;

    private String nick;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChatMomentEntity(String content, String avatar,
                            String username, String nick, Date date) {
        this.content = content;
        this.avatar = avatar;
        this.username = username;
        this.nick = nick;
        this.date = date;
    }

    public ChatMomentEntity(ChatMomentEntity chatMomentEntity) {
        this.nick = chatMomentEntity.nick;
        this.username = chatMomentEntity.username;
        this.avatar = chatMomentEntity.avatar;
        this.content = chatMomentEntity.content;
        this.date = chatMomentEntity.date;
    }
}
