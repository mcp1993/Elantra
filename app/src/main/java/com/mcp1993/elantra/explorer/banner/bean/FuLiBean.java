package com.mcp1993.elantra.explorer.banner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class FuLiBean implements Serializable {
    private boolean error;
    private List<FuLiResult> results = new ArrayList<>();

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<FuLiResult> getResults() {
        return results;
    }

    public void setResults(List<FuLiResult> results) {
        this.results = results;
    }
}
