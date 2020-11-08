package com.thoughtworks.wechatmoment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.thoughtworks.wechatmoment.db.entity.CommentEntity;

import java.util.List;

@Dao
public interface CommentDao {

    // todo 分页
    @Query("SELECT * FROM comments WHERE chatMomentId = :chatMomentId")
    List<CommentEntity> loadComments(int chatMomentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllComments(List<CommentEntity> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAComments(CommentEntity comment);
}
