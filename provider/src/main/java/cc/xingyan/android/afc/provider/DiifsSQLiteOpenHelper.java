package cc.xingyan.android.afc.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoColumns;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkColumns;

public class DiifsSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = DiifsSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "diifs.db";
    private static final int DATABASE_VERSION = 2;
    private static DiifsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final DiifsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_DIIFS_INFO = "CREATE TABLE IF NOT EXISTS "
            + DiifsInfoColumns.TABLE_NAME + " ( "
            + DiifsInfoColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiifsInfoColumns.USER_ID + " TEXT, "
            + DiifsInfoColumns.WONO + " TEXT, "
            + DiifsInfoColumns.ROUNDDEFID + " TEXT, "
            + DiifsInfoColumns.DESCR + " TEXT, "
            + DiifsInfoColumns.REQUIREDSTARTDATE + " INTEGER, "
            + DiifsInfoColumns.PLANSTARTDATE + " INTEGER, "
            + DiifsInfoColumns.PLANFINISHDATE + " INTEGER, "
            + DiifsInfoColumns.SIGNATURE + " TEXT, "
            + DiifsInfoColumns.SIGN + " TEXT, "
            + DiifsInfoColumns.DEVICECOUNT + " TEXT, "
            + DiifsInfoColumns.UPLOAD_PENDING + " INTEGER, "
            + DiifsInfoColumns.ISCONFIRM + " INTEGER, "
            + DiifsInfoColumns.STATUS + " INTEGER DEFAULT 0 "
            + " );";

    public static final String SQL_CREATE_TABLE_DIIFS_PM = "CREATE TABLE IF NOT EXISTS "
            + DiifsPmColumns.TABLE_NAME + " ( "
            + DiifsPmColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiifsPmColumns.WONO + " TEXT, "
            + DiifsPmColumns.PMNO + " TEXT, "
            + DiifsPmColumns.LOGICNAME + " TEXT, "
            + DiifsPmColumns.PHYSICNAME + " TEXT, "
            + DiifsPmColumns.PHYSICCODE + " TEXT, "
            + DiifsPmColumns.ISDONE + " INTEGER "
            + " );";

    public static final String SQL_CREATE_TABLE_DIIFS_PM_ITEM = "CREATE TABLE IF NOT EXISTS "
            + DiifsPmItemColumns.TABLE_NAME + " ( "
            + DiifsPmItemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiifsPmItemColumns.WONO + " TEXT, "
            + DiifsPmItemColumns.PMNO + " TEXT, "
            + DiifsPmItemColumns.TESTPOINTID + " TEXT, "
            + DiifsPmItemColumns.PARAMETERCODE + " TEXT, "
            + DiifsPmItemColumns.PARAMETERDESC + " TEXT, "
            + DiifsPmItemColumns.PARAMETERTYPE + " TEXT, "
            + DiifsPmItemColumns.UNIT + " TEXT, "
            + DiifsPmItemColumns.MINVALUE + " TEXT, "
            + DiifsPmItemColumns.MAXVALUE + " TEXT, "
            + DiifsPmItemColumns.STARTVALUE + " TEXT, "
            + DiifsPmItemColumns.INTERVAL + " TEXT, "
            + DiifsPmItemColumns.LASTVALUE + " TEXT, "
            + DiifsPmItemColumns.PMGENVALUE + " TEXT, "
            + DiifsPmItemColumns.OBJID + " TEXT, "
            + DiifsPmItemColumns.OBJVERSION + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_DIIFS_WORK = "CREATE TABLE IF NOT EXISTS "
            + DiifsWorkColumns.TABLE_NAME + " ( "
            + DiifsWorkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiifsWorkColumns.WONO + " TEXT, "
            + DiifsWorkColumns.PMNO + " TEXT, "
            + DiifsWorkColumns.TESTPOINTID + " TEXT, "
            + DiifsWorkColumns.PARAMETERCODE + " TEXT, "
            + DiifsWorkColumns.PARAMETERDESC + " TEXT, "
            + DiifsWorkColumns.PARAMETERTYPE + " TEXT, "
            + DiifsWorkColumns.UNIT + " TEXT, "
            + DiifsWorkColumns.MINVALUE + " TEXT, "
            + DiifsWorkColumns.MAXVALUE + " TEXT, "
            + DiifsWorkColumns.STARTVALUE + " TEXT, "
            + DiifsWorkColumns.INTERVAL + " TEXT, "
            + DiifsWorkColumns.LASTVALUE + " TEXT, "
            + DiifsWorkColumns.PMGENVALUE + " TEXT, "
            + DiifsWorkColumns.OBJID + " TEXT, "
            + DiifsWorkColumns.OBJVERSION + " TEXT, "
            + DiifsWorkColumns.ISUPDATE + " INTEGER, "
            + DiifsWorkColumns.PHYSICCODE + " TEXT "
            + " );";

    // @formatter:on

    public static DiifsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static DiifsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static DiifsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new DiifsSQLiteOpenHelper(context);
    }

    private DiifsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new DiifsSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static DiifsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new DiifsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DiifsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new DiifsSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_DIIFS_INFO);
        db.execSQL(SQL_CREATE_TABLE_DIIFS_PM);
        db.execSQL(SQL_CREATE_TABLE_DIIFS_PM_ITEM);
        db.execSQL(SQL_CREATE_TABLE_DIIFS_WORK);
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
