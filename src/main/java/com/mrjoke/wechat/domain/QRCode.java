package com.mrjoke.wechat.domain;

public class QRCode {
    private int expire_seconds;
    private String action_name;
    private String action_info;
    private long scene_id;
    private String scene_str;

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getAction_info() {
        return action_info;
    }

    public void setAction_info(String action_info) {
        this.action_info = action_info;
    }

    public long getScene_id() {
        return scene_id;
    }

    public void setScene_id(long scene_id) {
        this.scene_id = scene_id;
    }

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }
}
