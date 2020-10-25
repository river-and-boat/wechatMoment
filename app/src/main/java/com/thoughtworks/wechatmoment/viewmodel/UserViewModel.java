package com.thoughtworks.wechatmoment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoment.model.User;

public class UserViewModel extends ViewModel {
    private static MutableLiveData<User> userInfo;
    private static MutableLiveData<Boolean> infoIsUpdating;

    public void init() {
        if (userInfo != null) {
            return;
        }

    }
}
