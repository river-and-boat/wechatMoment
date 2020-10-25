package com.thoughtworks.wechatmoment.repository.chatmoment;

import android.os.Message;

import com.thoughtworks.wechatmoment.model.ChatMoment;
import com.thoughtworks.wechatmoment.model.ContentImage;
import com.thoughtworks.wechatmoment.model.UserSender;
import com.thoughtworks.wechatmoment.viewmodel.WeChatItemViewModel.SimulationFetchResource;

import java.util.ArrayList;
import java.util.List;

public class ChatMomentLocalImp implements ChatMomentRepository {

    private static final String TEST_URL = "https://timgsa.baidu.com/timg?image&quality=" +
            "80&size=b9999_10000&sec=1603627963276&di=1f3e3378bcf5142169e536968b0b5105&imgtype=0" +
            "&src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202008%2F08" +
            "%2F20200808135140_THdwT.thumb.400_0.jpeg";

    private static List<ChatMoment> chatMoments;
    private static ChatMomentLocalImp chatMomentLocalImp;
    private SimulationFetchResource simulationFetchResource;

    static {
        chatMoments = new ArrayList<>();
        UserSender userSender = new UserSender(TEST_URL,
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

    private ChatMomentLocalImp() {
    }

    public static ChatMomentLocalImp getInstance() {
        if (chatMomentLocalImp == null) {
            chatMomentLocalImp = new ChatMomentLocalImp();
        }
        return chatMomentLocalImp;
    }

    @Override
    public List<ChatMoment> getChatMomentList() {
        return chatMoments;
    }

    @Override
    public void addNewChatMoment() {
        // 模拟新用户
        UserSender userSender = new UserSender(TEST_URL,
                "jport", "Joe Portman");
        String content = "今天真是开心的一天啊，这是一个新的测试用户!";
        List<ContentImage> images = new ArrayList<>();
        images.add(new ContentImage("https://encrypted-tbn1.gstatic.com/images?q=tbn" +
                ":ANd9GcRDy7HZaHxn15wWj6pXE4uMKAqHTC_uBgBlIzeeQSj2QaGgUzUmHg"));
        images.add(new ContentImage("https://encrypted-tbn1.gstatic.com/images?" +
                "q=tbn:ANd9GcTlJRALAf-76JPOLohBKzBg8Ab4Q5pWeQhF5igSfBflE_UYbqu7"));
        images.add(new ContentImage("http://i.ytimg.com/vi/rGWI7mjmnNk/hqdefault.jpg"));

        ChatMoment chatMoment = new ChatMoment(content, images, userSender, null);
            if (simulationFetchResource == null) {
                simulationFetchResource = new SimulationFetchResource();
            }

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    chatMoments.add(chatMoment);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = chatMoments;
                    simulationFetchResource.handleMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }
}
