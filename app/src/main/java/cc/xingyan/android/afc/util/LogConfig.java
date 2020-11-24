package cc.xingyan.android.afc.util;

import android.util.Log;

import cc.xingyan.android.afc.BuildConfig;

/**
 * Created by San on 11/11/15.
 */
public interface LogConfig {
    default boolean willLog(String tag) {
        return BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG);
    }
}
