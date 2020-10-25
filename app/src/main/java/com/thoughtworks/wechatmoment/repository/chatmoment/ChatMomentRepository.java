package com.thoughtworks.wechatmoment.repository.chatmoment;

import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.wechatmoment.model.ChatMoment;
import java.util.List;

public interface ChatMomentRepository {
    List<ChatMoment> getChatMomentList();
    void addNewChatMoment();
}
