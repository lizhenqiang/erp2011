package cc.xingyan.android.afc.provider.diifsinfo;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.DiifsProvider;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkColumns;

/**
 * Columns for the {@code diifs_info} table.
 */
public class DiifsInfoColumns implements BaseColumns {
    public static final String TABLE_NAME = "diifs_info";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.diifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_ID = "user_id";

    public static final String WONO = "WoNo";

    public static final String ROUNDDEFID = "RounddefId";

    public static final String DESCR = "Descr";

    public static final String REQUIREDSTARTDATE = "RequiredStartDate";

    public static final String PLANSTARTDATE = "PlanStartDate";

    public static final String PLANFINISHDATE = "PlanFinishDate";

    public static final String SIGNATURE = "Signature";

    public static final String SIGN = "Sign";

    public static final String DEVICECOUNT = "DeviceCount";

    public static final String UPLOAD_PENDING = "upload_pending";

    public static final String ISCONFIRM = "isConfirm";

    public static final String STATUS = "status";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_ID,
            WONO,
            ROUNDDEFID,
            DESCR,
            REQUIREDSTARTDATE,
            PLANSTARTDATE,
            PLANFINISHDATE,
            SIGNATURE,
            SIGN,
            DEVICECOUNT,
            UPLOAD_PENDING,
            ISCONFIRM,
            STATUS
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(WONO) || c.contains("." + WONO)) return true;
            if (c.equals(ROUNDDEFID) || c.contains("." + ROUNDDEFID)) return true;
            if (c.equals(DESCR) || c.contains("." + DESCR)) return true;
            if (c.equals(REQUIREDSTARTDATE) || c.contains("." + REQUIREDSTARTDATE)) return true;
            if (c.equals(PLANSTARTDATE) || c.contains("." + PLANSTARTDATE)) return true;
            if (c.equals(PLANFINISHDATE) || c.contains("." + PLANFINISHDATE)) return true;
            if (c.equals(SIGNATURE) || c.contains("." + SIGNATURE)) return true;
            if (c.equals(SIGN) || c.contains("." + SIGN)) return true;
            if (c.equals(DEVICECOUNT) || c.contains("." + DEVICECOUNT)) return true;
            if (c.equals(UPLOAD_PENDING) || c.contains("." + UPLOAD_PENDING)) return true;
            if (c.equals(ISCONFIRM) || c.contains("." + ISCONFIRM)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
        }
        return false;
    }

}
