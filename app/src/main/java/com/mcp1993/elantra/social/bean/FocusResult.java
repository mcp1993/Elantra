package com.mcp1993.elantra.social.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class FocusResult implements Serializable {
    private String is_publish;
    private String code;
    private String type;
    private String randpic_desc;
    private String mo_type;
    private String randpic_iphone6;
    private String randpic;

    public String getIs_publish() {
        return is_publish;
    }

    public void setIs_publish(String is_publish) {
        this.is_publish = is_publish;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRandpic_desc() {
        return randpic_desc;
    }

    public void setRandpic_desc(String randpic_desc) {
        this.randpic_desc = randpic_desc;
    }

    public String getMo_type() {
        return mo_type;
    }

    public void setMo_type(String mo_type) {
        this.mo_type = mo_type;
    }

    public String getRandpic_iphone6() {
        return randpic_iphone6;
    }

    public void setRandpic_iphone6(String randpic_iphone6) {
        this.randpic_iphone6 = randpic_iphone6;
    }

    public String getRandpic() {
        return randpic;
    }

    public void setRandpic(String randpic) {
        this.randpic = randpic;
    }
}
