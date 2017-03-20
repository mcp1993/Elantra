package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class Challenge_horResult implements Serializable {
    private String stat;
    private List<Challenge_horData> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Challenge_horData> getData() {
        return data;
    }

    public void setData(List<Challenge_horData> data) {
        this.data = data;
    }
}
