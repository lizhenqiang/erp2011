package cc.xingyan.android.afc.provider.diifsinfo;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code diifs_info} table.
 */
public interface DiifsInfoModel extends BaseModel {

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    String getUserId();

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    String getWono();

    /**
     * Get the {@code rounddefid} value.
     * Can be {@code null}.
     */
    String getRounddefid();

    /**
     * Get the {@code descr} value.
     * Can be {@code null}.
     */
    String getDescr();

    /**
     * Get the {@code requiredstartdate} value.
     * Can be {@code null}.
     */
    Long getRequiredstartdate();

    /**
     * Get the {@code planstartdate} value.
     * Can be {@code null}.
     */
    Long getPlanstartdate();

    /**
     * Get the {@code planfinishdate} value.
     * Can be {@code null}.
     */
    Long getPlanfinishdate();

    /**
     * Get the {@code signature} value.
     * Can be {@code null}.
     */
    String getSignature();

    /**
     * Get the {@code sign} value.
     * Can be {@code null}.
     */
    String getSign();

    /**
     * Get the {@code devicecount} value.
     * Can be {@code null}.
     */
    String getDevicecount();

    /**
     * Get the {@code upload_pending} value.
     * Can be {@code null}.
     */
    Boolean getUploadPending();

    /**
     * Get the {@code isconfirm} value.
     * Can be {@code null}.
     */
    Boolean getIsconfirm();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    DiIfsStatus getStatus();
}
