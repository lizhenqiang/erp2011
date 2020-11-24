package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadColumns;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiColumns;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineColumns;

public class YunshuSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = YunshuSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "yunshu.db";
    private static final int DATABASE_VERSION = 1;
    private static YunshuSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final YunshuSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_YUNSHU_HEAD = "CREATE TABLE IF NOT EXISTS "
            + YunshuHeadColumns.TABLE_NAME + " ( "
            + YunshuHeadColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + YunshuHeadColumns.TRANSPORT_TASK_ID + " TEXT, "
            + YunshuHeadColumns.TRANSPORT_TASK_STATE + " TEXT, "
            + YunshuHeadColumns.CREATE_DATE + " INTEGER, "
            + YunshuHeadColumns.MAINTENANCE_TYPE_ID + " TEXT, "
            + YunshuHeadColumns.PLAN_BY + " TEXT, "
            + YunshuHeadColumns.PACK_NUMBER + " TEXT, "
            + YunshuHeadColumns.SEND_WAREHOUSE_NO + " TEXT, "
            + YunshuHeadColumns.RECEIVE_WAREHOUSE_NO + " TEXT, "
            + YunshuHeadColumns.TRANSPORT_TASK_TYPE + " TEXT, "
            + YunshuHeadColumns.KEEP_COL1 + " TEXT, "
            + YunshuHeadColumns.KEEP_COL2 + " TEXT, "
            + YunshuHeadColumns.KEEP_COL3 + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_YUNSHU_KUWEI = "CREATE TABLE IF NOT EXISTS "
            + YunshuKuweiColumns.TABLE_NAME + " ( "
            + YunshuKuweiColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + YunshuKuweiColumns.TRANSPORT_TASK_ID + " TEXT, "
            + YunshuKuweiColumns.WAREHOUSE_NO + " TEXT, "
            + YunshuKuweiColumns.WAREHOUSE_NAME + " TEXT, "
            + YunshuKuweiColumns.WAREHOUSE_TYPE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_YUNSHU_LINE = "CREATE TABLE IF NOT EXISTS "
            + YunshuLineColumns.TABLE_NAME + " ( "
            + YunshuLineColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + YunshuLineColumns.TRANSPORT_TASK_ID + " TEXT, "
            + YunshuLineColumns.LINE_NO + " TEXT, "
            + YunshuLineColumns.PART_NO + " TEXT, "
            + YunshuLineColumns.PART_NAME + " TEXT, "
            + YunshuLineColumns.QUANTITY + " TEXT, "
            + YunshuLineColumns.UNIT + " TEXT, "
            + YunshuLineColumns.LOT_BATCH_NO + " TEXT, "
            + YunshuLineColumns.SERIAL_NO + " TEXT, "
            + YunshuLineColumns.FORMAT + " TEXT, "
            + YunshuLineColumns.LINE_TYPE + " TEXT, "
            + YunshuLineColumns.LINE_MARK + " INTEGER, "
            + YunshuLineColumns.KEEP_COL1 + " TEXT, "
            + YunshuLineColumns.KEEP_COL2 + " TEXT "
            + " );";

    // @formatter:on

    public static YunshuSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static YunshuSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static YunshuSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new YunshuSQLiteOpenHelper(context);
    }

    private YunshuSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new YunshuSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static YunshuSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new YunshuSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private YunshuSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new YunshuSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_YUNSHU_HEAD);
        db.execSQL(SQL_CREATE_TABLE_YUNSHU_KUWEI);
        db.execSQL(SQL_CREATE_TABLE_YUNSHU_LINE);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
