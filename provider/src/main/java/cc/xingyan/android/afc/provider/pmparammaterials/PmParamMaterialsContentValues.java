package cc.xingyan.android.afc.provider.pmparammaterials;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code pm_param_materials} table.
 */
public class PmParamMaterialsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PmParamMaterialsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PmParamMaterialsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PmParamMaterialsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PmParamMaterialsContentValues putCode(String value) {
        mContentValues.put(PmParamMaterialsColumns.CODE, value);
        return this;
    }

    public PmParamMaterialsContentValues putCodeNull() {
        mContentValues.putNull(PmParamMaterialsColumns.CODE);
        return this;
    }

    public PmParamMaterialsContentValues putName(String value) {
        mContentValues.put(PmParamMaterialsColumns.NAME, value);
        return this;
    }

    public PmParamMaterialsContentValues putNameNull() {
        mContentValues.putNull(PmParamMaterialsColumns.NAME);
        return this;
    }

    public PmParamMaterialsContentValues putLine(String value) {
        mContentValues.put(PmParamMaterialsColumns.LINE, value);
        return this;
    }

    public PmParamMaterialsContentValues putLineNull() {
        mContentValues.putNull(PmParamMaterialsColumns.LINE);
        return this;
    }

    public PmParamMaterialsContentValues putDevice(String value) {
        mContentValues.put(PmParamMaterialsColumns.DEVICE, value);
        return this;
    }

    public PmParamMaterialsContentValues putDeviceNull() {
        mContentValues.putNull(PmParamMaterialsColumns.DEVICE);
        return this;
    }
}
