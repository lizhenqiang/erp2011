package cc.xingyan.android.afc.provider.cmparammaterials;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cm_param_materials} table.
 */
public class CmParamMaterialsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CmParamMaterialsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  CmParamMaterialsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  CmParamMaterialsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CmParamMaterialsContentValues putCode(String value) {
        mContentValues.put(CmParamMaterialsColumns.CODE, value);
        return this;
    }

    public CmParamMaterialsContentValues putCodeNull() {
        mContentValues.putNull(CmParamMaterialsColumns.CODE);
        return this;
    }

    public CmParamMaterialsContentValues putName(String value) {
        mContentValues.put(CmParamMaterialsColumns.NAME, value);
        return this;
    }

    public CmParamMaterialsContentValues putNameNull() {
        mContentValues.putNull(CmParamMaterialsColumns.NAME);
        return this;
    }

    public CmParamMaterialsContentValues putLine(String value) {
        mContentValues.put(CmParamMaterialsColumns.LINE, value);
        return this;
    }

    public CmParamMaterialsContentValues putLineNull() {
        mContentValues.putNull(CmParamMaterialsColumns.LINE);
        return this;
    }

    public CmParamMaterialsContentValues putDevice(String value) {
        mContentValues.put(CmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public CmParamMaterialsContentValues putDeviceNull() {
        mContentValues.putNull(CmParamMaterialsColumns.DEVICE);
        return this;
    }
}
