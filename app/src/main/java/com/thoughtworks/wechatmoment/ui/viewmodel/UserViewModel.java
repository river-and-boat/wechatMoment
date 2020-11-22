package com.thoughtworks.wechatmoment.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.wechatmoment.WeChatApplication;
import com.thoughtworks.wechatmoment.db.entity.UserEntity;
import com.thoughtworks.wechatmoment.db.repository.DataRepository;

import io.reactivex.Flowable;

public class UserViewModel extends AndroidViewModel {

    private static MutableLiveData<Boolean> infoIsUpdating;
    private DataRepository dataRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((WeChatApplication) application).getDataRepository();
    }

    public LiveData<Boolean> getUserIsUpdating() {
        return infoIsUpdating;
    }

    public Flowable<UserEntity> getUserInfo() {
        return dataRepository.getUserInfo();
    }

    public void editUserInfo(UserEntity user) {
        dataRepository.updateUserInfo(user);
    }
}
