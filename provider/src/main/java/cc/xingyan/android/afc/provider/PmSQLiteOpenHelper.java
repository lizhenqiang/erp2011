package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.pmresultenum.PmResultEnumColumns;
import cc.xingyan.android.afc.provider.pmwork.PmWorkColumns;
import cc.xingyan.android.afc.provider.pmworkitem.PmWorkItemColumns;

public class PmSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PmSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "pm.db";
    private static final int DATABASE_VERSION = 1;
    private static PmSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PmSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_PM_RESULT_ENUM = "CREATE TABLE IF NOT EXISTS "
            + PmResultEnumColumns.TABLE_NAME + " ( "
            + PmResultEnumColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmResultEnumColumns.ITEM_GUID + " TEXT, "
            + PmResultEnumColumns.ORDINAL + " INTEGER, "
            + PmResultEnumColumns.VALUE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PM_WORK = "CREATE TABLE IF NOT EXISTS "
            + PmWorkColumns.TABLE_NAME + " ( "
            + PmWorkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmWorkColumns.USER_ID + " TEXT, "
            + PmWorkColumns.GUID + " TEXT, "
            + PmWorkColumns.JOB_ID + " TEXT, "
            + PmWorkColumns.JOB_DESCRIPTION + " TEXT, "
            + PmWorkColumns.WORK_ID + " TEXT, "
            + PmWorkColumns.WORK_KIND + " TEXT, "
            + PmWorkColumns.DEVICE_CODE + " TEXT, "
            + PmWorkColumns.DEVICE_DESCRIPTION + " TEXT, "
            + PmWorkColumns.EXECUTOR_ID + " TEXT, "
            + PmWorkColumns.EXECUTOR_DESCRIPTION + " TEXT, "
            + PmWorkColumns.START_TIME + " INTEGER, "
            + PmWorkColumns.END_TIME + " INTEGER, "
            + PmWorkColumns.COMPLETE_TIME + " INTEGER, "
            + PmWorkColumns.LAST_MODIFIED + " INTEGER, "
            + PmWorkColumns.STATUS + " INTEGER DEFAULT 0, "
            + PmWorkColumns.UPLOAD_PENDING + " INTEGER "
            + " );";

    public static final String SQL_CREATE_TABLE_PM_WORK_ITEM = "CREATE TABLE IF NOT EXISTS "
            + PmWorkItemColumns.TABLE_NAME + " ( "
            + PmWorkItemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmWorkItemColumns.WORK_GUID + " TEXT, "
            + PmWorkItemColumns.PACKAGE_ID + " TEXT, "
            + PmWorkItemColumns.PACKAGE_DESCRIPTION + " TEXT, "
            + PmWorkItemColumns.GUID + " TEXT, "
            + PmWorkItemColumns.ITEM_ID + " TEXT, "
            + PmWorkItemColumns.DESCRIPTION + " TEXT, "
            + PmWorkItemColumns.ORDINAL + " INTEGER, "
            + PmWorkItemColumns.RESULT_TYPE + " TEXT, "
            + PmWorkItemColumns.RESULT_MIN_VALUE + " INTEGER, "
            + PmWorkItemColumns.RESULT_MAX_VALUE + " INTEGER, "
            + PmWorkItemColumns.RESULT_DEFAULT_VALUE + " TEXT, "
            + PmWorkItemColumns.RESULT_VALUE_UNIT + " TEXT, "
            + PmWorkItemColumns.RESULT_ENUM_ORDINAL + " INTEGER, "
            + PmWorkItemColumns.RESULT_VALUE + " TEXT, "
            + PmWorkItemColumns.LAST_MODIFIED + " INTEGER, "
            + PmWorkItemColumns.REQUIRED + " INTEGER "
            + " );";

    // @formatter:on

    public static PmSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PmSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PmSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PmSQLiteOpenHelper(context);
    }

    private PmSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PmSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PmSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PmSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PmSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PmSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_PM_RESULT_ENUM);
        db.execSQL(SQL_CREATE_TABLE_PM_WORK);
        db.execSQL(SQL_CREATE_TABLE_PM_WORK_ITEM);
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
