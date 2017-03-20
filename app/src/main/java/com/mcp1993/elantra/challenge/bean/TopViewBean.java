package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class TopViewBean implements Serializable{
    private String reason;
    private TopViewResult result;
    private String error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TopViewResult getResult() {
        return result;
    }

    public void setResult(TopViewResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
