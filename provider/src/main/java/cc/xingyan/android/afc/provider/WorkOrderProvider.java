package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.device.DeviceColumns;
import cc.xingyan.android.afc.provider.devicephysics.DevicePhysicsColumns;
import cc.xingyan.android.afc.provider.paramvalue.ParamValueColumns;
import cc.xingyan.android.afc.provider.user.UserColumns;
import cc.xingyan.android.afc.provider.workorder.WorkOrderColumns;

public class WorkOrderProvider extends BaseContentProvider {
    private static final String TAG = WorkOrderProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("true");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.work_order";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_DEVICE = 0;
    private static final int URI_TYPE_DEVICE_ID = 1;

    private static final int URI_TYPE_DEVICE_PHYSICS = 2;
    private static final int URI_TYPE_DEVICE_PHYSICS_ID = 3;

    private static final int URI_TYPE_PARAM_VALUE = 4;
    private static final int URI_TYPE_PARAM_VALUE_ID = 5;

    private static final int URI_TYPE_USER = 6;
    private static final int URI_TYPE_USER_ID = 7;

    private static final int URI_TYPE_WORK_ORDER = 8;
    private static final int URI_TYPE_WORK_ORDER_ID = 9;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, DeviceColumns.TABLE_NAME, URI_TYPE_DEVICE);
        URI_MATCHER.addURI(AUTHORITY, DeviceColumns.TABLE_NAME + "/#", URI_TYPE_DEVICE_ID);
        URI_MATCHER.addURI(AUTHORITY, DevicePhysicsColumns.TABLE_NAME, URI_TYPE_DEVICE_PHYSICS);
        URI_MATCHER.addURI(AUTHORITY, DevicePhysicsColumns.TABLE_NAME + "/#", URI_TYPE_DEVICE_PHYSICS_ID);
        URI_MATCHER.addURI(AUTHORITY, ParamValueColumns.TABLE_NAME, URI_TYPE_PARAM_VALUE);
        URI_MATCHER.addURI(AUTHORITY, ParamValueColumns.TABLE_NAME + "/#", URI_TYPE_PARAM_VALUE_ID);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME, URI_TYPE_USER);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME + "/#", URI_TYPE_USER_ID);
        URI_MATCHER.addURI(AUTHORITY, WorkOrderColumns.TABLE_NAME, URI_TYPE_WORK_ORDER);
        URI_MATCHER.addURI(AUTHORITY, WorkOrderColumns.TABLE_NAME + "/#", URI_TYPE_WORK_ORDER_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return WorkOrderSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_DEVICE:
                return TYPE_CURSOR_DIR + DeviceColumns.TABLE_NAME;
            case URI_TYPE_DEVICE_ID:
                return TYPE_CURSOR_ITEM + DeviceColumns.TABLE_NAME;

            case URI_TYPE_DEVICE_PHYSICS:
                return TYPE_CURSOR_DIR + DevicePhysicsColumns.TABLE_NAME;
            case URI_TYPE_DEVICE_PHYSICS_ID:
                return TYPE_CURSOR_ITEM + DevicePhysicsColumns.TABLE_NAME;

            case URI_TYPE_PARAM_VALUE:
                return TYPE_CURSOR_DIR + ParamValueColumns.TABLE_NAME;
            case URI_TYPE_PARAM_VALUE_ID:
                return TYPE_CURSOR_ITEM + ParamValueColumns.TABLE_NAME;

            case URI_TYPE_USER:
                return TYPE_CURSOR_DIR + UserColumns.TABLE_NAME;
            case URI_TYPE_USER_ID:
                return TYPE_CURSOR_ITEM + UserColumns.TABLE_NAME;

            case URI_TYPE_WORK_ORDER:
                return TYPE_CURSOR_DIR + WorkOrderColumns.TABLE_NAME;
            case URI_TYPE_WORK_ORDER_ID:
                return TYPE_CURSOR_ITEM + WorkOrderColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_DEVICE:
            case URI_TYPE_DEVICE_ID:
                res.table = DeviceColumns.TABLE_NAME;
                res.idColumn = DeviceColumns._ID;
                res.tablesWithJoins = DeviceColumns.TABLE_NAME;
                res.orderBy = DeviceColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DEVICE_PHYSICS:
            case URI_TYPE_DEVICE_PHYSICS_ID:
                res.table = DevicePhysicsColumns.TABLE_NAME;
                res.idColumn = DevicePhysicsColumns._ID;
                res.tablesWithJoins = DevicePhysicsColumns.TABLE_NAME;
                res.orderBy = DevicePhysicsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PARAM_VALUE:
            case URI_TYPE_PARAM_VALUE_ID:
                res.table = ParamValueColumns.TABLE_NAME;
                res.idColumn = ParamValueColumns._ID;
                res.tablesWithJoins = ParamValueColumns.TABLE_NAME;
                res.orderBy = ParamValueColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_USER:
            case URI_TYPE_USER_ID:
                res.table = UserColumns.TABLE_NAME;
                res.idColumn = UserColumns._ID;
                res.tablesWithJoins = UserColumns.TABLE_NAME;
                res.orderBy = UserColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_WORK_ORDER:
            case URI_TYPE_WORK_ORDER_ID:
                res.table = WorkOrderColumns.TABLE_NAME;
                res.idColumn = WorkOrderColumns._ID;
                res.tablesWithJoins = WorkOrderColumns.TABLE_NAME;
                res.orderBy = WorkOrderColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_DEVICE_ID:
            case URI_TYPE_DEVICE_PHYSICS_ID:
            case URI_TYPE_PARAM_VALUE_ID:
            case URI_TYPE_USER_ID:
            case URI_TYPE_WORK_ORDER_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
