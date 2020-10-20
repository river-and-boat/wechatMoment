package com.thoughtworks.wechatmoment.viewmodel;


import androidx.lifecycle.ViewModel;

public class WeChatItemViewModel extends ViewModel {

    private String username;

    private String content;

    public WeChatItemViewModel(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
