package com.thoughtworks.wechatmoment.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoment.db.model.User;
import com.thoughtworks.wechatmoment.db.repository.user.UserLocalImp;
import com.thoughtworks.wechatmoment.db.repository.user.UserRepository;

public class UserViewModel extends ViewModel {
    private static MutableLiveData<User> userInfo;
    private static MutableLiveData<Boolean> infoIsUpdating;
    private UserRepository userRepository;

    public void init() {
        if (userInfo != null) {
            return;
        }
        userRepository = UserLocalImp.getInstance();
        userInfo = new MutableLiveData<>();
        userInfo.setValue(userRepository.getUserInfo());
    }

    public LiveData<Boolean> getUserIsUpdating() {
        return infoIsUpdating;
    }

    public LiveData<User> getUserInfo() {
        return userInfo;
    }

    public void editUserInfo(User user) {
        User editedUser = userRepository.editUserInfo(user);
        userInfo.setValue(editedUser);
    }
}
