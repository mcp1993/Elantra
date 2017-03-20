package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class ShowList implements Serializable {
    private String ShowList;
    private List<ShowListResult> result = new ArrayList<>();

    public String getShowList() {
        return ShowList;
    }

    public void setShowList(String showList) {
        ShowList = showList;
    }

    public List<ShowListResult> getResult() {
        return result;
    }

    public void setResult(List<ShowListResult> result) {
        this.result = result;
    }
}
