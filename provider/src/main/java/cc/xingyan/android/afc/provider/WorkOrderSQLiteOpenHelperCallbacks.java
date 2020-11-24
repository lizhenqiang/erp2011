package cc.xingyan.android.afc.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

/**
 * Implement your custom database creation or upgrade code here.
 *
 * This file will not be overwritten if you re-run the content provider generator.
 */
public class WorkOrderSQLiteOpenHelperCallbacks {
    private static final String TAG = WorkOrderSQLiteOpenHelperCallbacks.class.getSimpleName();

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
                    db.execSQL(WorkOrderSQLiteOpenHelper.SQL_CREATE_TABLE_DEVICE_PHYSICS);
                    break;
                case 2:
                    db.execSQL("alter table workorder add form_flag_text varchar2(10)");
                    db.execSQL("alter table workorder add form_flag_code varchar2(10)");
                    db.execSQL("update workorder set form_flag='1'");
                    break;
                default:
                    break;
            }
            i++;
        }
    }
}
