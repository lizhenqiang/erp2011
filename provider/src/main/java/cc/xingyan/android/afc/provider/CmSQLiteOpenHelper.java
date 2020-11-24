package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;

public class CmSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = CmSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "cm.db";
    private static final int DATABASE_VERSION = 8;
    private static CmSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final CmSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CM_MATERIAL = "CREATE TABLE IF NOT EXISTS "
            + CmMaterialColumns.TABLE_NAME + " ( "
            + CmMaterialColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CmMaterialColumns.CM_ORDER_ID + " TEXT, "
            + CmMaterialColumns.MATERIAL_ORDER_ID + " TEXT, "
            + CmMaterialColumns.USER + " TEXT, "
            + CmMaterialColumns.DEPARTMENT + " TEXT, "
            + CmMaterialColumns.INT_CUSTOMER_NO + " TEXT, "
            + CmMaterialColumns.ENTER_DATE + " INTEGER, "
            + CmMaterialColumns.DUE_DATE + " INTEGER, "
            + CmMaterialColumns.GUID + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_CM_MATERIAL_ROW = "CREATE TABLE IF NOT EXISTS "
            + CmMaterialRowColumns.TABLE_NAME + " ( "
            + CmMaterialRowColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CmMaterialRowColumns.CM_ORDER_ID + " TEXT, "
            + CmMaterialRowColumns.MATERIAL_ORDER_ID + " TEXT, "
            + CmMaterialRowColumns.MATERIAL_ROW + " TEXT, "
            + CmMaterialRowColumns.MATERIAL_ID + " TEXT, "
            + CmMaterialRowColumns.MATERIAL_DESCRIPTION + " TEXT, "
            + CmMaterialRowColumns.DUE_DATE + " INTEGER, "
            + CmMaterialRowColumns.MATERIAL_COUNT + " INTEGER, "
            + CmMaterialRowColumns.GUID + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_CM_PARAM_MATERIALS = "CREATE TABLE IF NOT EXISTS "
            + CmParamMaterialsColumns.TABLE_NAME + " ( "
            + CmParamMaterialsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CmParamMaterialsColumns.CODE + " TEXT, "
            + CmParamMaterialsColumns.NAME + " TEXT, "
            + CmParamMaterialsColumns.LINE + " TEXT, "
            + CmParamMaterialsColumns.DEVICE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_CM_SPARE_PARTS = "CREATE TABLE IF NOT EXISTS "
            + CmSparePartsColumns.TABLE_NAME + " ( "
            + CmSparePartsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CmSparePartsColumns.CM_WORK_ID + " TEXT, "
            + CmSparePartsColumns.ORDER_ID + " TEXT, "
            + CmSparePartsColumns.PART_ID + " TEXT, "
            + CmSparePartsColumns.PART_DESCRIPTION + " TEXT, "
            + CmSparePartsColumns.APPLY_DATE + " INTEGER, "
            + CmSparePartsColumns.INSTALL_DATE + " INTEGER, "
            + CmSparePartsColumns.OLD_PART_SN + " TEXT, "
            + CmSparePartsColumns.NEW_PART_SN + " TEXT, "
            + CmSparePartsColumns.SPARE_PART_STATUS + " INTEGER "
            + " );";

    public static final String SQL_CREATE_TABLE_CM_WORK = "CREATE TABLE IF NOT EXISTS "
            + CmWorkColumns.TABLE_NAME + " ( "
            + CmWorkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CmWorkColumns.USER_ID + " TEXT, "
            + CmWorkColumns.GUID + " TEXT, "
            + CmWorkColumns.ORDER_ID + " TEXT, "
            + CmWorkColumns.PRIORITY + " TEXT, "
            + CmWorkColumns.DEVICE_CODE + " TEXT, "
            + CmWorkColumns.DEVICE_NAME + " TEXT, "
            + CmWorkColumns.FAULT_DESCRIPTION_TEXT + " TEXT, "
            + CmWorkColumns.FAULT_DESCRIPTION_CODE + " TEXT, "
            + CmWorkColumns.FAULT_NOTE + " TEXT, "
            + CmWorkColumns.REPORTER_TYPE_TEXT + " TEXT, "
            + CmWorkColumns.REPORTER_TYPE_CODE + " TEXT, "
            + CmWorkColumns.INSTRUCT + " TEXT, "
            + CmWorkColumns.REPORTER + " TEXT, "
            + CmWorkColumns.PREPARE_MAN + " TEXT, "
            + CmWorkColumns.DISPOSE + " TEXT, "
            + CmWorkColumns.WORKAREA + " TEXT, "
            + CmWorkColumns.FAULT_START_TIME + " INTEGER, "
            + CmWorkColumns.APPLY_S_TIME + " INTEGER, "
            + CmWorkColumns.APPLY_F_TIME + " INTEGER, "
            + CmWorkColumns.PLAN_S_TIME + " INTEGER, "
            + CmWorkColumns.PLAN_F_TIME + " INTEGER, "
            + CmWorkColumns.FAULT_GRADE_TEXT + " TEXT, "
            + CmWorkColumns.FAULT_GRADE_CODE + " TEXT, "
            + CmWorkColumns.FAULT_TYPE_TEXT + " TEXT, "
            + CmWorkColumns.FAULT_TYPE_CODE + " TEXT, "
            + CmWorkColumns.FAULT_CAUSE_TEXT + " TEXT, "
            + CmWorkColumns.FAULT_CAUSE_CODE + " TEXT, "
            + CmWorkColumns.WORK_DETAILS_TEXT + " TEXT, "
            + CmWorkColumns.WORK_DETAILS_CODE + " TEXT, "
            + CmWorkColumns.WORK_DONE_TEXT + " TEXT, "
            + CmWorkColumns.WORK_DONE_CODE + " TEXT, "
            + CmWorkColumns.FAULT_CAUSE_NOTE + " TEXT, "
            + CmWorkColumns.WORK_NOTE + " TEXT, "
            + CmWorkColumns.OPERATOR_TEXT + " TEXT, "
            + CmWorkColumns.OPERATOR_CODE + " TEXT, "
            + CmWorkColumns.OPERATION_START_TIME + " INTEGER, "
            + CmWorkColumns.OPERATION_END_TIME + " INTEGER, "
            + CmWorkColumns.ORDER_RECEIVE_TIME + " INTEGER, "
            + CmWorkColumns.ARRIVE_TIME + " INTEGER, "
            + CmWorkColumns.INT_CUSTOMER_NO + " TEXT, "
            + CmWorkColumns.FORM_FLAG + " TEXT, "
            + CmWorkColumns.EQUIP_FAULT + " TEXT, "
            + CmWorkColumns.STATUS_BACKSTAGE + " TEXT, "
            + CmWorkColumns.STATUS + " INTEGER DEFAULT 0, "
            + CmWorkColumns.LAST_MODIFIED + " INTEGER, "
            + CmWorkColumns.DELETE_PENDING + " INTEGER DEFAULT 0, "
            + CmWorkColumns.UPLOAD_PENDING + " INTEGER "
            + " );";

    // @formatter:on

    public static CmSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static CmSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static CmSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new CmSQLiteOpenHelper(context);
    }

    private CmSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new CmSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static CmSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new CmSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private CmSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new CmSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CM_MATERIAL);
        db.execSQL(SQL_CREATE_TABLE_CM_MATERIAL_ROW);
        db.execSQL(SQL_CREATE_TABLE_CM_PARAM_MATERIALS);
        db.execSQL(SQL_CREATE_TABLE_CM_SPARE_PARTS);
        db.execSQL(SQL_CREATE_TABLE_CM_WORK);
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
