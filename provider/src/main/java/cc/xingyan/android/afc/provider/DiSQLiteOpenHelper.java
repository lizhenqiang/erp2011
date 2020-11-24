package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.diwork.DiWorkColumns;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemColumns;

public class DiSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = DiSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "di.db";
    private static final int DATABASE_VERSION = 1;
    private static DiSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final DiSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_DI_WORK = "CREATE TABLE IF NOT EXISTS "
            + DiWorkColumns.TABLE_NAME + " ( "
            + DiWorkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiWorkColumns.WORK_AREA_ID + " TEXT, "
            + DiWorkColumns.WORK_AREA_DESCRIPTION + " TEXT, "
            + DiWorkColumns.GUID + " TEXT, "
            + DiWorkColumns.WORK_ID + " TEXT, "
            + DiWorkColumns.DATE + " INTEGER, "
            + DiWorkColumns.START_TIME + " INTEGER, "
            + DiWorkColumns.END_TIME + " INTEGER, "
            + DiWorkColumns.COMPLETE_TIME + " INTEGER, "
            + DiWorkColumns.LAST_MODIFIED + " INTEGER, "
            + DiWorkColumns.STATUS + " INTEGER DEFAULT 0, "
            + DiWorkColumns.UPLOAD_PENDING + " INTEGER, "
            + DiWorkColumns.OPERATOR + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_DI_WORK_ITEM = "CREATE TABLE IF NOT EXISTS "
            + DiWorkItemColumns.TABLE_NAME + " ( "
            + DiWorkItemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiWorkItemColumns.WORK_GUID + " TEXT, "
            + DiWorkItemColumns.STATION_ID + " TEXT, "
            + DiWorkItemColumns.STATION_DESCRIPTION + " TEXT, "
            + DiWorkItemColumns.DEVICE_ID + " TEXT, "
            + DiWorkItemColumns.DEVICE_DESCRIPTION + " TEXT, "
            + DiWorkItemColumns.DEVICE_SYSTEM + " TEXT, "
            + DiWorkItemColumns.GUID + " TEXT, "
            + DiWorkItemColumns.RESULT_ORDINAL + " INTEGER, "
            + DiWorkItemColumns.RESULT_VALUE + " TEXT, "
            + DiWorkItemColumns.LAST_MODIFIED + " INTEGER "
            + " );";

    // @formatter:on

    public static DiSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static DiSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static DiSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new DiSQLiteOpenHelper(context);
    }

    private DiSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new DiSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static DiSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new DiSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DiSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new DiSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_DI_WORK);
        db.execSQL(SQL_CREATE_TABLE_DI_WORK_ITEM);
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
