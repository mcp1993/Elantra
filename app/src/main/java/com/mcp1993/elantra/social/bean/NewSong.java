package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class NewSong implements Serializable {
    private String error_code;
    private NewSongResult result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public NewSongResult getResult() {
        return result;
    }

    public void setResult(NewSongResult result) {
        this.result = result;
    }
}
