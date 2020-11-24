package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.user.UserColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;

public class WorkOrderSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = WorkOrderSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "work_order.db";
    private static final int DATABASE_VERSION = 2;
    private static WorkOrderSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final WorkOrderSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_DEVICE = "CREATE TABLE IF NOT EXISTS "
            + DeviceColumns.TABLE_NAME + " ( "
            + DeviceColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DeviceColumns.CODE + " TEXT, "
            + DeviceColumns.NAME + " TEXT, "
            + DeviceColumns.TYPE + " TEXT, "
            + DeviceColumns.LOCATION + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_DEVICE_PHYSICS = "CREATE TABLE IF NOT EXISTS "
            + DevicePhysicsColumns.TABLE_NAME + " ( "
            + DevicePhysicsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DevicePhysicsColumns.CODE + " TEXT, "
            + DevicePhysicsColumns.CODE_PHYSICS + " TEXT, "
            + DevicePhysicsColumns.NAME + " TEXT, "
            + DevicePhysicsColumns.TYPE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PARAM_VALUE = "CREATE TABLE IF NOT EXISTS "
            + ParamValueColumns.TABLE_NAME + " ( "
            + ParamValueColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ParamValueColumns.CODE + " TEXT, "
            + ParamValueColumns.VALUE + " TEXT, "
            + ParamValueColumns.TYPE + " TEXT, "
            + ParamValueColumns.PARENT_CODE + " TEXT, "
            + ParamValueColumns.PARENT_TYPE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + UserColumns.TABLE_NAME + " ( "
            + UserColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserColumns.ORG_CODE + " TEXT, "
            + UserColumns.USER_ID + " TEXT, "
            + UserColumns.USER_NO + " TEXT, "
            + UserColumns.USER_NAME + " TEXT, "
            + UserColumns.PASSWORD_MD5 + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_WORK_ORDER = "CREATE TABLE IF NOT EXISTS "
            + WorkOrderColumns.TABLE_NAME + " ( "
            + WorkOrderColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkOrderColumns.USER_ID + " TEXT, "
            + WorkOrderColumns.GUID + " TEXT, "
            + WorkOrderColumns.NO + " TEXT, "
            + WorkOrderColumns.DEVICE_CODE + " TEXT, "
            + WorkOrderColumns.DEVICE_NAME + " TEXT, "
            + WorkOrderColumns.FAULT_DESCRIPTION_TEXT + " TEXT, "
            + WorkOrderColumns.FAULT_DESCRIPTION_CODE + " TEXT, "
            + WorkOrderColumns.FAULT_TYPE_TEXT + " TEXT, "
            + WorkOrderColumns.FAULT_TYPE_CODE + " TEXT, "
            + WorkOrderColumns.REPORTER_TYPE_TEXT + " TEXT, "
            + WorkOrderColumns.REPORTER_TYPE_CODE + " TEXT, "
            + WorkOrderColumns.REPORTER + " TEXT, "
            + WorkOrderColumns.FAULT_NOTE + " TEXT, "
            + WorkOrderColumns.FAULT_CAUSE_TEXT + " TEXT, "
            + WorkOrderColumns.FAULT_CAUSE_CODE + " TEXT, "
            + WorkOrderColumns.FAULT_START_TIME + " INTEGER, "
            + WorkOrderColumns.OPERATION_TEXT + " TEXT, "
            + WorkOrderColumns.OPERATION_CODE + " TEXT, "
            + WorkOrderColumns.OPERATION_RESULT_TEXT + " TEXT, "
            + WorkOrderColumns.OPERATION_RESULT_CODE + " TEXT, "
            + WorkOrderColumns.OPERATION_NOTE + " TEXT, "
            + WorkOrderColumns.OPERATOR_TEXT + " TEXT, "
            + WorkOrderColumns.OPERATOR_CODE + " TEXT, "
            + WorkOrderColumns.OPERATION_START_TIME + " INTEGER, "
            + WorkOrderColumns.OPERATION_END_TIME + " INTEGER, "
            + WorkOrderColumns.FORM_FLAG_TEXT + " TEXT, "
            + WorkOrderColumns.FORM_FLAG_CODE + " TEXT, "
            + WorkOrderColumns.SYNC_STATUS + " INTEGER DEFAULT 0, "
            + WorkOrderColumns.LAST_MODIFIED + " INTEGER, "
            + WorkOrderColumns.DELETE_PENDING + " INTEGER DEFAULT 0, "
            + WorkOrderColumns.UPLOAD_PENDING + " INTEGER "
            + " );";

    // @formatter:on

    public static WorkOrderSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static WorkOrderSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static WorkOrderSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new WorkOrderSQLiteOpenHelper(context);
    }

    private WorkOrderSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new WorkOrderSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static WorkOrderSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new WorkOrderSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private WorkOrderSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new WorkOrderSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_DEVICE);
        db.execSQL(SQL_CREATE_TABLE_DEVICE_PHYSICS);
        db.execSQL(SQL_CREATE_TABLE_PARAM_VALUE);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_WORK_ORDER);
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
