package com.thoughtworks.wechatmoment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.thoughtworks.wechatmoment.db.entity.CommentEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface CommentDao {
    @Query("SELECT * FROM comments WHERE comment_chat_id = :chatMomentId")
    Flowable<List<CommentEntity>> loadComments(String chatMomentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllComments(List<CommentEntity> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAComment(CommentEntity comment);
}
