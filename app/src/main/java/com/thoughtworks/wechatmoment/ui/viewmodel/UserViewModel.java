package com.thoughtworks.wechatmoment.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoment.WeChatApplication;
import com.thoughtworks.wechatmoment.db.WeChatDataBase;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;
import com.thoughtworks.wechatmoment.db.model.User;
import com.thoughtworks.wechatmoment.db.repository.DataRepository;
import com.thoughtworks.wechatmoment.db.repository.user.UserLocalImp;
import com.thoughtworks.wechatmoment.db.repository.user.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private static MutableLiveData<UserEntity> userInfo;
    private static MutableLiveData<Boolean> infoIsUpdating;
    private DataRepository dataRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((WeChatApplication) application).getDataRepository();
    }

    public void init() {
        if (userInfo != null) {
            return;
        }
        userInfo = new MutableLiveData<>();
        userInfo.setValue(dataRepository.getUserInfo());
    }

    public LiveData<Boolean> getUserIsUpdating() {
        return infoIsUpdating;
    }

    public LiveData<UserEntity> getUserInfo() {
        return userInfo;
    }

    public void editUserInfo(UserEntity user) {
        dataRepository.updateUserInfo(user);
        // todo: flowable和compleble替换
        userInfo.setValue(dataRepository.getUserInfo());
    }
}
