package cc.xingyan.android.afc.provider.cmspareparts;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code cm_spare_parts} table.
 */
public class CmSparePartsSelection extends AbstractSelection<CmSparePartsSelection> {
    @Override
    protected Uri baseUri() {
        return CmSparePartsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmSparePartsCursor} object, which is positioned before the first entry, or null.
     */
    public CmSparePartsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmSparePartsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CmSparePartsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CmSparePartsCursor} object, which is positioned before the first entry, or null.
     */
    public CmSparePartsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CmSparePartsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CmSparePartsCursor query(Context context) {
        return query(context, null);
    }


    public CmSparePartsSelection id(long... value) {
        addEquals("cm_spare_parts." + CmSparePartsColumns._ID, toObjectArray(value));
        return this;
    }

    public CmSparePartsSelection idNot(long... value) {
        addNotEquals("cm_spare_parts." + CmSparePartsColumns._ID, toObjectArray(value));
        return this;
    }

    public CmSparePartsSelection orderById(boolean desc) {
        orderBy("cm_spare_parts." + CmSparePartsColumns._ID, desc);
        return this;
    }

    public CmSparePartsSelection orderById() {
        return orderById(false);
    }

    public CmSparePartsSelection cmWorkId(String... value) {
        addEquals(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection cmWorkIdNot(String... value) {
        addNotEquals(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection cmWorkIdLike(String... value) {
        addLike(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection cmWorkIdContains(String... value) {
        addContains(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection cmWorkIdStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection cmWorkIdEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.CM_WORK_ID, value);
        return this;
    }

    public CmSparePartsSelection orderByCmWorkId(boolean desc) {
        orderBy(CmSparePartsColumns.CM_WORK_ID, desc);
        return this;
    }

    public CmSparePartsSelection orderByCmWorkId() {
        orderBy(CmSparePartsColumns.CM_WORK_ID, false);
        return this;
    }

    public CmSparePartsSelection orderId(String... value) {
        addEquals(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderIdNot(String... value) {
        addNotEquals(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderIdLike(String... value) {
        addLike(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderIdContains(String... value) {
        addContains(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderIdStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderIdEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.ORDER_ID, value);
        return this;
    }

    public CmSparePartsSelection orderByOrderId(boolean desc) {
        orderBy(CmSparePartsColumns.ORDER_ID, desc);
        return this;
    }

    public CmSparePartsSelection orderByOrderId() {
        orderBy(CmSparePartsColumns.ORDER_ID, false);
        return this;
    }

    public CmSparePartsSelection partId(String... value) {
        addEquals(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection partIdNot(String... value) {
        addNotEquals(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection partIdLike(String... value) {
        addLike(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection partIdContains(String... value) {
        addContains(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection partIdStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection partIdEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.PART_ID, value);
        return this;
    }

    public CmSparePartsSelection orderByPartId(boolean desc) {
        orderBy(CmSparePartsColumns.PART_ID, desc);
        return this;
    }

    public CmSparePartsSelection orderByPartId() {
        orderBy(CmSparePartsColumns.PART_ID, false);
        return this;
    }

    public CmSparePartsSelection partDescription(String... value) {
        addEquals(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection partDescriptionNot(String... value) {
        addNotEquals(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection partDescriptionLike(String... value) {
        addLike(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection partDescriptionContains(String... value) {
        addContains(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection partDescriptionStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection partDescriptionEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.PART_DESCRIPTION, value);
        return this;
    }

    public CmSparePartsSelection orderByPartDescription(boolean desc) {
        orderBy(CmSparePartsColumns.PART_DESCRIPTION, desc);
        return this;
    }

    public CmSparePartsSelection orderByPartDescription() {
        orderBy(CmSparePartsColumns.PART_DESCRIPTION, false);
        return this;
    }

    public CmSparePartsSelection applyDate(Integer... value) {
        addEquals(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection applyDateNot(Integer... value) {
        addNotEquals(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection applyDateGt(int value) {
        addGreaterThan(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection applyDateGtEq(int value) {
        addGreaterThanOrEquals(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection applyDateLt(int value) {
        addLessThan(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection applyDateLtEq(int value) {
        addLessThanOrEquals(CmSparePartsColumns.APPLY_DATE, value);
        return this;
    }

    public CmSparePartsSelection orderByApplyDate(boolean desc) {
        orderBy(CmSparePartsColumns.APPLY_DATE, desc);
        return this;
    }

    public CmSparePartsSelection orderByApplyDate() {
        orderBy(CmSparePartsColumns.APPLY_DATE, false);
        return this;
    }

    public CmSparePartsSelection installDate(Integer... value) {
        addEquals(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection installDateNot(Integer... value) {
        addNotEquals(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection installDateGt(int value) {
        addGreaterThan(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection installDateGtEq(int value) {
        addGreaterThanOrEquals(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection installDateLt(int value) {
        addLessThan(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection installDateLtEq(int value) {
        addLessThanOrEquals(CmSparePartsColumns.INSTALL_DATE, value);
        return this;
    }

    public CmSparePartsSelection orderByInstallDate(boolean desc) {
        orderBy(CmSparePartsColumns.INSTALL_DATE, desc);
        return this;
    }

    public CmSparePartsSelection orderByInstallDate() {
        orderBy(CmSparePartsColumns.INSTALL_DATE, false);
        return this;
    }

    public CmSparePartsSelection oldPartSn(String... value) {
        addEquals(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection oldPartSnNot(String... value) {
        addNotEquals(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection oldPartSnLike(String... value) {
        addLike(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection oldPartSnContains(String... value) {
        addContains(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection oldPartSnStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection oldPartSnEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.OLD_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection orderByOldPartSn(boolean desc) {
        orderBy(CmSparePartsColumns.OLD_PART_SN, desc);
        return this;
    }

    public CmSparePartsSelection orderByOldPartSn() {
        orderBy(CmSparePartsColumns.OLD_PART_SN, false);
        return this;
    }

    public CmSparePartsSelection newPartSn(String... value) {
        addEquals(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection newPartSnNot(String... value) {
        addNotEquals(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection newPartSnLike(String... value) {
        addLike(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection newPartSnContains(String... value) {
        addContains(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection newPartSnStartsWith(String... value) {
        addStartsWith(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection newPartSnEndsWith(String... value) {
        addEndsWith(CmSparePartsColumns.NEW_PART_SN, value);
        return this;
    }

    public CmSparePartsSelection orderByNewPartSn(boolean desc) {
        orderBy(CmSparePartsColumns.NEW_PART_SN, desc);
        return this;
    }

    public CmSparePartsSelection orderByNewPartSn() {
        orderBy(CmSparePartsColumns.NEW_PART_SN, false);
        return this;
    }

    public CmSparePartsSelection sparePartStatus(Integer... value) {
        addEquals(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection sparePartStatusNot(Integer... value) {
        addNotEquals(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection sparePartStatusGt(int value) {
        addGreaterThan(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection sparePartStatusGtEq(int value) {
        addGreaterThanOrEquals(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection sparePartStatusLt(int value) {
        addLessThan(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection sparePartStatusLtEq(int value) {
        addLessThanOrEquals(CmSparePartsColumns.SPARE_PART_STATUS, value);
        return this;
    }

    public CmSparePartsSelection orderBySparePartStatus(boolean desc) {
        orderBy(CmSparePartsColumns.SPARE_PART_STATUS, desc);
        return this;
    }

    public CmSparePartsSelection orderBySparePartStatus() {
        orderBy(CmSparePartsColumns.SPARE_PART_STATUS, false);
        return this;
    }
}
