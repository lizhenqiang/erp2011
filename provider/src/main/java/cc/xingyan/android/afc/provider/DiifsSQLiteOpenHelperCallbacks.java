package cc.xingyan.android.afc.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Implement your custom database creation or upgrade code here.
 *
 * This file will not be overwritten if you re-run the content provider generator.
 */
public class DiifsSQLiteOpenHelperCallbacks {
    private static final String TAG = DiifsSQLiteOpenHelperCallbacks.class.getSimpleName();

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

        while (i < newVersion) {
            switch (i) {
                case 1:

                    db.execSQL("alter table diifs_pm_item add ObjId TEXT");
                    db.execSQL("alter table diifs_pm_item add ObjVersion TEXT");
                    db.execSQL("alter table diifs_pm_item add WoNo TEXT");

                    StringBuilder sb0 = new StringBuilder();
                    sb0.append(" UPDATE diifs_pm_item ");
                    sb0.append(" SET Wono = ");
                    sb0.append(" ( SELECT b.Wono FROM diifs_pm b WHERE diifs_pm_item.PmNo = b.PmNo ) ");
                    sb0.append(" WHERE EXISTS ");
                    sb0.append(" ( SELECT 1 FROM diifs_pm b WHERE diifs_pm_item.PmNo = b.PmNo ) ");
                    db.execSQL(sb0.toString());



                    db.execSQL("alter table diifs_work add ObjId TEXT");
                    db.execSQL("alter table diifs_work add ObjVersion TEXT");
                    db.execSQL("alter table diifs_work add isUpdate INTEGER");
                    db.execSQL("alter table diifs_work add Physiccode TEXT");




                    db.execSQL(" ALTER TABLE diifs_work RENAME TO temp_diifs_work ");

                    db.execSQL(DiifsSQLiteOpenHelper.SQL_CREATE_TABLE_DIIFS_WORK);

                    StringBuilder sb = new StringBuilder();
                    sb.append("INSERT INTO ");
                    sb.append("diifs_work");
                    sb.append("(WoNo, PmNo, TestPointId, ParameterCode, ParameterDesc, ParameterType, Unit, MinValue, MaxValue, StartValue, Interval, LastValue, PmgenValue, ObjId, ObjVersion, isUpdate, PhysicCode)");
                    sb.append(" SELECT ");
                    sb.append("WoNo, PmNo, TestPointId, ParameterCode, ParameterDesc, ParameterType, Unit, MinValue, MaxValue, StartValue, Interval, LastValue, PmgenValue, ObjId, ObjVersion, isUpdate, PhysicCode");
                    sb.append(" FROM ");
                    sb.append("temp_diifs_work");
                    db.execSQL(sb.toString());

                    db.execSQL(" DROP TABLE temp_diifs_work ");

                    db.execSQL("update diifs_work set isUpdate='0'");

                    break;
                case 2:


                    break;
                default:
                    break;
            }
            i++;
        }
    }
}
