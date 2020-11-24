package cc.xingyan.android.afc.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import cc.xingyan.android.afc.provider.base.BaseContentProvider;
import cc.xingyan.android.afc.provider.pmmaterial.PmMaterialColumns;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowColumns;
import cc.xingyan.android.afc.provider.pmparammaterials.PmParamMaterialsColumns;
import cc.xingyan.android.afc.provider.pmifsresultenum.PmifsResultEnumColumns;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkColumns;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemColumns;

public class PmifsProvider extends BaseContentProvider {
    private static final String TAG = PmifsProvider.class.getSimpleName();

    private static final boolean DEBUG = Boolean.parseBoolean("true");

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.biizy.android.erg.provider.pmifs";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_PM_MATERIAL = 0;
    private static final int URI_TYPE_PM_MATERIAL_ID = 1;

    private static final int URI_TYPE_PM_MATERIAL_ROW = 2;
    private static final int URI_TYPE_PM_MATERIAL_ROW_ID = 3;

    private static final int URI_TYPE_PM_PARAM_MATERIALS = 4;
    private static final int URI_TYPE_PM_PARAM_MATERIALS_ID = 5;

    private static final int URI_TYPE_PMIFS_RESULT_ENUM = 6;
    private static final int URI_TYPE_PMIFS_RESULT_ENUM_ID = 7;

    private static final int URI_TYPE_PMIFS_WORK = 8;
    private static final int URI_TYPE_PMIFS_WORK_ID = 9;

