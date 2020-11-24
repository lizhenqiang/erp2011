package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.pmresultenum.PmResultEnumColumns;
import cc.xingyan.android.afc.provider.pmwork.PmWorkColumns;
import cc.xingyan.android.afc.provider.pmworkitem.PmWorkItemColumns;

public class PmProvider extends BaseContentProvider {
    private static final String TAG = PmProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("true");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.pm";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_PM_RESULT_ENUM = 0;
    private static final int URI_TYPE_PM_RESULT_ENUM_ID = 1;

    private static final int URI_TYPE_PM_WORK = 2;
    private static final int URI_TYPE_PM_WORK_ID = 3;

    private static final int URI_TYPE_PM_WORK_ITEM = 4;
    private static final int URI_TYPE_PM_WORK_ITEM_ID = 5;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, PmResultEnumColumns.TABLE_NAME, URI_TYPE_PM_RESULT_ENUM);
        URI_MATCHER.addURI(AUTHORITY, PmResultEnumColumns.TABLE_NAME + "/#", URI_TYPE_PM_RESULT_ENUM_ID);
        URI_MATCHER.addURI(AUTHORITY, PmWorkColumns.TABLE_NAME, URI_TYPE_PM_WORK);
        URI_MATCHER.addURI(AUTHORITY, PmWorkColumns.TABLE_NAME + "/#", URI_TYPE_PM_WORK_ID);
        URI_MATCHER.addURI(AUTHORITY, PmWorkItemColumns.TABLE_NAME, URI_TYPE_PM_WORK_ITEM);
        URI_MATCHER.addURI(AUTHORITY, PmWorkItemColumns.TABLE_NAME + "/#", URI_TYPE_PM_WORK_ITEM_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return PmSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_PM_RESULT_ENUM:
                return TYPE_CURSOR_DIR + PmResultEnumColumns.TABLE_NAME;
            case URI_TYPE_PM_RESULT_ENUM_ID:
                return TYPE_CURSOR_ITEM + PmResultEnumColumns.TABLE_NAME;

            case URI_TYPE_PM_WORK:
                return TYPE_CURSOR_DIR + PmWorkColumns.TABLE_NAME;
            case URI_TYPE_PM_WORK_ID:
                return TYPE_CURSOR_ITEM + PmWorkColumns.TABLE_NAME;

            case URI_TYPE_PM_WORK_ITEM:
                return TYPE_CURSOR_DIR + PmWorkItemColumns.TABLE_NAME;
            case URI_TYPE_PM_WORK_ITEM_ID:
                return TYPE_CURSOR_ITEM + PmWorkItemColumns.TABLE_NAME;

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
            case URI_TYPE_PM_RESULT_ENUM:
            case URI_TYPE_PM_RESULT_ENUM_ID:
                res.table = PmResultEnumColumns.TABLE_NAME;
                res.idColumn = PmResultEnumColumns._ID;
                res.tablesWithJoins = PmResultEnumColumns.TABLE_NAME;
                res.orderBy = PmResultEnumColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PM_WORK:
            case URI_TYPE_PM_WORK_ID:
                res.table = PmWorkColumns.TABLE_NAME;
                res.idColumn = PmWorkColumns._ID;
                res.tablesWithJoins = PmWorkColumns.TABLE_NAME;
                res.orderBy = PmWorkColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PM_WORK_ITEM:
            case URI_TYPE_PM_WORK_ITEM_ID:
                res.table = PmWorkItemColumns.TABLE_NAME;
                res.idColumn = PmWorkItemColumns._ID;
                res.tablesWithJoins = PmWorkItemColumns.TABLE_NAME;
                res.orderBy = PmWorkItemColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_PM_RESULT_ENUM_ID:
            case URI_TYPE_PM_WORK_ID:
            case URI_TYPE_PM_WORK_ITEM_ID:
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
