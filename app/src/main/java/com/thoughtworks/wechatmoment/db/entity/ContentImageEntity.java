package com.thoughtworks.wechatmoment.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "content_image", foreignKeys = {
        @ForeignKey(entity = ChatMomentEntity.class,
                parentColumns = "id",
                childColumns = "chatMomentId",
                onDelete = ForeignKey.CASCADE)
})
public class ContentImageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int chatMomentId;

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
}
