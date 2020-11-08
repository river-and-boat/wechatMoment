package com.thoughtworks.wechatmoment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;

import java.util.List;

@Dao
public interface ChatMomentDao {

    @Query("SELECT * FROM chat_moments")
    List<ChatMomentEntity> loadChatMoments();

    @Query("SELECT * FROM chat_moments WHERE id = :chatMomentId")
    ChatMomentEntity loadChatMomentsById(int chatMomentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChatMoments(List<ChatMomentEntity> chatMoments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertACharMoment(ChatMomentEntity chatMoment);
}
