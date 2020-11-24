package cc.xingyan.android.afc.provider.diifswork;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.DiifsProvider;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoColumns;
import cc.xingyan.android.afc.provider.diifspm.DiifsPmColumns;
import cc.xingyan.android.afc.provider.diifspmitem.DiifsPmItemColumns;

/**
 * Columns for the {@code diifs_work} table.
 */
public class DiifsWorkColumns implements BaseColumns {
    public static final String TABLE_NAME = "diifs_work";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.diifs/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String WONO = "WoNo";

    public static final String PMNO = "PmNo";

    public static final String TESTPOINTID = "TestPointId";

    public static final String PARAMETERCODE = "ParameterCode";

    public static final String PARAMETERDESC = "ParameterDesc";

    public static final String PARAMETERTYPE = "ParameterType";

    public static final String UNIT = "Unit";

    public static final String MINVALUE = "MinValue";

    public static final String MAXVALUE = "MaxValue";

    public static final String STARTVALUE = "StartValue";

    public static final String INTERVAL = "Interval";

    public static final String LASTVALUE = "LastValue";

    public static final String PMGENVALUE = "PmgenValue";

    public static final String OBJID = "ObjId";

    public static final String OBJVERSION = "ObjVersion";

    public static final String ISUPDATE = "isUpdate";

    public static final String PHYSICCODE = "PhysicCode";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            WONO,
            PMNO,
            TESTPOINTID,
            PARAMETERCODE,
            PARAMETERDESC,
            PARAMETERTYPE,
            UNIT,
            MINVALUE,
            MAXVALUE,
            STARTVALUE,
            INTERVAL,
            LASTVALUE,
            PMGENVALUE,
            OBJID,
            OBJVERSION,
            ISUPDATE,
            PHYSICCODE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(WONO) || c.contains("." + WONO)) return true;
            if (c.equals(PMNO) || c.contains("." + PMNO)) return true;
            if (c.equals(TESTPOINTID) || c.contains("." + TESTPOINTID)) return true;
            if (c.equals(PARAMETERCODE) || c.contains("." + PARAMETERCODE)) return true;
            if (c.equals(PARAMETERDESC) || c.contains("." + PARAMETERDESC)) return true;
            if (c.equals(PARAMETERTYPE) || c.contains("." + PARAMETERTYPE)) return true;
            if (c.equals(UNIT) || c.contains("." + UNIT)) return true;
            if (c.equals(MINVALUE) || c.contains("." + MINVALUE)) return true;
            if (c.equals(MAXVALUE) || c.contains("." + MAXVALUE)) return true;
            if (c.equals(STARTVALUE) || c.contains("." + STARTVALUE)) return true;
            if (c.equals(INTERVAL) || c.contains("." + INTERVAL)) return true;
            if (c.equals(LASTVALUE) || c.contains("." + LASTVALUE)) return true;
            if (c.equals(PMGENVALUE) || c.contains("." + PMGENVALUE)) return true;
            if (c.equals(OBJID) || c.contains("." + OBJID)) return true;
            if (c.equals(OBJVERSION) || c.contains("." + OBJVERSION)) return true;
            if (c.equals(ISUPDATE) || c.contains("." + ISUPDATE)) return true;
            if (c.equals(PHYSICCODE) || c.contains("." + PHYSICCODE)) return true;
        }
        return false;
    }

}
