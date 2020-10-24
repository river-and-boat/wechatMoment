package com.thoughtworks.wechatmoment.repository.chatmoment;

import com.thoughtworks.wechatmoment.model.ChatMoment;
import java.util.List;

public interface ChatMomentRepository {
    List<ChatMoment> getChatMomentList();
}
