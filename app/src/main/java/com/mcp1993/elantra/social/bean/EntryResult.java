package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class EntryResult implements Serializable {
    private String day;
    private String title;
    private String icon;
    private String jump;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }
}
