package com.example.pcc.doan2_v1.model;

/**
 * Created by pcc on 17/10/2017.
 */

public class Chude {
    private String id;
    private String tenchude;

    public Chude(String id, String tenchude) {
        this.id = id;
        this.tenchude = tenchude;
    }

    public Chude() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }
}
