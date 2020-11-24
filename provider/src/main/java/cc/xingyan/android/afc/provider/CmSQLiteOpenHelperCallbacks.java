package cc.xingyan.android.afc.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

/**
 * Implement your custom database creation or upgrade code here.
 *
 * This file will not be overwritten if you re-run the content provider generator.
 */
public class CmSQLiteOpenHelperCallbacks {
    private static final String TAG = CmSQLiteOpenHelperCallbacks.class.getSimpleName();

    public void onOpen(final Context context, final SQLiteDatabase db) {
        // Insert your db open code here.
    }

    public void onPreCreate(final Context context, final SQLiteDatabase db) {
        // Insert your db creation code here. This is called before your tables are created.
    }

    public void onPostCreate(final Context context, final SQLiteDatabase db) {
        // Insert your db creation code here. This is called after your tables are created.
    }

    public void onUpgrade(final Context context, final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // Insert your upgrading code here.
        int i = oldVersion;
        while (i < newVersion){
            switch (i){
                case 1:
                    db.execSQL("update cm_work set status=2 where status=3");
                    db.execSQL("update cm_work set status=2 where status=4");
                    db.execSQL("update cm_work set status=6 where status=5");
                    break;
                case 2:
                    db.execSQL("update cm_work set status=2 where status=3");
                    db.execSQL("update cm_work set status=2 where status=4");
                    db.execSQL("update cm_work set status=4 where status=5");
                    db.execSQL("update cm_work set status=5 where status=6");
                    break;
                case 3:
                    db.execSQL("alter table cm_work add prepare_man varchar2(100)");
                    db.execSQL("update cm_work set prepare_man=dispose");

                    db.execSQL("alter table cm_material_row add guid varchar2(100)");
                    db.execSQL("update cm_material_row set guid='" + UUID.randomUUID().toString() + "'");

                    db.execSQL("alter table cm_material add guid varchar2(100)");
                    db.execSQL("update cm_material set guid='" + UUID.randomUUID().toString() + "'");
                    break;
                case 4:
                    db.execSQL("alter table cm_work add int_customer_no varchar2(100)");
                    break;
                case 5:
                    db.execSQL("alter table cm_work add form_flag varchar2(10)");
                    break;
                case 6:
                    break;
                case 7:
                    db.execSQL("alter table cm_work add equip_fault varchar2(10)");
                    break;
                default:
                    break;
            }
            i++;
        }
    }
}
