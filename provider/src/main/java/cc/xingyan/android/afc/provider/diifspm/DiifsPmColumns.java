package cc.xingyan.android.afc.provider.diifspm;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.DiifsProvider;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;
import cc.xingyan.android.afc.provider.diifswork.DiifsWorkColumns;

/**
 * Columns for the {@code diifs_pm} table.
 */
public class DiifsPmColumns implements BaseColumns {
    public static final String TABLE_NAME = "diifs_pm";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.diifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String WONO = "WoNo";

    public static final String PMNO = "PmNo";

    public static final String LOGICNAME = "LogicName";

    public static final String PHYSICNAME = "PhysicName";

    public static final String PHYSICCODE = "PhysicCode";

    public static final String ISDONE = "isDone";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            WONO,
            PMNO,
            LOGICNAME,
            PHYSICNAME,
            PHYSICCODE,
            ISDONE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(WONO) || c.contains("." + WONO)) return true;
            if (c.equals(PMNO) || c.contains("." + PMNO)) return true;
            if (c.equals(LOGICNAME) || c.contains("." + LOGICNAME)) return true;
            if (c.equals(PHYSICNAME) || c.contains("." + PHYSICNAME)) return true;
            if (c.equals(PHYSICCODE) || c.contains("." + PHYSICCODE)) return true;
            if (c.equals(ISDONE) || c.contains("." + ISDONE)) return true;
        }
        return false;
    }

}
