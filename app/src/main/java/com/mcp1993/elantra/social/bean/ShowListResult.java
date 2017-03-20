package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class ShowListResult implements Serializable {
    private String type;
    private String picture_iphone6;
    private String picture;
    private String web_url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture_iphone6() {
        return picture_iphone6;
    }

    public void setPicture_iphone6(String picture_iphone6) {
        this.picture_iphone6 = picture_iphone6;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
}
