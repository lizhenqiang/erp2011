package cc.xingyan.android.afc.util;

import android.util.Log;

/**
 * @ClassName: LogUtil
 * @Description: 打印Log工具类
 * @author: YuJiadeng
 * @date: 2013-7-2 上午9:14:34
 */
public class LogUtil {
	private static final boolean ERROR = true;
	private static final boolean DEBUG = true;
	public  static final boolean INFO = true;

	public static void debug(String tag, String m) {
		if(DEBUG)
			Log.d(tag, m);
	}

	public static void debug(String tag, String msg, Throwable tr) {
		if(DEBUG)
			Log.d(tag, msg, tr);
	}

	public static void info(String tag, String m) {
		if(INFO)
			Log.i(tag, m);
	}

	public static void error(String tag, String m) {
		if(ERROR)
			Log.e(tag, m);
	}

	public static void error(String tag, String m, Throwable tr) {
		if(ERROR)
			Log.e(tag, m, tr);
	}
}
