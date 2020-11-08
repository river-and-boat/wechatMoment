package com.thoughtworks.wechatmoment.db.repository.chatmoment;

import com.thoughtworks.wechatmoment.db.model.ChatMoment;
import java.util.List;

public interface ChatMomentRepository {
    List<ChatMoment> getChatMomentList();
    void addNewChatMoment();
}
