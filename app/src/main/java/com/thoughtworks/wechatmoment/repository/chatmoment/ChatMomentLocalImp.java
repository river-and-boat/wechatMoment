package com.thoughtworks.wechatmoment.repository.chatmoment;

import com.thoughtworks.wechatmoment.model.ChatMoment;
import com.thoughtworks.wechatmoment.model.ContentImage;
import com.thoughtworks.wechatmoment.model.UserSender;

import java.util.ArrayList;
import java.util.List;

public class ChatMomentLocalImp implements ChatMomentRepository {

    private static List<ChatMoment> chatMoments;
    private ChatMomentLocalImp chatMomentLocalImp;

    static {
        chatMoments = new ArrayList<>();
        UserSender userSender = new UserSender("https://encrypted-tbn3.gstatic.com" +
                "/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w",
                "jport", "Joe Portman");
        String content = "沙发!";
        List<ContentImage> images = new ArrayList<>();
        images.add(new ContentImage("https://encrypted-tbn1.gstatic.com/images?q=tbn" +
                ":ANd9GcRDy7HZaHxn15wWj6pXE4uMKAqHTC_uBgBlIzeeQSj2QaGgUzUmHg"));
        images.add(new ContentImage("https://encrypted-tbn1.gstatic.com/images?" +
                "q=tbn:ANd9GcTlJRALAf-76JPOLohBKzBg8Ab4Q5pWeQhF5igSfBflE_UYbqu7"));
        images.add(new ContentImage("http://i.ytimg.com/vi/rGWI7mjmnNk/hqdefault.jpg"));

        chatMoments.add(new ChatMoment(content, images, userSender, null));
    }

    private ChatMomentLocalImp() {}

    public ChatMomentLocalImp getInstance() {
        if (chatMomentLocalImp == null) {
            chatMomentLocalImp = new ChatMomentLocalImp();
        }
        return chatMomentLocalImp;
    }

    @Override
    public List<ChatMoment> getChatMomentList() {
        return chatMoments;
    }
}
