package net.winnerawan.madiun.utils;

import android.content.Context;

public class DimentionUtils {

    private DimentionUtils() {
    }

    public static float converPixelsToSp(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().scaledDensity;
    }
}
