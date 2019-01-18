package com.zhenl.violet.media;

import com.zhenl.violet.R;

public class ToolBarOptions {
    /**
     * toolbar的title资源id
     */
    public int titleId;
    /**
     * toolbar的title
     */
    public String titleString;
    /**
     * toolbar的logo资源id
     */
    public int logoId;
    /**
     * toolbar的返回按钮资源id
     */
    public int navigateId;
    /**
     * toolbar的返回按钮
     */
    public boolean isNeedNavigate;

    public ToolBarOptions() {
        navigateId = R.drawable.nim_actionbar_dark_back_icon;
        isNeedNavigate = true;
    }
}
