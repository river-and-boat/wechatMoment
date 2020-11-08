package com.thoughtworks.wechatmoment;

import android.app.Application;

import com.thoughtworks.wechatmoment.db.WeChatDataBase;
import com.thoughtworks.wechatmoment.db.repository.DataRepository;

public class WeChatApplication extends Application {

    private AppExecutors appExecutors;

    private WeChatDataBase weChatDataBase;

    private DataRepository dataRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors = new AppExecutors();
        weChatDataBase = WeChatDataBase.getInstance(this, appExecutors);
        dataRepository =
    }
}
