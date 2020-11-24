package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.cmmaterial.CmMaterialColumns;
import cc.xingyan.android.afc.provider.cmmaterialrow.CmMaterialRowColumns;
import cc.xingyan.android.afc.provider.cmparammaterials.CmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.cmspareparts.CmSparePartsColumns;
import cc.xingyan.android.afc.provider.cmwork.CmWorkColumns;

public class CmProvider extends BaseContentProvider {
    private static final String TAG = CmProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("true");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.cm";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_CM_MATERIAL = 0;
    private static final int URI_TYPE_CM_MATERIAL_ID = 1;

    private static final int URI_TYPE_CM_MATERIAL_ROW = 2;
    private static final int URI_TYPE_CM_MATERIAL_ROW_ID = 3;

    private static final int URI_TYPE_CM_PARAM_MATERIALS = 4;
    private static final int URI_TYPE_CM_PARAM_MATERIALS_ID = 5;

    private static final int URI_TYPE_CM_SPARE_PARTS = 6;
    private static final int URI_TYPE_CM_SPARE_PARTS_ID = 7;

    private static final int URI_TYPE_CM_WORK = 8;
    private static final int URI_TYPE_CM_WORK_ID = 9;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, CmMaterialColumns.TABLE_NAME, URI_TYPE_CM_MATERIAL);
        URI_MATCHER.addURI(AUTHORITY, CmMaterialColumns.TABLE_NAME + "/#", URI_TYPE_CM_MATERIAL_ID);
        URI_MATCHER.addURI(AUTHORITY, CmMaterialRowColumns.TABLE_NAME, URI_TYPE_CM_MATERIAL_ROW);
        URI_MATCHER.addURI(AUTHORITY, CmMaterialRowColumns.TABLE_NAME + "/#", URI_TYPE_CM_MATERIAL_ROW_ID);
        URI_MATCHER.addURI(AUTHORITY, CmParamMaterialsColumns.TABLE_NAME, URI_TYPE_CM_PARAM_MATERIALS);
        URI_MATCHER.addURI(AUTHORITY, CmParamMaterialsColumns.TABLE_NAME + "/#", URI_TYPE_CM_PARAM_MATERIALS_ID);
        URI_MATCHER.addURI(AUTHORITY, CmSparePartsColumns.TABLE_NAME, URI_TYPE_CM_SPARE_PARTS);
        URI_MATCHER.addURI(AUTHORITY, CmSparePartsColumns.TABLE_NAME + "/#", URI_TYPE_CM_SPARE_PARTS_ID);
        URI_MATCHER.addURI(AUTHORITY, CmWorkColumns.TABLE_NAME, URI_TYPE_CM_WORK);
        URI_MATCHER.addURI(AUTHORITY, CmWorkColumns.TABLE_NAME + "/#", URI_TYPE_CM_WORK_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return CmSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_CM_MATERIAL:
                return TYPE_CURSOR_DIR + CmMaterialColumns.TABLE_NAME;
            case URI_TYPE_CM_MATERIAL_ID:
                return TYPE_CURSOR_ITEM + CmMaterialColumns.TABLE_NAME;

            case URI_TYPE_CM_MATERIAL_ROW:
                return TYPE_CURSOR_DIR + CmMaterialRowColumns.TABLE_NAME;
            case URI_TYPE_CM_MATERIAL_ROW_ID:
                return TYPE_CURSOR_ITEM + CmMaterialRowColumns.TABLE_NAME;

            case URI_TYPE_CM_PARAM_MATERIALS:
                return TYPE_CURSOR_DIR + CmParamMaterialsColumns.TABLE_NAME;
            case URI_TYPE_CM_PARAM_MATERIALS_ID:
                return TYPE_CURSOR_ITEM + CmParamMaterialsColumns.TABLE_NAME;

            case URI_TYPE_CM_SPARE_PARTS:
                return TYPE_CURSOR_DIR + CmSparePartsColumns.TABLE_NAME;
            case URI_TYPE_CM_SPARE_PARTS_ID:
                return TYPE_CURSOR_ITEM + CmSparePartsColumns.TABLE_NAME;

            case URI_TYPE_CM_WORK:
                return TYPE_CURSOR_DIR + CmWorkColumns.TABLE_NAME;
            case URI_TYPE_CM_WORK_ID:
                return TYPE_CURSOR_ITEM + CmWorkColumns.TABLE_NAME;

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
            case URI_TYPE_CM_MATERIAL:
            case URI_TYPE_CM_MATERIAL_ID:
                res.table = CmMaterialColumns.TABLE_NAME;
                res.idColumn = CmMaterialColumns._ID;
                res.tablesWithJoins = CmMaterialColumns.TABLE_NAME;
                res.orderBy = CmMaterialColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CM_MATERIAL_ROW:
            case URI_TYPE_CM_MATERIAL_ROW_ID:
                res.table = CmMaterialRowColumns.TABLE_NAME;
                res.idColumn = CmMaterialRowColumns._ID;
                res.tablesWithJoins = CmMaterialRowColumns.TABLE_NAME;
                res.orderBy = CmMaterialRowColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CM_PARAM_MATERIALS:
            case URI_TYPE_CM_PARAM_MATERIALS_ID:
                res.table = CmParamMaterialsColumns.TABLE_NAME;
                res.idColumn = CmParamMaterialsColumns._ID;
                res.tablesWithJoins = CmParamMaterialsColumns.TABLE_NAME;
                res.orderBy = CmParamMaterialsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CM_SPARE_PARTS:
            case URI_TYPE_CM_SPARE_PARTS_ID:
                res.table = CmSparePartsColumns.TABLE_NAME;
                res.idColumn = CmSparePartsColumns._ID;
                res.tablesWithJoins = CmSparePartsColumns.TABLE_NAME;
                res.orderBy = CmSparePartsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CM_WORK:
            case URI_TYPE_CM_WORK_ID:
                res.table = CmWorkColumns.TABLE_NAME;
                res.idColumn = CmWorkColumns._ID;
                res.tablesWithJoins = CmWorkColumns.TABLE_NAME;
                res.orderBy = CmWorkColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_CM_MATERIAL_ID:
            case URI_TYPE_CM_MATERIAL_ROW_ID:
            case URI_TYPE_CM_PARAM_MATERIALS_ID:
            case URI_TYPE_CM_SPARE_PARTS_ID:
            case URI_TYPE_CM_WORK_ID:
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
