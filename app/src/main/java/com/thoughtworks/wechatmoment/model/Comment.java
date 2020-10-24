package com.thoughtworks.wechatmoment.model;

public class Comment {

    private String content;
    private UserSender sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserSender getSender() {
        return sender;
    }

    public void setSender(UserSender sender) {
        this.sender = sender;
    }
}
