package com.zzc.bustongzhi.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Administrator on 2015/8/3.
 */
public class VibratorUtil {
    public static void Vibrate(final Context activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }
    public static void Vibrate(final Context activity, long[] pattern,boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }
}
