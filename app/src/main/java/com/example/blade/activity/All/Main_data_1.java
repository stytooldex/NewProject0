package com.example.blade.activity.All;

public class Main_data_1 {
    private String title;
    private String content;
    private String imgUrl;

    public Main_data_1(String s, String s1, String s2) {
        title = s;
        content = s1;
        imgUrl = s2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
