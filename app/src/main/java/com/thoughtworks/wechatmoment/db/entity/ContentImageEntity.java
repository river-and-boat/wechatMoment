package com.thoughtworks.wechatmoment.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "content_image", foreignKeys = {
        @ForeignKey(entity = ChatMomentEntity.class,
                parentColumns = "chat_id",
                childColumns = "content_chat_id",
                onDelete = ForeignKey.CASCADE)
})
public class ContentImageEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "content_id")
    private int id;

    @ColumnInfo(name = "content_chat_id")
    private String chatMomentId;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ContentImageEntity(String url) {
        this.url = url;
    }

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
}
