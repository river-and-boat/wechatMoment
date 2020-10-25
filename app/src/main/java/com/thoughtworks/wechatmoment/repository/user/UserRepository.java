package com.thoughtworks.wechatmoment.repository.user;

import androidx.lifecycle.LiveData;
import com.thoughtworks.wechatmoment.model.User;

public interface UserRepository {
    User getUserInfo();
    void editUserInfo(User user);
}
