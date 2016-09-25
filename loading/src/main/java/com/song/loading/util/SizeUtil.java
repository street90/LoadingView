package com.song.loading.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by song on 2016/9/25.
 */

public class SizeUtil {

    /**
     * dp 转换成px
     * @param context
     * @param dp
     * @return
     */
    public static float Dp2Px(Context context, int dp)
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转换成px
     * @param context
     * @param sp
     * @return
     */
    public static float Sp2Px(Context context, int sp)
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }


    /**
     * px转换成sp
     * @param context
     * @param px
     * @return
     */
    public static float Px2Dp(Context context, int px)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return px/scale + 0.5f;
    }


    public static float Px2Sp(Context context, int px)
    {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scale + 0.5f;
    }

}
