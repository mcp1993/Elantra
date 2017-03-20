package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class Config implements Serializable {
    private String color_other;
    private String play_color;
    private int scene_version;
    private String desc;
    private String end_time;
    private String start_time;
    private String scene_color;
    private String bgpic;
    private String bgpic_special;
    private String button_color;

    public String getColor_other() {
        return color_other;
    }

    public void setColor_other(String color_other) {
        this.color_other = color_other;
    }

    public String getPlay_color() {
        return play_color;
    }

    public void setPlay_color(String play_color) {
        this.play_color = play_color;
    }

    public int getScene_version() {
        return scene_version;
    }

    public void setScene_version(int scene_version) {
        this.scene_version = scene_version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getScene_color() {
        return scene_color;
    }

    public void setScene_color(String scene_color) {
        this.scene_color = scene_color;
    }

    public String getBgpic() {
        return bgpic;
    }

    public void setBgpic(String bgpic) {
        this.bgpic = bgpic;
    }

    public String getBgpic_special() {
        return bgpic_special;
    }

    public void setBgpic_special(String bgpic_special) {
        this.bgpic_special = bgpic_special;
    }

    public String getButton_color() {
        return button_color;
    }

    public void setButton_color(String button_color) {
        this.button_color = button_color;
    }
}
