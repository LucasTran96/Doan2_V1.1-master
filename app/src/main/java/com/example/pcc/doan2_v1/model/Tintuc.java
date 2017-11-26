package com.example.pcc.doan2_v1.model;

import java.io.Serializable;

/**
 * Created by pcc on 15/10/2017.
 */

public class Tintuc implements Serializable{
    private String url_image;
    private String title;
    private String description;
    private String content;
    private String time_post;
    private int active; //0: private, 1: public

    public Tintuc(String url_image, String title, String description, String content, String time_post, int active, int yeuthich, String chude) {
        this.url_image = url_image;
        this.title = title;
        this.description = description;
        this.content = content;
        this.time_post = time_post;
        this.active = active;
        this.yeuthich = yeuthich;
        this.chude = chude;
    }

    public int getYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(int yeuthich) {
        this.yeuthich = yeuthich;
    }

    private int yeuthich;//0: chưa like, 1: đã like

    private String chude;

    public Tintuc() {
    }


    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime_post() {
        return time_post;
    }

    public void setTime_post(String time_post) {
        this.time_post = time_post;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    public String getChude() {
        return chude;
    }

    public void setChude(String chude) {
        this.chude = chude;
    }

}
