package com.thoughtworks.wechatmoment.repository.user;

import com.thoughtworks.wechatmoment.model.User;

public class UserLocalImp implements UserRepository {

    private static User user;
    private static UserLocalImp userLocalImp;

    private static final String TEST_AVATAR_URL = "https://timgsa.baidu.com/timg?image&quality=80&size" +
            "=b9999_10000&sec=1603627963275&di=bb933e1839e25ffb2a049bc9e98ec4ac&imgtype=0&src=http%" +
            "3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F18%2F20200718121409_liuku" +
            ".thumb.400_0.jpeg";

    private static final String TEST_PROFILE_IMAGE = "https://timgsa.baidu.com/timg?image&quality=" +
            "80&size=b9999_10000&sec=1603641537272&di=d4cd54ba6338aa894e8a47e8fed8a304&imgtype=" +
            "0&src=http%3A%2F%2Fp2.itc.cn%2Fimages01%2F20200918%2F9d10cf01fadc4d6c828197c3" +
            "adaa17fe.jpeg";

    static {
        user = new User(TEST_PROFILE_IMAGE, TEST_AVATAR_URL, "jsmith", "John Smith");
    }

    private UserLocalImp() {}

    public static UserLocalImp getInstance() {
        if (userLocalImp == null) {
            userLocalImp = new UserLocalImp();
        }
        return userLocalImp;
    }

    @Override
    public User getUserInfo() {
        return UserLocalImp.user;
    }

    @Override
    public User editUserInfo(User user) {
        if (user != null) {
            UserLocalImp.user = user;
        }
        return UserLocalImp.user;
    }
}
