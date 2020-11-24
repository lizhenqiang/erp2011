package cc.xingyan.android.afc.provider.picture;

import android.net.Uri;
import android.provider.BaseColumns;

import cc.xingyan.android.afc.provider.pictureProvider;

/**
 * Columns for the {@code picture} table.
 */
public class PictureColumns implements BaseColumns {
    public static final String TABLE_NAME = "picture";
    public static final Uri CONTENT_URI = Uri.parse("content://com.biizy.android.erg.provider.picture/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String KEY_ID = "key_id";

    public static final String TYPE = "type";

    public static final String PICTURE_ID = "picture_id";

    public static final String ADDR = "addr";

    public static final String URL = "url";

    public static final String DATE_CURRENT = "date_current";

    public static final String REMARK = "remark";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            KEY_ID,
            TYPE,
            PICTURE_ID,
            ADDR,
            URL,
            DATE_CURRENT,
            REMARK
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(KEY_ID) || c.contains("." + KEY_ID)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(PICTURE_ID) || c.contains("." + PICTURE_ID)) return true;
            if (c.equals(ADDR) || c.contains("." + ADDR)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
            if (c.equals(DATE_CURRENT) || c.contains("." + DATE_CURRENT)) return true;
            if (c.equals(REMARK) || c.contains("." + REMARK)) return true;
        }
        return false;
    }

}
