package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class WXNewResult implements Serializable{
    private List<WXNewList> list =new ArrayList<>();
    private int totalPage;
    private int ps;
    private int pno;

    public List<WXNewList> getList() {
        return list;
    }

    public void setList(List<WXNewList> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
}
