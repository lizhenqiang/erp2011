package cc.xingyan.android.afc.provider.diifspm;

import cc.xingyan.android.afc.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code diifs_pm} table.
 */
public interface DiifsPmModel extends BaseModel {

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
     * Get the {@code logicname} value.
     * Can be {@code null}.
     */
    String getLogicname();

    /**
     * Get the {@code physicname} value.
     * Can be {@code null}.
     */
    String getPhysicname();

    /**
     * Get the {@code physiccode} value.
     * Can be {@code null}.
     */
    String getPhysiccode();

    /**
     * Get the {@code isdone} value.
     * Can be {@code null}.
     */
    Boolean getIsdone();
}
