package com.thoughtworks.wechatmoment.db.repository;

import com.thoughtworks.wechatmoment.db.entity.ChatMomentEntity;
import com.thoughtworks.wechatmoment.db.entity.CommentEntity;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataGenerator {

    private static final String AVATAR = "https://timgsa.baidu.com/timg?image&quality=80&size" +
            "=b9999_10000&sec=1603627963275&di=bb933e1839e25ffb2a049bc9e98ec4ac&imgtype=0&src=http%" +
            "3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F18%2F20200718121409_liuku" +
            ".thumb.400_0.jpeg";

    private static final String PROFILE = "https://timgsa.baidu.com/timg?image&quality=" +
            "80&size=b9999_10000&sec=1603641537272&di=d4cd54ba6338aa894e8a47e8fed8a304&imgtype=" +
            "0&src=http%3A%2F%2Fp2.itc.cn%2Fimages01%2F20200918%2F9d10cf01fadc4d6c828197c3" +
            "adaa17fe.jpeg";

    private static final String USER_NAME = "JSmith";

    private static final String[] CONTENTS = new String[]{
            "下周开始要加班了咋办啊", "爱你穿越时间，两行来自秋末的眼泪",
            "白色的风车，静静的转着", "想唤醒被遗弃的爱情，雪花已铺满了地",
            "缓缓飘落的枫叶像思恋，我点燃烛火燃烧岁末的秋天"
    };

    private static final String[] NAMES = new String[]{
            "江雨舟", "张进进", "黛珂宇", "饶花样", "周恩"
    };

    private static final String[] AVATARS = new String[] {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604835122388&di=" +
                    "c78ed8ce1a797037034c6a77539b6583&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%" +
                    "2Fuploads%2Fitem%2F201409%2F11%2F20140911211243_3rT4u.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604835122388&di=" +
                    "78d32c0e0a8ef4b4cfba0c83fbf739ed&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%" +
                    "2Fuploads%2Fitem%2F201911%2F03%2F20191103115047_LXnKu.thumb.400_0.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604835122387&di=" +
                    "3bb7d2293f11f8c7cab089a1000610d3&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%" +
                    "2Fuploads%2Fitem%2F202009%2F24%2F20200924201441_xdhsp.thumb.400_0.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604835122386&di=" +
                    "8aeda162ba9d5b772c7a06fdc6d147fb&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%" +
                    "2Fuploads%2Fblog%2F202008%2F08%2F20200808142612_nsopf.thumb.400_0.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604835122386&di=" +
                    "01a949853bd1c5e367a8fbe06efd19da&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%" +
                    "2Fuploads%2Fitem%2F202005%2F11%2F20200511141839_NUsHG.thumb.400_0.jpeg"
    };

    public static List<ChatMomentEntity> getChatMoments() {
        List<ChatMomentEntity> chatMoments = new ArrayList<>();
        for (int i = 0; i < CONTENTS.length; i++) {
            ChatMomentEntity chatMoment = new ChatMomentEntity(CONTENTS[i], AVATARS[i],
                    NAMES[i], NAMES[i], new Date());
            chatMoment.setChatId(String.valueOf(i));
            chatMoments.add(chatMoment);
        }
        return chatMoments;
    }

    public static List<CommentEntity> getComments(List<ChatMomentEntity> chatMoments) {
        List<CommentEntity> comments = new ArrayList<>();
        for (int i = 0; i < chatMoments.size(); i++) {
            CommentEntity comment = new CommentEntity(CONTENTS[i], AVATARS[i], NAMES[i], NAMES[i],
                    chatMoments.get(i).getChatId(), new Date());
            comment.setId(i);
            comments.add(comment);
        }
        return comments;
    }

    public static UserEntity getUser() {
        return new UserEntity(PROFILE, AVATAR, USER_NAME, USER_NAME);
    }
}
