package com.bill.recreation.utils;

import com.bill.recreation.App;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class DimenUtil {
    public static float dp2px(float dp) {
        final float scale = App.getAppContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(float sp) {
        final float scale = App.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int getScreenSize() {
        return App.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
}
