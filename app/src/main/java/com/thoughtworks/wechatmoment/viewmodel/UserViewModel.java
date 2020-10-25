package com.thoughtworks.wechatmoment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoment.model.User;
import com.thoughtworks.wechatmoment.repository.user.UserLocalImp;
import com.thoughtworks.wechatmoment.repository.user.UserRepository;

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
        userRepository.editUserInfo(user);
    }
}
