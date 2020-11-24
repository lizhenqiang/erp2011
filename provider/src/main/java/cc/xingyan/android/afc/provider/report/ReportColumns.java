package cc.xingyan.android.afc.provider.report;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.ReportProvider;

/**
 * Columns for the {@code report} table.
 */
public class ReportColumns implements BaseColumns {
    public static final String TABLE_NAME = "report";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.report/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String DATA_START = "data_start";

    public static final String DATA_END = "data_end";

    public static final String LAST_RECE_NUM = "last_rece_num";

    public static final String CURRECT_RECE_NUM = "currect_rece_num";

    public static final String RECE_LRR = "rece_lrr";

    public static final String LAST_FORM_NUM = "last_form_num";

    public static final String CURRECT_FORM_NUM = "currect_form_num";

    public static final String FORM_LRR = "form_lrr";

    public static final String LAST_FORM_DELAY = "last_form_delay";

    public static final String CURRECT_FORM_DELAY = "currect_form_delay";

    public static final String FORM_DELAY_LRR = "form_delay_lrr";

    public static final String YTD_RECE_NUM = "ytd_rece_num";

    public static final String RECE_PER = "rece_per";

    public static final String FORM_PER = "form_per";

    public static final String AG_NUM = "ag_num";

    public static final String BOM_NUM = "bom_num";

    public static final String TVM_NUM = "tvm_num";

    public static final String OTHER_NUM = "other_num";

    public static final String AG_PER = "ag_per";

    public static final String BOM_PER = "bom_per";

    public static final String TVM_PER = "tvm_per";

    public static final String OTHER_PER = "other_per";

    public static final String DEVICE_FAULT_NUM = "device_fault_num";

    public static final String DEVICE_FAULT_PER = "device_fault_per";

    public static final String NOT_DEVICE_FAULT_NUM = "not_device_fault_num";

    public static final String NOT_DEVICE_FAULT_PER = "not_device_fault_per";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CODE,
            NAME,
            DATA_START,
            DATA_END,
            LAST_RECE_NUM,
            CURRECT_RECE_NUM,
            RECE_LRR,
            LAST_FORM_NUM,
            CURRECT_FORM_NUM,
            FORM_LRR,
            LAST_FORM_DELAY,
            CURRECT_FORM_DELAY,
            FORM_DELAY_LRR,
            YTD_RECE_NUM,
            RECE_PER,
            FORM_PER,
            AG_NUM,
            BOM_NUM,
            TVM_NUM,
            OTHER_NUM,
            AG_PER,
            BOM_PER,
            TVM_PER,
            OTHER_PER,
            DEVICE_FAULT_NUM,
            DEVICE_FAULT_PER,
            NOT_DEVICE_FAULT_NUM,
            NOT_DEVICE_FAULT_PER
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CODE) || c.contains("." + CODE)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(DATA_START) || c.contains("." + DATA_START)) return true;
            if (c.equals(DATA_END) || c.contains("." + DATA_END)) return true;
            if (c.equals(LAST_RECE_NUM) || c.contains("." + LAST_RECE_NUM)) return true;
            if (c.equals(CURRECT_RECE_NUM) || c.contains("." + CURRECT_RECE_NUM)) return true;
            if (c.equals(RECE_LRR) || c.contains("." + RECE_LRR)) return true;
            if (c.equals(LAST_FORM_NUM) || c.contains("." + LAST_FORM_NUM)) return true;
            if (c.equals(CURRECT_FORM_NUM) || c.contains("." + CURRECT_FORM_NUM)) return true;
            if (c.equals(FORM_LRR) || c.contains("." + FORM_LRR)) return true;
            if (c.equals(LAST_FORM_DELAY) || c.contains("." + LAST_FORM_DELAY)) return true;
            if (c.equals(CURRECT_FORM_DELAY) || c.contains("." + CURRECT_FORM_DELAY)) return true;
            if (c.equals(FORM_DELAY_LRR) || c.contains("." + FORM_DELAY_LRR)) return true;
            if (c.equals(YTD_RECE_NUM) || c.contains("." + YTD_RECE_NUM)) return true;
            if (c.equals(RECE_PER) || c.contains("." + RECE_PER)) return true;
            if (c.equals(FORM_PER) || c.contains("." + FORM_PER)) return true;
            if (c.equals(AG_NUM) || c.contains("." + AG_NUM)) return true;
            if (c.equals(BOM_NUM) || c.contains("." + BOM_NUM)) return true;
            if (c.equals(TVM_NUM) || c.contains("." + TVM_NUM)) return true;
            if (c.equals(OTHER_NUM) || c.contains("." + OTHER_NUM)) return true;
            if (c.equals(AG_PER) || c.contains("." + AG_PER)) return true;
            if (c.equals(BOM_PER) || c.contains("." + BOM_PER)) return true;
            if (c.equals(TVM_PER) || c.contains("." + TVM_PER)) return true;
            if (c.equals(OTHER_PER) || c.contains("." + OTHER_PER)) return true;
            if (c.equals(DEVICE_FAULT_NUM) || c.contains("." + DEVICE_FAULT_NUM)) return true;
            if (c.equals(DEVICE_FAULT_PER) || c.contains("." + DEVICE_FAULT_PER)) return true;
            if (c.equals(NOT_DEVICE_FAULT_NUM) || c.contains("." + NOT_DEVICE_FAULT_NUM)) return true;
            if (c.equals(NOT_DEVICE_FAULT_PER) || c.contains("." + NOT_DEVICE_FAULT_PER)) return true;
        }
        return false;
    }

}
