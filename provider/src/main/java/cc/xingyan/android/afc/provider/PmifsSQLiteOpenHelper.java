package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemColumns;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowColumns;
import cc.xingyan.android.afc.provider.pmparammaterials.PmParamMaterialsColumns;

public class PmifsSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PmifsSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "pmifs.db";
    private static final int DATABASE_VERSION = 3;
    private static PmifsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PmifsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_PM_MATERIAL = "CREATE TABLE IF NOT EXISTS "
            + PmMaterialColumns.TABLE_NAME + " ( "
            + PmMaterialColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmMaterialColumns.PM_ORDER_ID + " TEXT, "
            + PmMaterialColumns.MATERIAL_ORDER_ID + " TEXT, "
            + PmMaterialColumns.USER + " TEXT, "
            + PmMaterialColumns.DEPARTMENT + " TEXT, "
            + PmMaterialColumns.INT_CUSTOMER_NO + " TEXT, "
            + PmMaterialColumns.ENTER_DATE + " INTEGER, "
            + PmMaterialColumns.DUE_DATE + " INTEGER, "
            + PmMaterialColumns.GUID + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PM_MATERIAL_ROW = "CREATE TABLE IF NOT EXISTS "
            + PmMaterialRowColumns.TABLE_NAME + " ( "
            + PmMaterialRowColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmMaterialRowColumns.PM_ORDER_ID + " TEXT, "
            + PmMaterialRowColumns.MATERIAL_ORDER_ID + " TEXT, "
            + PmMaterialRowColumns.MATERIAL_ROW + " TEXT, "
            + PmMaterialRowColumns.MATERIAL_ID + " TEXT, "
            + PmMaterialRowColumns.MATERIAL_DESCRIPTION + " TEXT, "
            + PmMaterialRowColumns.DUE_DATE + " INTEGER, "
            + PmMaterialRowColumns.MATERIAL_COUNT + " INTEGER, "
            + PmMaterialRowColumns.GUID + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PM_PARAM_MATERIALS = "CREATE TABLE IF NOT EXISTS "
            + PmParamMaterialsColumns.TABLE_NAME + " ( "
            + PmParamMaterialsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmParamMaterialsColumns.CODE + " TEXT, "
            + PmParamMaterialsColumns.NAME + " TEXT, "
            + PmParamMaterialsColumns.LINE + " TEXT, "
            + PmParamMaterialsColumns.DEVICE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PMIFS_RESULT_ENUM = "CREATE TABLE IF NOT EXISTS "
            + PmifsResultEnumColumns.TABLE_NAME + " ( "
            + PmifsResultEnumColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmifsResultEnumColumns.ITEM_GUID + " TEXT, "
            + PmifsResultEnumColumns.SN + " INTEGER, "
            + PmifsResultEnumColumns.VALUE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_PMIFS_WORK = "CREATE TABLE IF NOT EXISTS "
            + PmifsWorkColumns.TABLE_NAME + " ( "
            + PmifsWorkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmifsWorkColumns.USER_ID + " TEXT, "
            + PmifsWorkColumns.GUID + " TEXT, "
            + PmifsWorkColumns.ORDER_ID + " TEXT, "
            + PmifsWorkColumns.PRIORITY_TEXT + " TEXT, "
            + PmifsWorkColumns.PRIORITY_CODE + " TEXT, "
            + PmifsWorkColumns.DEVICE_CODE + " TEXT, "
            + PmifsWorkColumns.DEVICE_NAME + " TEXT, "
            + PmifsWorkColumns.DEVICE_LOGIC_CODE + " TEXT, "
            + PmifsWorkColumns.DEVICE_LOGIC_NAME + " TEXT, "
            + PmifsWorkColumns.INSTRUCT + " TEXT, "
            + PmifsWorkColumns.PREPARE_MAN_CODE + " TEXT, "
            + PmifsWorkColumns.PREPARE_MAN_TEXT + " TEXT, "
            + PmifsWorkColumns.WORKAREA_TEXT + " TEXT, "
            + PmifsWorkColumns.WORKAREA_CODE + " TEXT, "
            + PmifsWorkColumns.APPLY_S_TIME + " INTEGER, "
            + PmifsWorkColumns.APPLY_F_TIME + " INTEGER, "
            + PmifsWorkColumns.PLAN_S_TIME + " INTEGER, "
            + PmifsWorkColumns.PLAN_F_TIME + " INTEGER, "
            + PmifsWorkColumns.WORK_DETAILS_TEXT + " TEXT, "
            + PmifsWorkColumns.WORK_DETAILS_CODE + " TEXT, "
            + PmifsWorkColumns.WORK_DONE_TEXT + " TEXT, "
            + PmifsWorkColumns.WORK_DONE_CODE + " TEXT, "
            + PmifsWorkColumns.WORK_NOTE + " TEXT, "
            + PmifsWorkColumns.OPERATOR_TEXT + " TEXT, "
            + PmifsWorkColumns.OPERATOR_CODE + " TEXT, "
            + PmifsWorkColumns.OPERATION_START_TIME + " INTEGER, "
            + PmifsWorkColumns.OPERATION_END_TIME + " INTEGER, "
            + PmifsWorkColumns.ORDER_RECEIVE_TIME + " INTEGER, "
            + PmifsWorkColumns.ARRIVE_TIME + " INTEGER, "
            + PmifsWorkColumns.INT_CUSTOMER_NO + " TEXT, "
            + PmifsWorkColumns.STATUS_BACKSTAGE + " TEXT, "
            + PmifsWorkColumns.STATUS + " INTEGER DEFAULT 0, "
            + PmifsWorkColumns.LAST_MODIFIED + " INTEGER, "
            + PmifsWorkColumns.DELETE_PENDING + " INTEGER DEFAULT 0, "
            + PmifsWorkColumns.WORK_TYPE_ID + " TEXT, "
            + PmifsWorkColumns.UPLOAD_PENDING + " INTEGER "
            + " );";

    public static final String SQL_CREATE_TABLE_PMIFS_WORK_ITEM = "CREATE TABLE IF NOT EXISTS "
            + PmifsWorkItemColumns.TABLE_NAME + " ( "
            + PmifsWorkItemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PmifsWorkItemColumns.ORDER_ID + " TEXT, "
            + PmifsWorkItemColumns.WORK_GUID + " TEXT, "
            + PmifsWorkItemColumns.PACKAGE_ID + " TEXT, "
            + PmifsWorkItemColumns.PACKAGE_DES + " TEXT, "
            + PmifsWorkItemColumns.GUID + " TEXT, "
            + PmifsWorkItemColumns.ITEM_ID + " TEXT, "
            + PmifsWorkItemColumns.ITEM_DES + " TEXT, "
            + PmifsWorkItemColumns.WORK_SN + " INTEGER, "
            + PmifsWorkItemColumns.RESULT_TYPE + " TEXT, "
            + PmifsWorkItemColumns.RESULT_MIN_VALUE + " INTEGER, "
            + PmifsWorkItemColumns.RESULT_MAX_VALUE + " INTEGER, "
            + PmifsWorkItemColumns.RESULT_DEFAULT_VALUE + " TEXT, "
            + PmifsWorkItemColumns.RESULT_VALUE_UNIT + " TEXT, "
            + PmifsWorkItemColumns.RESULT_ENUM_ORDINAL + " INTEGER, "
            + PmifsWorkItemColumns.RESULT_VALUE + " TEXT, "
            + PmifsWorkItemColumns.LAST_MODIFIED + " INTEGER, "
            + PmifsWorkItemColumns.REQUIRED + " INTEGER "
            + " );";

    // @formatter:on

    public static PmifsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PmifsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PmifsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PmifsSQLiteOpenHelper(context);
    }

    private PmifsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PmifsSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PmifsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PmifsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PmifsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PmifsSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_PM_MATERIAL);
        db.execSQL(SQL_CREATE_TABLE_PM_MATERIAL_ROW);
        db.execSQL(SQL_CREATE_TABLE_PM_PARAM_MATERIALS);
        db.execSQL(SQL_CREATE_TABLE_PMIFS_RESULT_ENUM);
        db.execSQL(SQL_CREATE_TABLE_PMIFS_WORK);
        db.execSQL(SQL_CREATE_TABLE_PMIFS_WORK_ITEM);
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
