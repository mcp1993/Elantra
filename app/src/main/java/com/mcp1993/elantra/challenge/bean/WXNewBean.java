package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class WXNewBean implements Serializable {
    private String reason;
    private WXNewResult result;
    private String error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public WXNewResult getResult() {
        return result;
    }

    public void setResult(WXNewResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
