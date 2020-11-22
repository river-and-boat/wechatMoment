package com.thoughtworks.wechatmoment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ChatMomentDao {

    @Query("SELECT * FROM chat_moments")
    Flowable<List<ChatMomentEntity>> loadChatMoments();

    @Query("SELECT * FROM chat_moments WHERE chat_id = :chatMomentId")
    Flowable<ChatMomentEntity> loadChatMomentsById(int chatMomentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertChatMoments(List<ChatMomentEntity> chatMoments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertACharMoment(ChatMomentEntity chatMoment);
}
