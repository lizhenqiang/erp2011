package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoColumns;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkColumns;

public class DiifsProvider extends BaseContentProvider {
    private static final String TAG = DiifsProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("false");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.diifs";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_DIIFS_INFO = 0;
    private static final int URI_TYPE_DIIFS_INFO_ID = 1;

    private static final int URI_TYPE_DIIFS_PM = 2;
    private static final int URI_TYPE_DIIFS_PM_ID = 3;

    private static final int URI_TYPE_DIIFS_PM_ITEM = 4;
    private static final int URI_TYPE_DIIFS_PM_ITEM_ID = 5;

    private static final int URI_TYPE_DIIFS_WORK = 6;
    private static final int URI_TYPE_DIIFS_WORK_ID = 7;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, DiifsInfoColumns.TABLE_NAME, URI_TYPE_DIIFS_INFO);
        URI_MATCHER.addURI(AUTHORITY, DiifsInfoColumns.TABLE_NAME + "/#", URI_TYPE_DIIFS_INFO_ID);
        URI_MATCHER.addURI(AUTHORITY, DiifsPmColumns.TABLE_NAME, URI_TYPE_DIIFS_PM);
        URI_MATCHER.addURI(AUTHORITY, DiifsPmColumns.TABLE_NAME + "/#", URI_TYPE_DIIFS_PM_ID);
        URI_MATCHER.addURI(AUTHORITY, DiifsPmItemColumns.TABLE_NAME, URI_TYPE_DIIFS_PM_ITEM);
        URI_MATCHER.addURI(AUTHORITY, DiifsPmItemColumns.TABLE_NAME + "/#", URI_TYPE_DIIFS_PM_ITEM_ID);
        URI_MATCHER.addURI(AUTHORITY, DiifsWorkColumns.TABLE_NAME, URI_TYPE_DIIFS_WORK);
        URI_MATCHER.addURI(AUTHORITY, DiifsWorkColumns.TABLE_NAME + "/#", URI_TYPE_DIIFS_WORK_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return DiifsSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_DIIFS_INFO:
                return TYPE_CURSOR_DIR + DiifsInfoColumns.TABLE_NAME;
            case URI_TYPE_DIIFS_INFO_ID:
                return TYPE_CURSOR_ITEM + DiifsInfoColumns.TABLE_NAME;

            case URI_TYPE_DIIFS_PM:
                return TYPE_CURSOR_DIR + DiifsPmColumns.TABLE_NAME;
            case URI_TYPE_DIIFS_PM_ID:
                return TYPE_CURSOR_ITEM + DiifsPmColumns.TABLE_NAME;

            case URI_TYPE_DIIFS_PM_ITEM:
                return TYPE_CURSOR_DIR + DiifsPmItemColumns.TABLE_NAME;
            case URI_TYPE_DIIFS_PM_ITEM_ID:
                return TYPE_CURSOR_ITEM + DiifsPmItemColumns.TABLE_NAME;

            case URI_TYPE_DIIFS_WORK:
                return TYPE_CURSOR_DIR + DiifsWorkColumns.TABLE_NAME;
            case URI_TYPE_DIIFS_WORK_ID:
                return TYPE_CURSOR_ITEM + DiifsWorkColumns.TABLE_NAME;

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
            case URI_TYPE_DIIFS_INFO:
            case URI_TYPE_DIIFS_INFO_ID:
                res.table = DiifsInfoColumns.TABLE_NAME;
                res.idColumn = DiifsInfoColumns._ID;
                res.tablesWithJoins = DiifsInfoColumns.TABLE_NAME;
                res.orderBy = DiifsInfoColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DIIFS_PM:
            case URI_TYPE_DIIFS_PM_ID:
                res.table = DiifsPmColumns.TABLE_NAME;
                res.idColumn = DiifsPmColumns._ID;
                res.tablesWithJoins = DiifsPmColumns.TABLE_NAME;
                res.orderBy = DiifsPmColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DIIFS_PM_ITEM:
            case URI_TYPE_DIIFS_PM_ITEM_ID:
                res.table = DiifsPmItemColumns.TABLE_NAME;
                res.idColumn = DiifsPmItemColumns._ID;
                res.tablesWithJoins = DiifsPmItemColumns.TABLE_NAME;
                res.orderBy = DiifsPmItemColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DIIFS_WORK:
            case URI_TYPE_DIIFS_WORK_ID:
                res.table = DiifsWorkColumns.TABLE_NAME;
                res.idColumn = DiifsWorkColumns._ID;
                res.tablesWithJoins = DiifsWorkColumns.TABLE_NAME;
                res.orderBy = DiifsWorkColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_DIIFS_INFO_ID:
            case URI_TYPE_DIIFS_PM_ID:
            case URI_TYPE_DIIFS_PM_ITEM_ID:
            case URI_TYPE_DIIFS_WORK_ID:
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
