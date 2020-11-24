package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.pankureport.PankuReportColumns;

public class KucunjianSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = KucunjianSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "kucunjian.db";
    private static final int DATABASE_VERSION = 1;
    private static KucunjianSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final KucunjianSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_PANKU_REPORT = "CREATE TABLE IF NOT EXISTS "
            + PankuReportColumns.TABLE_NAME + " ( "
            + PankuReportColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PankuReportColumns.USER_ID + " TEXT, "
            + PankuReportColumns.REPORT_NO + " TEXT, "
            + PankuReportColumns.PART_NO + " TEXT, "
            + PankuReportColumns.PART_NAME + " TEXT, "
            + PankuReportColumns.ACTUAL_AMOUNT + " TEXT, "
            + PankuReportColumns.LOTBATCH_NO + " TEXT, "
            + PankuReportColumns.LINE_NO + " TEXT, "
            + PankuReportColumns.PART_SEQ + " TEXT, "
            + PankuReportColumns.WAREHOUSE_NO + " TEXT, "
            + PankuReportColumns.WAREHOUSE_NAME + " TEXT, "
            + PankuReportColumns.PANDIAN_MARK + " TEXT, "
            + PankuReportColumns.PANDIAN_TIME + " INTEGER "
            + " );";

    // @formatter:on

    public static KucunjianSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static KucunjianSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static KucunjianSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new KucunjianSQLiteOpenHelper(context);
    }

    private KucunjianSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new KucunjianSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static KucunjianSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new KucunjianSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private KucunjianSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new KucunjianSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_PANKU_REPORT);
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
