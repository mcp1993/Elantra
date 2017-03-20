package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class King implements Serializable {
    private String King;
    private List<KingResult> result = new ArrayList<>();

    public String getKing() {
        return King;
    }

    public void setKing(String king) {
        King = king;
    }

    public List<KingResult> getResult() {
        return result;
    }

    public void setResult(List<KingResult> result) {
        this.result = result;
    }
}
