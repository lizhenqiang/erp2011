package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.yunshuhead.YunshuHeadColumns;
import cc.xingyan.android.afc.provider.yunshukuwei.YunshuKuweiColumns;
import cc.xingyan.android.afc.provider.yunshuline.YunshuLineColumns;

public class YunshuProvider extends BaseContentProvider {
    private static final String TAG = YunshuProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("false");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.yunshu";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_YUNSHU_HEAD = 0;
    private static final int URI_TYPE_YUNSHU_HEAD_ID = 1;

    private static final int URI_TYPE_YUNSHU_KUWEI = 2;
    private static final int URI_TYPE_YUNSHU_KUWEI_ID = 3;

    private static final int URI_TYPE_YUNSHU_LINE = 4;
    private static final int URI_TYPE_YUNSHU_LINE_ID = 5;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, YunshuHeadColumns.TABLE_NAME, URI_TYPE_YUNSHU_HEAD);
        URI_MATCHER.addURI(AUTHORITY, YunshuHeadColumns.TABLE_NAME + "/#", URI_TYPE_YUNSHU_HEAD_ID);
        URI_MATCHER.addURI(AUTHORITY, YunshuKuweiColumns.TABLE_NAME, URI_TYPE_YUNSHU_KUWEI);
        URI_MATCHER.addURI(AUTHORITY, YunshuKuweiColumns.TABLE_NAME + "/#", URI_TYPE_YUNSHU_KUWEI_ID);
        URI_MATCHER.addURI(AUTHORITY, YunshuLineColumns.TABLE_NAME, URI_TYPE_YUNSHU_LINE);
        URI_MATCHER.addURI(AUTHORITY, YunshuLineColumns.TABLE_NAME + "/#", URI_TYPE_YUNSHU_LINE_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return YunshuSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_YUNSHU_HEAD:
                return TYPE_CURSOR_DIR + YunshuHeadColumns.TABLE_NAME;
            case URI_TYPE_YUNSHU_HEAD_ID:
                return TYPE_CURSOR_ITEM + YunshuHeadColumns.TABLE_NAME;

            case URI_TYPE_YUNSHU_KUWEI:
                return TYPE_CURSOR_DIR + YunshuKuweiColumns.TABLE_NAME;
            case URI_TYPE_YUNSHU_KUWEI_ID:
                return TYPE_CURSOR_ITEM + YunshuKuweiColumns.TABLE_NAME;

            case URI_TYPE_YUNSHU_LINE:
                return TYPE_CURSOR_DIR + YunshuLineColumns.TABLE_NAME;
            case URI_TYPE_YUNSHU_LINE_ID:
                return TYPE_CURSOR_ITEM + YunshuLineColumns.TABLE_NAME;

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
            case URI_TYPE_YUNSHU_HEAD:
            case URI_TYPE_YUNSHU_HEAD_ID:
                res.table = YunshuHeadColumns.TABLE_NAME;
                res.idColumn = YunshuHeadColumns._ID;
                res.tablesWithJoins = YunshuHeadColumns.TABLE_NAME;
                res.orderBy = YunshuHeadColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_YUNSHU_KUWEI:
            case URI_TYPE_YUNSHU_KUWEI_ID:
                res.table = YunshuKuweiColumns.TABLE_NAME;
                res.idColumn = YunshuKuweiColumns._ID;
                res.tablesWithJoins = YunshuKuweiColumns.TABLE_NAME;
                res.orderBy = YunshuKuweiColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_YUNSHU_LINE:
            case URI_TYPE_YUNSHU_LINE_ID:
                res.table = YunshuLineColumns.TABLE_NAME;
                res.idColumn = YunshuLineColumns._ID;
                res.tablesWithJoins = YunshuLineColumns.TABLE_NAME;
                res.orderBy = YunshuLineColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_YUNSHU_HEAD_ID:
            case URI_TYPE_YUNSHU_KUWEI_ID:
            case URI_TYPE_YUNSHU_LINE_ID:
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
