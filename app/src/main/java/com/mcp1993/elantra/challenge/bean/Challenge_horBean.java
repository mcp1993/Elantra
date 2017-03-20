package com.mcp1993.elantra.challenge.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class Challenge_horBean implements Serializable{
    private String reason;
    private Challenge_horResult result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Challenge_horResult getResult() {
        return result;
    }

    public void setResult(Challenge_horResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
