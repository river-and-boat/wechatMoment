package com.thoughtworks.wechatmoment.db;

import android.content.Context;
import android.telecom.Call;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.thoughtworks.wechatmoment.AppExecutors;
import com.thoughtworks.wechatmoment.db.converter.DateConverter;
import com.thoughtworks.wechatmoment.db.dao.ChatMomentDao;
import com.thoughtworks.wechatmoment.db.dao.CommentDao;
import com.thoughtworks.wechatmoment.db.dao.UserDao;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.db.entity.ContentImageEntity;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;
import com.thoughtworks.wechatmoment.db.repository.DataGenerator;

import java.util.List;

@Database(entities = {ChatMomentEntity.class, CommentEntity.class,
        UserEntity.class, ContentImageEntity.class}, version = 1)
@TypeConverters(value = {DateConverter.class})
public abstract class WeChatDataBase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATA_BASE_NAME = "we-chat-db";

    private static WeChatDataBase weChatDataBase;

    public abstract ChatMomentDao chatMomentDao();

    public abstract CommentDao commentDao();

    public abstract UserDao userDao();

    public static WeChatDataBase getInstance(Context context, AppExecutors appExecutors) {
        if (weChatDataBase == null) {
            synchronized (WeChatDataBase.class) {
                if (weChatDataBase == null) {
                    weChatDataBase = buildDatabase(context, appExecutors);
                }
            }
        }
        return weChatDataBase;
    }

    private static WeChatDataBase buildDatabase(final Context context,
                                                final AppExecutors executors) {
        weChatDataBase = Room.databaseBuilder(context.getApplicationContext(),
                WeChatDataBase.class, DATA_BASE_NAME)
                .allowMainThreadQueries()
                .build();

        List<ChatMomentEntity> chatMoments = DataGenerator.getChatMoments();
        List<CommentEntity> comments = DataGenerator.getComments(chatMoments);
        UserEntity user = DataGenerator.getUser();
        insertData(weChatDataBase, chatMoments, comments, user);

        return weChatDataBase;
    }

    private static void insertData(final WeChatDataBase weChatDataBase,
                                   final List<ChatMomentEntity> chatMoments,
                                   final List<CommentEntity> comments,
                                   final UserEntity user) {
        weChatDataBase.runInTransaction(() -> {
            weChatDataBase.chatMomentDao().insertChatMoments(chatMoments);
//            weChatDataBase.commentDao().insertAllComments(comments);
//            weChatDataBase.userDao().insertAUser(user);
        });
        weChatDataBase.runInTransaction(() -> {
            weChatDataBase.commentDao().insertAllComments(comments);
        });
        weChatDataBase.runInTransaction(() -> {
            weChatDataBase.userDao().insertAUser(user);
        });
    }
}
