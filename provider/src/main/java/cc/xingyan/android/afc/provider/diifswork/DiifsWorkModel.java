package cc.xingyan.android.afc.provider.diifswork;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code diifs_work} table.
 */
public interface DiifsWorkModel extends BaseModel {

    /**
     * Get the {@code wono} value.
     * Can be {@code null}.
     */
    String getWono();

    /**
     * Get the {@code pmno} value.
     * Can be {@code null}.
     */
    String getPmno();

    /**
     * Get the {@code testpointid} value.
     * Can be {@code null}.
     */
    String getTestpointid();

    /**
     * Get the {@code parametercode} value.
     * Can be {@code null}.
     */
    String getParametercode();

    /**
     * Get the {@code parameterdesc} value.
     * Can be {@code null}.
     */
    String getParameterdesc();

    /**
     * Get the {@code parametertype} value.
     * Can be {@code null}.
     */
    String getParametertype();

    /**
     * Get the {@code unit} value.
     * Can be {@code null}.
     */
    String getUnit();

    /**
     * Get the {@code minvalue} value.
     * Can be {@code null}.
     */
    String getMinvalue();

    /**
     * Get the {@code maxvalue} value.
     * Can be {@code null}.
     */
    String getMaxvalue();

    /**
     * Get the {@code startvalue} value.
     * Can be {@code null}.
     */
    String getStartvalue();

    /**
     * Get the {@code interval} value.
     * Can be {@code null}.
     */
    String getInterval();

    /**
     * Get the {@code lastvalue} value.
     * Can be {@code null}.
     */
    String getLastvalue();

    /**
     * Get the {@code pmgenvalue} value.
     * Can be {@code null}.
     */
    String getPmgenvalue();

    /**
     * Get the {@code objid} value.
     * Can be {@code null}.
     */
    String getObjid();

    /**
     * Get the {@code objversion} value.
     * Can be {@code null}.
     */
    String getObjversion();

    /**
     * Get the {@code isupdate} value.
     * Can be {@code null}.
     */
    Boolean getIsupdate();

    /**
     * Get the {@code physiccode} value.
     * Can be {@code null}.
     */
    String getPhysiccode();
}
