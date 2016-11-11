package com.bill.recreation.utils;

import android.os.SystemClock;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class ClickUtil {

    private static long mLastClickTime = 0;
    private static final int SPACE_TIME = 500;

    public static boolean isFastDoubleClick() {
        long time = SystemClock.elapsedRealtime();
        if (time - mLastClickTime <= SPACE_TIME) {
            return true;
        } else {
            mLastClickTime = time;
            return false;
        }
    }
}
