package com.thoughtworks.wechatmoment.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "comments", foreignKeys = {
        @ForeignKey(entity = ChatMomentEntity.class,
                parentColumns = "chat_id",
                childColumns = "comment_chat_id",
                onDelete = ForeignKey.CASCADE)
})
public class CommentEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "comment_id")
    private int id;

    @ColumnInfo(name = "comment_chat_id")
    private String chatMomentId;

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

    public String getChatMomentId() {
        return chatMomentId;
    }

    public void setChatMomentId(String chatMomentId) {
        this.chatMomentId = chatMomentId;
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

    public CommentEntity(String content, String avatar,
                         String username, String nick, String chatMomentId, Date date) {
        this.content = content;
        this.avatar = avatar;
        this.username = username;
        this.nick = nick;
        this.chatMomentId = chatMomentId;
        this.date = date;
    }

    public CommentEntity(CommentEntity commentEntity) {
        this.nick = commentEntity.nick;
        this.username = commentEntity.username;
        this.avatar = commentEntity.avatar;
        this.content = commentEntity.content;
        this.chatMomentId = commentEntity.chatMomentId;
        this.date = commentEntity.date;
    }
}
