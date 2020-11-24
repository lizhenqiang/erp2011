package cc.xingyan.android.afc.provider.diifsinfo;

import java.util.Date;

import android.database.Cursor;

import cc.xingyan.android.afc.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code diifs_info} table.
 */
public class DiifsInfoCursor extends AbstractCursor implements DiifsInfoModel {
    public DiifsInfoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DiifsInfoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    public String getUserId() {
        String res = getStringOrNull(DiifsInfoColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    public String getWono() {
        String res = getStringOrNull(DiifsInfoColumns.WONO);
        return res;
    }

    /**
     * Get the {@code rounddefid} value.
     * Can be {@code null}.
     */
    public String getRounddefid() {
        String res = getStringOrNull(DiifsInfoColumns.ROUNDDEFID);
        return res;
    }

    /**
     * Get the {@code descr} value.
     * Can be {@code null}.
     */
    public String getDescr() {
        String res = getStringOrNull(DiifsInfoColumns.DESCR);
        return res;
    }

    /**
     * Get the {@code requiredstartdate} value.
     * Can be {@code null}.
     */
    public Long getRequiredstartdate() {
        Long res = getLongOrNull(DiifsInfoColumns.REQUIREDSTARTDATE);
        return res;
    }

    /**
     * Get the {@code planstartdate} value.
     * Can be {@code null}.
     */
    public Long getPlanstartdate() {
        Long res = getLongOrNull(DiifsInfoColumns.PLANSTARTDATE);
        return res;
    }

    /**
     * Get the {@code planfinishdate} value.
     * Can be {@code null}.
     */
    public Long getPlanfinishdate() {
        Long res = getLongOrNull(DiifsInfoColumns.PLANFINISHDATE);
        return res;
    }

    /**
     * Get the {@code signature} value.
     * Can be {@code null}.
     */
    public String getSignature() {
        String res = getStringOrNull(DiifsInfoColumns.SIGNATURE);
        return res;
    }

    /**
     * Get the {@code sign} value.
     * Can be {@code null}.
     */
    public String getSign() {
        String res = getStringOrNull(DiifsInfoColumns.SIGN);
        return res;
    }

    /**
     * Get the {@code devicecount} value.
     * Can be {@code null}.
     */
    public String getDevicecount() {
        String res = getStringOrNull(DiifsInfoColumns.DEVICECOUNT);
        return res;
    }

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    public Boolean getUploadPending() {
        Boolean res = getBooleanOrNull(DiifsInfoColumns.UPLOAD_PENDING);
        return res;
    }

    /**
     * Get the {@code isconfirm} value.
     * Can be {@code null}.
     */
    public Boolean getIsconfirm() {
        Boolean res = getBooleanOrNull(DiifsInfoColumns.ISCONFIRM);
        return res;
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public DiIfsStatus getStatus() {
        Integer intValue = getIntegerOrNull(DiifsInfoColumns.STATUS);
        if (intValue == null) return null;
        return DiIfsStatus.values()[intValue];
    }
}
