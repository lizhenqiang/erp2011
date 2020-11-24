package cc.xingyan.android.afc.provider.picture;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code picture} table.
 */
public class PictureCursor extends AbstractCursor implements PictureModel {
    public PictureCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PictureColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code key_id} value.
     * Can be {@code null}.
     */
    public String getKeyId() {
        String res = getStringOrNull(PictureColumns.KEY_ID);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        String res = getStringOrNull(PictureColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code picture_id} value.
     * Can be {@code null}.
     */
    public String getPictureId() {
        String res = getStringOrNull(PictureColumns.PICTURE_ID);
        return res;
    }

    /**
     * Get the {@code addr} value.
     * Can be {@code null}.
     */
    public String getAddr() {
        String res = getStringOrNull(PictureColumns.ADDR);
        return res;
    }

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    public String getUrl() {
        String res = getStringOrNull(PictureColumns.URL);
        return res;
    }

    /**
     * Get the {@code date_current} value.
     * Can be {@code null}.
     */
    public Long getDateCurrent() {
        Long res = getLongOrNull(PictureColumns.DATE_CURRENT);
        return res;
    }

    /**
     * Get the {@code remark} value.
     * Can be {@code null}.
     */
    public String getRemark() {
        String res = getStringOrNull(PictureColumns.REMARK);
        return res;
    }
}
