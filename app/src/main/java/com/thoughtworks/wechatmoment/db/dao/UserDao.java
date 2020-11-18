package com.thoughtworks.wechatmoment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.thoughtworks.wechatmoment.db.entity.UserEntity;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    UserEntity getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAUser(UserEntity user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUserInfo(UserEntity user);
}
