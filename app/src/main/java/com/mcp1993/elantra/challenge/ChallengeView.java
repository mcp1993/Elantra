package com.mcp1993.elantra.challenge;

import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.TopViewBean;
import com.mcp1993.elantra.challenge.bean.WXNewBean;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public interface ChallengeView {
    void showHorView(Challenge_horBean bean);
    void showTopView(TopViewBean bean);
    void showWXNewView(WXNewBean bean);
    void showSport(Challenge_horBean bean);
}
