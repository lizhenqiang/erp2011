package cc.xingyan.android.afc.provider.picture;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code picture} table.
 */
public class PictureContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PictureColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver,  PictureSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param context The content resolver from which to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context,  PictureSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PictureContentValues putKeyId(String value) {
        mContentValues.put(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureContentValues putKeyIdNull() {
        mContentValues.putNull(PictureColumns.KEY_ID);
        return this;
    }

    public PictureContentValues putType(String value) {
        mContentValues.put(PictureColumns.TYPE, value);
        return this;
    }

    public PictureContentValues putTypeNull() {
        mContentValues.putNull(PictureColumns.TYPE);
        return this;
    }

    public PictureContentValues putPictureId(String value) {
        mContentValues.put(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureContentValues putPictureIdNull() {
        mContentValues.putNull(PictureColumns.PICTURE_ID);
        return this;
    }

    public PictureContentValues putAddr(String value) {
        mContentValues.put(PictureColumns.ADDR, value);
        return this;
    }

    public PictureContentValues putAddrNull() {
        mContentValues.putNull(PictureColumns.ADDR);
        return this;
    }

    public PictureContentValues putUrl(String value) {
        mContentValues.put(PictureColumns.URL, value);
        return this;
    }

    public PictureContentValues putUrlNull() {
        mContentValues.putNull(PictureColumns.URL);
        return this;
    }

    public PictureContentValues putDateCurrent(Long value) {
        mContentValues.put(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureContentValues putDateCurrentNull() {
        mContentValues.putNull(PictureColumns.DATE_CURRENT);
        return this;
    }

    public PictureContentValues putRemark(String value) {
        mContentValues.put(PictureColumns.REMARK, value);
        return this;
    }

    public PictureContentValues putRemarkNull() {
        mContentValues.putNull(PictureColumns.REMARK);
        return this;
    }
}