    private static final int URI_TYPE_PMIFS_WORK_ITEM = 10;
    private static final int URI_TYPE_PMIFS_WORK_ITEM_ID = 11;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, PmMaterialColumns.TABLE_NAME, URI_TYPE_PM_MATERIAL);
        URI_MATCHER.addURI(AUTHORITY, PmMaterialColumns.TABLE_NAME + "/#", URI_TYPE_PM_MATERIAL_ID);
        URI_MATCHER.addURI(AUTHORITY, PmMaterialRowColumns.TABLE_NAME, URI_TYPE_PM_MATERIAL_ROW);
        URI_MATCHER.addURI(AUTHORITY, PmMaterialRowColumns.TABLE_NAME + "/#", URI_TYPE_PM_MATERIAL_ROW_ID);
        URI_MATCHER.addURI(AUTHORITY, PmParamMaterialsColumns.TABLE_NAME, URI_TYPE_PM_PARAM_MATERIALS);
        URI_MATCHER.addURI(AUTHORITY, PmParamMaterialsColumns.TABLE_NAME + "/#", URI_TYPE_PM_PARAM_MATERIALS_ID);
        URI_MATCHER.addURI(AUTHORITY, PmifsResultEnumColumns.TABLE_NAME, URI_TYPE_PMIFS_RESULT_ENUM);
        URI_MATCHER.addURI(AUTHORITY, PmifsResultEnumColumns.TABLE_NAME + "/#", URI_TYPE_PMIFS_RESULT_ENUM_ID);
        URI_MATCHER.addURI(AUTHORITY, PmifsWorkColumns.TABLE_NAME, URI_TYPE_PMIFS_WORK);
        URI_MATCHER.addURI(AUTHORITY, PmifsWorkColumns.TABLE_NAME + "/#", URI_TYPE_PMIFS_WORK_ID);
        URI_MATCHER.addURI(AUTHORITY, PmifsWorkItemColumns.TABLE_NAME, URI_TYPE_PMIFS_WORK_ITEM);
        URI_MATCHER.addURI(AUTHORITY, PmifsWorkItemColumns.TABLE_NAME + "/#", URI_TYPE_PMIFS_WORK_ITEM_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return PmifsSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_PM_MATERIAL:
                return TYPE_CURSOR_DIR + PmMaterialColumns.TABLE_NAME;
            case URI_TYPE_PM_MATERIAL_ID:
                return TYPE_CURSOR_ITEM + PmMaterialColumns.TABLE_NAME;

            case URI_TYPE_PM_MATERIAL_ROW:
                return TYPE_CURSOR_DIR + PmMaterialRowColumns.TABLE_NAME;
            case URI_TYPE_PM_MATERIAL_ROW_ID:
                return TYPE_CURSOR_ITEM + PmMaterialRowColumns.TABLE_NAME;

            case URI_TYPE_PM_PARAM_MATERIALS:
                return TYPE_CURSOR_DIR + PmParamMaterialsColumns.TABLE_NAME;
            case URI_TYPE_PM_PARAM_MATERIALS_ID:
                return TYPE_CURSOR_ITEM + PmParamMaterialsColumns.TABLE_NAME;

            case URI_TYPE_PMIFS_RESULT_ENUM:
                return TYPE_CURSOR_DIR + PmifsResultEnumColumns.TABLE_NAME;
            case URI_TYPE_PMIFS_RESULT_ENUM_ID:
                return TYPE_CURSOR_ITEM + PmifsResultEnumColumns.TABLE_NAME;

            case URI_TYPE_PMIFS_WORK:
                return TYPE_CURSOR_DIR + PmifsWorkColumns.TABLE_NAME;
            case URI_TYPE_PMIFS_WORK_ID:
                return TYPE_CURSOR_ITEM + PmifsWorkColumns.TABLE_NAME;

            case URI_TYPE_PMIFS_WORK_ITEM:
                return TYPE_CURSOR_DIR + PmifsWorkItemColumns.TABLE_NAME;
            case URI_TYPE_PMIFS_WORK_ITEM_ID:
                return TYPE_CURSOR_ITEM + PmifsWorkItemColumns.TABLE_NAME;

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
            case URI_TYPE_PM_MATERIAL:
            case URI_TYPE_PM_MATERIAL_ID:
                res.table = PmMaterialColumns.TABLE_NAME;
                res.idColumn = PmMaterialColumns._ID;
                res.tablesWithJoins = PmMaterialColumns.TABLE_NAME;
                res.orderBy = PmMaterialColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PM_MATERIAL_ROW:
            case URI_TYPE_PM_MATERIAL_ROW_ID:
                res.table = PmMaterialRowColumns.TABLE_NAME;
                res.idColumn = PmMaterialRowColumns._ID;
                res.tablesWithJoins = PmMaterialRowColumns.TABLE_NAME;
                res.orderBy = PmMaterialRowColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PM_PARAM_MATERIALS:
            case URI_TYPE_PM_PARAM_MATERIALS_ID:
                res.table = PmParamMaterialsColumns.TABLE_NAME;
                res.idColumn = PmParamMaterialsColumns._ID;
                res.tablesWithJoins = PmParamMaterialsColumns.TABLE_NAME;
                res.orderBy = PmParamMaterialsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PMIFS_RESULT_ENUM:
            case URI_TYPE_PMIFS_RESULT_ENUM_ID:
                res.table = PmifsResultEnumColumns.TABLE_NAME;
                res.idColumn = PmifsResultEnumColumns._ID;
                res.tablesWithJoins = PmifsResultEnumColumns.TABLE_NAME;
                res.orderBy = PmifsResultEnumColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PMIFS_WORK:
            case URI_TYPE_PMIFS_WORK_ID:
                res.table = PmifsWorkColumns.TABLE_NAME;
                res.idColumn = PmifsWorkColumns._ID;
                res.tablesWithJoins = PmifsWorkColumns.TABLE_NAME;
                res.orderBy = PmifsWorkColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PMIFS_WORK_ITEM:
            case URI_TYPE_PMIFS_WORK_ITEM_ID:
                res.table = PmifsWorkItemColumns.TABLE_NAME;
                res.idColumn = PmifsWorkItemColumns._ID;
                res.tablesWithJoins = PmifsWorkItemColumns.TABLE_NAME;
                res.orderBy = PmifsWorkItemColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_PM_MATERIAL_ID:
            case URI_TYPE_PM_MATERIAL_ROW_ID:
            case URI_TYPE_PM_PARAM_MATERIALS_ID:
            case URI_TYPE_PMIFS_RESULT_ENUM_ID:
            case URI_TYPE_PMIFS_WORK_ID:
            case URI_TYPE_PMIFS_WORK_ITEM_ID:
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
