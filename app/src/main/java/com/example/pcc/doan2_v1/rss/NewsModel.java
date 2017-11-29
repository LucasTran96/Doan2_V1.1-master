package com.example.pcc.doan2_v1.rss;

/**
 * Created by pcc on 01/10/2017.
 */

public class NewsModel {
    private String title;
    private String image;
    private String link;

    public NewsModel() {
    }

    public NewsModel(String title, String image, String link) {
        this.title = title;
        this.image = image;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
