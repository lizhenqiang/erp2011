package cc.xingyan.android.afc.provider.diifsinfo;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code diifs_info} table.
 */
public class DiifsInfoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DiifsInfoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  DiifsInfoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  DiifsInfoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DiifsInfoContentValues putUserId(String value) {
        mContentValues.put(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoContentValues putUserIdNull() {
        mContentValues.putNull(DiifsInfoColumns.USER_ID);
        return this;
    }

    public DiifsInfoContentValues putWono(String value) {
        mContentValues.put(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoContentValues putWonoNull() {
        mContentValues.putNull(DiifsInfoColumns.WONO);
        return this;
    }

    public DiifsInfoContentValues putRounddefid(String value) {
        mContentValues.put(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoContentValues putRounddefidNull() {
        mContentValues.putNull(DiifsInfoColumns.ROUNDDEFID);
        return this;
    }

    public DiifsInfoContentValues putDescr(String value) {
        mContentValues.put(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoContentValues putDescrNull() {
        mContentValues.putNull(DiifsInfoColumns.DESCR);
        return this;
    }

    public DiifsInfoContentValues putRequiredstartdate(Long value) {
        mContentValues.put(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoContentValues putRequiredstartdateNull() {
        mContentValues.putNull(DiifsInfoColumns.REQUIREDSTARTDATE);
        return this;
    }

    public DiifsInfoContentValues putPlanstartdate(Long value) {
        mContentValues.put(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoContentValues putPlanstartdateNull() {
        mContentValues.putNull(DiifsInfoColumns.PLANSTARTDATE);
        return this;
    }

    public DiifsInfoContentValues putPlanfinishdate(Long value) {
        mContentValues.put(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoContentValues putPlanfinishdateNull() {
        mContentValues.putNull(DiifsInfoColumns.PLANFINISHDATE);
        return this;
    }

    public DiifsInfoContentValues putSignature(String value) {
        mContentValues.put(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoContentValues putSignatureNull() {
        mContentValues.putNull(DiifsInfoColumns.SIGNATURE);
        return this;
    }

    public DiifsInfoContentValues putSign(String value) {
        mContentValues.put(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoContentValues putSignNull() {
        mContentValues.putNull(DiifsInfoColumns.SIGN);
        return this;
    }

    public DiifsInfoContentValues putDevicecount(String value) {
        mContentValues.put(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoContentValues putDevicecountNull() {
        mContentValues.putNull(DiifsInfoColumns.DEVICECOUNT);
        return this;
    }

    public DiifsInfoContentValues putUploadPending(Boolean value) {
        mContentValues.put(DiifsInfoColumns.UPLOAD_PENDING, value);
        return this;
    }

    public DiifsInfoContentValues putUploadPendingNull() {
        mContentValues.putNull(DiifsInfoColumns.UPLOAD_PENDING);
        return this;
    }

    public DiifsInfoContentValues putIsconfirm(Boolean value) {
        mContentValues.put(DiifsInfoColumns.ISCONFIRM, value);
        return this;
    }

    public DiifsInfoContentValues putIsconfirmNull() {
        mContentValues.putNull(DiifsInfoColumns.ISCONFIRM);
        return this;
    }

    public DiifsInfoContentValues putStatus(DiIfsStatus value) {
        mContentValues.put(DiifsInfoColumns.STATUS, value == null ? null : value.ordinal());
        return this;
    }

    public DiifsInfoContentValues putStatusNull() {
        mContentValues.putNull(DiifsInfoColumns.STATUS);
        return this;
    }
}
