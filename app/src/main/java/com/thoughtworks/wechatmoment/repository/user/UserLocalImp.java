package com.thoughtworks.wechatmoment.repository.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.wechatmoment.model.User;

public class UserLocalImp implements UserRepository {

    private static User user;
    private UserLocalImp userLocalImp;

    static {
        user = new User("http://img2.findthebest.com/sites/default/files" +
                "/688/media/images/Mingle_159902_i0.png", "http://info.thoughtworks.com" +
                "/rs/thoughtworks2/images/glyph_badge.png", "jsmith", "John Smith");
    }

    private UserLocalImp() {}

    public UserLocalImp getInstance() {
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
    public void editUserInfo(User user) {
        if (user != null) {
            UserLocalImp.user = user;
        }
    }
}
