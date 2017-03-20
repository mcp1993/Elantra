package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class DiyResult implements Serializable {
    private String position;
    private String tag;

    private String pic;
    private String title;
    private String collectnum;
    private String type;
    private int listenum;
    private String listid;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(String collectnum) {
        this.collectnum = collectnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getListenum() {
        return listenum;
    }

    public void setListenum(int listenum) {
        this.listenum = listenum;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }
}
