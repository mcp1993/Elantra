package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class MIX_9 implements Serializable {
    private String error_code;
    private List<MIXResult> result = new ArrayList<>();

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<MIXResult> getResult() {
        return result;
    }

    public void setResult(List<MIXResult> result) {
        this.result = result;
    }
}
