package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.diwork.DiWorkColumns;
import cc.xingyan.android.afc.provider.diworkitem.DiWorkItemColumns;

public class DiProvider extends BaseContentProvider {
    private static final String TAG = DiProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("true");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.di";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_DI_WORK = 0;
    private static final int URI_TYPE_DI_WORK_ID = 1;

    private static final int URI_TYPE_DI_WORK_ITEM = 2;
    private static final int URI_TYPE_DI_WORK_ITEM_ID = 3;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, DiWorkColumns.TABLE_NAME, URI_TYPE_DI_WORK);
        URI_MATCHER.addURI(AUTHORITY, DiWorkColumns.TABLE_NAME + "/#", URI_TYPE_DI_WORK_ID);
        URI_MATCHER.addURI(AUTHORITY, DiWorkItemColumns.TABLE_NAME, URI_TYPE_DI_WORK_ITEM);
        URI_MATCHER.addURI(AUTHORITY, DiWorkItemColumns.TABLE_NAME + "/#", URI_TYPE_DI_WORK_ITEM_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return DiSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_DI_WORK:
                return TYPE_CURSOR_DIR + DiWorkColumns.TABLE_NAME;
            case URI_TYPE_DI_WORK_ID:
                return TYPE_CURSOR_ITEM + DiWorkColumns.TABLE_NAME;

            case URI_TYPE_DI_WORK_ITEM:
                return TYPE_CURSOR_DIR + DiWorkItemColumns.TABLE_NAME;
            case URI_TYPE_DI_WORK_ITEM_ID:
                return TYPE_CURSOR_ITEM + DiWorkItemColumns.TABLE_NAME;

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
            case URI_TYPE_DI_WORK:
            case URI_TYPE_DI_WORK_ID:
                res.table = DiWorkColumns.TABLE_NAME;
                res.idColumn = DiWorkColumns._ID;
                res.tablesWithJoins = DiWorkColumns.TABLE_NAME;
                res.orderBy = DiWorkColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DI_WORK_ITEM:
            case URI_TYPE_DI_WORK_ITEM_ID:
                res.table = DiWorkItemColumns.TABLE_NAME;
                res.idColumn = DiWorkItemColumns._ID;
                res.tablesWithJoins = DiWorkItemColumns.TABLE_NAME;
                res.orderBy = DiWorkItemColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_DI_WORK_ID:
            case URI_TYPE_DI_WORK_ITEM_ID:
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
