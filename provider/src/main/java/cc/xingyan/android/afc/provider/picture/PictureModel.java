package cc.xingyan.android.afc.provider.picture;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code picture} table.
 */
public interface PictureModel extends BaseModel {

    /**
     * Get the {@code key_id} value.
     * Can be {@code null}.
     */
    String getKeyId();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    String getType();

    /**
     * Get the {@code picture_id} value.
     * Can be {@code null}.
     */
    String getPictureId();

    /**
     * Get the {@code addr} value.
     * Can be {@code null}.
     */
    String getAddr();

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    String getUrl();

    /**
     * Get the {@code date_current} value.
     * Can be {@code null}.
     */
    Long getDateCurrent();

    /**
     * Get the {@code remark} value.
     * Can be {@code null}.
     */
    String getRemark();
}
