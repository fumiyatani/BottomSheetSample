package jp.androidbook.bottomsheetsample;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {
    /**
     * dpからpixelへの変換
     * @param dp
     * @param context
     * @return float dp
     */
    public static float convertDp2Px(float dp, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return dp * metrics.density;
    }
}
