package com.example.pcc.doan2_v1.model;

/**
 * Created by Admin on 11/16/2017.
 */

public class BinhLuan {
    private String id;
    private String username;
    private String noidungbinhluan;


    public String getUsername() {
        return username;
    }

    public BinhLuan() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNoidungbinhluan() {
        return noidungbinhluan;
    }

    public void setNoidungbinhluan(String noidungbinhluan) {
        this.noidungbinhluan = noidungbinhluan;
    }

    public BinhLuan(String id,String username, String noidungbinhluan) {
        this.username = username;
        this.noidungbinhluan = noidungbinhluan;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
