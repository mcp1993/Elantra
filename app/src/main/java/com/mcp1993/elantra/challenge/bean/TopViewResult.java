package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class TopViewResult implements Serializable{
    private String title;
    private String tag;
    private String act;
    private String year;
    private String rating;
    private String area;
    private String dir;
    private String desc;
    private String cover;
    private String vdo_status;
    private PlayLink playlinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVdo_status() {
        return vdo_status;
    }

    public void setVdo_status(String vdo_status) {
        this.vdo_status = vdo_status;
    }

    public PlayLink getPlaylinks() {
        return playlinks;
    }

    public void setPlaylinks(PlayLink playlinks) {
        this.playlinks = playlinks;
    }
}
