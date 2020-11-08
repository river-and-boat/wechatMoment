package com.thoughtworks.wechatmoment.db.model;

import java.util.List;

public class ChatMoment {

    private String content;
    private List<ContentImage> images;
    private UserSender sender;
    private List<Comment> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ContentImage> getImages() {
        return images;
    }

    public void setImages(List<ContentImage> images) {
        this.images = images;
    }

    public UserSender getSender() {
        return sender;
    }

    public void setSender(UserSender sender) {
        this.sender = sender;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ChatMoment(String content, List<ContentImage> images,
                      UserSender sender, List<Comment> comments) {
        this.content = content;
        this.images = images;
        this.sender = sender;
        this.comments = comments;
    }
}
