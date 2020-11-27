package com.thoughtworks.wechatmoment.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.thoughtworks.wechatmoment.WeChatApplication;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.db.repository.DataRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class CommentViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((WeChatApplication) application).getDataRepository();
    }

    public Completable addComment(CommentEntity comment) {
        return dataRepository.addComment(comment);
    }

    public Flowable<List<CommentEntity>> getComments(String chatMomentId) {
        return dataRepository.getComments(chatMomentId);
    }
}
