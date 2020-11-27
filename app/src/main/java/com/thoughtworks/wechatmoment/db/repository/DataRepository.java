package com.thoughtworks.wechatmoment.db.repository;

import com.thoughtworks.wechatmoment.db.WeChatDataBase;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DataRepository {

    private static DataRepository dataRepository;

    private WeChatDataBase weChatDataBase;

    private DataRepository(final WeChatDataBase weChatDataBase) {
        this.weChatDataBase = weChatDataBase;
    }

    public static DataRepository getInstance(final WeChatDataBase weChatDataBase) {
        if (dataRepository == null) {
            synchronized (DataRepository.class) {
                if (dataRepository == null) {
                    dataRepository = new DataRepository(weChatDataBase);
                }
            }
        }
        return dataRepository;
    }

    public Flowable<List<ChatMomentEntity>> getChatMoments() {
        return weChatDataBase.chatMomentDao().loadChatMoments();
    }

    public Completable addChatMoment(ChatMomentEntity chatMomentEntity) {
        return weChatDataBase.chatMomentDao().insertACharMoment(chatMomentEntity);
    }

    public Flowable<List<CommentEntity>> getComments(String chatMomentId) {
        return weChatDataBase.commentDao().loadComments(chatMomentId);
    }

    public Completable addComment(CommentEntity commentEntity) {
        return weChatDataBase.commentDao().insertAComment(commentEntity);
    }

    public Completable updateUserInfo(UserEntity userEntity) {
        return weChatDataBase.userDao().updateUserInfo(userEntity);
    }

    public Flowable<UserEntity> getUserInfo() {
        return weChatDataBase.userDao().getUser();
    }
}
