package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class NewSongResult implements Serializable {
    private String pic_500;
    private String listid;
    private List<NewSongResultInfo> song_info = new ArrayList<>();

    public String getPic_500() {
        return pic_500;
    }

    public void setPic_500(String pic_500) {
        this.pic_500 = pic_500;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public List<NewSongResultInfo> getSong_info() {
        return song_info;
    }

    public void setSong_info(List<NewSongResultInfo> song_info) {
        this.song_info = song_info;
    }
}
