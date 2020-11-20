package com.thoughtworks.wechatmoment.ui.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.wechatmoment.WeChatApplication;
import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.repository.DataRepository;

import java.util.Date;
import java.util.List;

public class WeChatItemViewModel extends AndroidViewModel {

    private static MutableLiveData<List<ChatMomentEntity>> chatMoments;
    private static MutableLiveData<Boolean> itemIsUpdating = new MutableLiveData<>();
    private DataRepository dataRepository;

    public void init() {
        if (chatMoments != null) {
            return;
        }
        chatMoments = new MutableLiveData<>();
        chatMoments.setValue(dataRepository.getChatMoments());
    }

    public WeChatItemViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((WeChatApplication) application).getDataRepository();
    }

    public LiveData<Boolean> getItemIsUpdating() {
        return itemIsUpdating;
    }

    public LiveData<List<ChatMomentEntity>> getChatMomentList() {
        return chatMoments;
    }

    public void addNewItems() {
        itemIsUpdating.setValue(true);
        dataRepository.addChatMoment(new ChatMomentEntity("content", "avatar",
                "username", "nick", new Date()));
    }

    public static class SimulationFetchResource extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    // 模拟刷新朋友圈
                    chatMoments.postValue((List<ChatMomentEntity>) msg.obj);
                    itemIsUpdating.postValue(false);
                    break;
                default:
                    break;
            }
        }
    }
}
