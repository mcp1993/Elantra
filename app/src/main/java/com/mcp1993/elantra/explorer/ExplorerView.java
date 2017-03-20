package com.mcp1993.elantra.explorer;

import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public interface ExplorerView {
    void showBanner(Challenge_horBean bean);
    void showItemView(FuLiBean bean);
}
