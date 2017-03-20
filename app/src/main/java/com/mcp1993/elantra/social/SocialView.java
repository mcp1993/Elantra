package com.mcp1993.elantra.social;

import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.social.bean.MusicBean;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public interface SocialView {
    void showBanner(FuLiBean bean);
    void showMySocialView(Challenge_horBean bean);
    void showSocialView(MusicBean bean);

}
