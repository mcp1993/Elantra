package com.mcp1993.elantra.social.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class ScenceResult implements Serializable {
    private List<ScenceResultAction> action = new ArrayList();
    private List<ScenceResultEmotion> emotion= new ArrayList();
    private List<ScenceResultOperation> operation= new ArrayList();
    private List<ScenceResultOther> other= new ArrayList();

    public List<ScenceResultAction> getAction() {
        return action;
    }

    public void setAction(List<ScenceResultAction> action) {
        this.action = action;
    }

    public List<ScenceResultEmotion> getEmotion() {
        return emotion;
    }

    public void setEmotion(List<ScenceResultEmotion> emotion) {
        this.emotion = emotion;
    }

    public List<ScenceResultOperation> getOperation() {
        return operation;
    }

    public void setOperation(List<ScenceResultOperation> operation) {
        this.operation = operation;
    }

    public List<ScenceResultOther> getOther() {
        return other;
    }

    public void setOther(List<ScenceResultOther> other) {
        this.other = other;
    }
}
