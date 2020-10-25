package com.thoughtworks.wechatmoment.viewmodel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoment.model.ChatMoment;
import com.thoughtworks.wechatmoment.repository.chatmoment.ChatMomentLocalImp;
import com.thoughtworks.wechatmoment.repository.chatmoment.ChatMomentRepository;

import java.util.List;

public class WeChatItemViewModel extends ViewModel {

    private static MutableLiveData<List<ChatMoment>> chatMoments;
    private static MutableLiveData<Boolean> itemIsUpdating = new MutableLiveData<>();
    private ChatMomentRepository chatMomentRepository;

    public void init() {
        if (chatMoments != null) {
            return;
        }
        chatMomentRepository = ChatMomentLocalImp.getInstance();
        chatMoments = new MutableLiveData<>();
        chatMoments.setValue(chatMomentRepository.getChatMomentList());
    }

    public LiveData<Boolean> getIsUpdating() {
        return this.itemIsUpdating;
    }

    public LiveData<List<ChatMoment>> getChatMomentList() {
        return this.chatMoments;
    }

    public void addNewItems() {
        itemIsUpdating.setValue(true);
        chatMomentRepository.addNewChatMoment();
    }

    public static class SimulationFetchResource extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    // 模拟刷新朋友圈
                    chatMoments.postValue((List<ChatMoment>) msg.obj);
                    itemIsUpdating.postValue(false);
                    break;
                default:
                    break;
            }
        }
    }
}
