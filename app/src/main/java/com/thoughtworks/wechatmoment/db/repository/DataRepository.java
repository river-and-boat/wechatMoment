package com.thoughtworks.wechatmoment.db.repository;

import android.os.AsyncTask;

import com.thoughtworks.wechatmoment.db.WeChatDataBase;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public List<ChatMomentEntity> getChatMoments() {
        return weChatDataBase.chatMomentDao().loadChatMoments();
    }

    public void addChatMoment(ChatMomentEntity chatMomentEntity) {
        weChatDataBase.chatMomentDao().insertACharMoment(chatMomentEntity);
    }

    public List<CommentEntity> getComments(int chatMomentId) {
        return weChatDataBase.commentDao().loadComments(chatMomentId);
    }

    public void addComment(CommentEntity commentEntity) {
        weChatDataBase.commentDao().insertAComments(commentEntity);
    }

    public void updateUserInfo(UserEntity userEntity) {
        weChatDataBase.userDao().updateUserInfo(userEntity);
    }

    public UserEntity getUserInfo() {
        return weChatDataBase.userDao().getUser();
    }
}
