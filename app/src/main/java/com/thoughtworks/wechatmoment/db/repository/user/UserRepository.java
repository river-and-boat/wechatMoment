package com.thoughtworks.wechatmoment.db.repository.user;

import com.thoughtworks.wechatmoment.db.model.User;

public interface UserRepository {
    User getUserInfo();
    User editUserInfo(User user);
}
