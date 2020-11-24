package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.report.ReportColumns;

public class ReportSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = ReportSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "report.db";
    private static final int DATABASE_VERSION = 1;
    private static ReportSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final ReportSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_REPORT = "CREATE TABLE IF NOT EXISTS "
            + ReportColumns.TABLE_NAME + " ( "
            + ReportColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReportColumns.CODE + " TEXT, "
            + ReportColumns.NAME + " TEXT, "
            + ReportColumns.DATA_START + " INTEGER, "
            + ReportColumns.DATA_END + " INTEGER, "
            + ReportColumns.LAST_RECE_NUM + " TEXT, "
            + ReportColumns.CURRECT_RECE_NUM + " TEXT, "
            + ReportColumns.RECE_LRR + " TEXT, "
            + ReportColumns.LAST_FORM_NUM + " TEXT, "
            + ReportColumns.CURRECT_FORM_NUM + " TEXT, "
            + ReportColumns.FORM_LRR + " TEXT, "
            + ReportColumns.LAST_FORM_DELAY + " TEXT, "
            + ReportColumns.CURRECT_FORM_DELAY + " TEXT, "
            + ReportColumns.FORM_DELAY_LRR + " TEXT, "
            + ReportColumns.YTD_RECE_NUM + " TEXT, "
            + ReportColumns.RECE_PER + " TEXT, "
            + ReportColumns.FORM_PER + " TEXT, "
            + ReportColumns.AG_NUM + " TEXT, "
            + ReportColumns.BOM_NUM + " TEXT, "
            + ReportColumns.TVM_NUM + " TEXT, "
            + ReportColumns.OTHER_NUM + " TEXT, "
            + ReportColumns.AG_PER + " TEXT, "
            + ReportColumns.BOM_PER + " TEXT, "
            + ReportColumns.TVM_PER + " TEXT, "
            + ReportColumns.OTHER_PER + " TEXT, "
            + ReportColumns.DEVICE_FAULT_NUM + " TEXT, "
            + ReportColumns.DEVICE_FAULT_PER + " TEXT, "
            + ReportColumns.NOT_DEVICE_FAULT_NUM + " TEXT, "
            + ReportColumns.NOT_DEVICE_FAULT_PER + " TEXT "
            + " );";

    // @formatter:on

    public static ReportSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static ReportSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static ReportSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new ReportSQLiteOpenHelper(context);
    }

    private ReportSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new ReportSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static ReportSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new ReportSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ReportSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new ReportSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_REPORT);
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
