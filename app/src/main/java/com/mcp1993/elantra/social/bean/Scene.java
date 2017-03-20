package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class Scene implements Serializable{
    private ScenceResult result;
    private String error_code;
    private List<Config> config = new ArrayList<>();

    public ScenceResult getResult() {
        return result;
    }

    public void setResult(ScenceResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<Config> getConfig() {
        return config;
    }

    public void setConfig(List<Config> config) {
        this.config = config;
    }
}
