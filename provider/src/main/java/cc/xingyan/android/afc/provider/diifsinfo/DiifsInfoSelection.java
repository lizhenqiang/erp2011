package cc.xingyan.android.afc.provider.diifsinfo;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code diifs_info} table.
 */
public class DiifsInfoSelection extends AbstractSelection<DiifsInfoSelection> {
    @Override
    protected Uri baseUri() {
        return DiifsInfoColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsInfoCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsInfoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsInfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public DiifsInfoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code DiifsInfoCursor} object, which is positioned before the first entry, or null.
     */
    public DiifsInfoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new DiifsInfoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public DiifsInfoCursor query(Context context) {
        return query(context, null);
    }


    public DiifsInfoSelection id(long... value) {
        addEquals("diifs_info." + DiifsInfoColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsInfoSelection idNot(long... value) {
        addNotEquals("diifs_info." + DiifsInfoColumns._ID, toObjectArray(value));
        return this;
    }

    public DiifsInfoSelection orderById(boolean desc) {
        orderBy("diifs_info." + DiifsInfoColumns._ID, desc);
        return this;
    }

    public DiifsInfoSelection orderById() {
        return orderById(false);
    }

    public DiifsInfoSelection userId(String... value) {
        addEquals(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection userIdNot(String... value) {
        addNotEquals(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection userIdLike(String... value) {
        addLike(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection userIdContains(String... value) {
        addContains(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection userIdStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection userIdEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.USER_ID, value);
        return this;
    }

    public DiifsInfoSelection orderByUserId(boolean desc) {
        orderBy(DiifsInfoColumns.USER_ID, desc);
        return this;
    }

    public DiifsInfoSelection orderByUserId() {
        orderBy(DiifsInfoColumns.USER_ID, false);
        return this;
    }

    public DiifsInfoSelection wono(String... value) {
        addEquals(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection wonoNot(String... value) {
        addNotEquals(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection wonoLike(String... value) {
        addLike(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection wonoContains(String... value) {
        addContains(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection wonoStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection wonoEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.WONO, value);
        return this;
    }

    public DiifsInfoSelection orderByWono(boolean desc) {
        orderBy(DiifsInfoColumns.WONO, desc);
        return this;
    }

    public DiifsInfoSelection orderByWono() {
        orderBy(DiifsInfoColumns.WONO, false);
        return this;
    }

    public DiifsInfoSelection rounddefid(String... value) {
        addEquals(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection rounddefidNot(String... value) {
        addNotEquals(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection rounddefidLike(String... value) {
        addLike(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection rounddefidContains(String... value) {
        addContains(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection rounddefidStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection rounddefidEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.ROUNDDEFID, value);
        return this;
    }

    public DiifsInfoSelection orderByRounddefid(boolean desc) {
        orderBy(DiifsInfoColumns.ROUNDDEFID, desc);
        return this;
    }

    public DiifsInfoSelection orderByRounddefid() {
        orderBy(DiifsInfoColumns.ROUNDDEFID, false);
        return this;
    }

    public DiifsInfoSelection descr(String... value) {
        addEquals(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection descrNot(String... value) {
        addNotEquals(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection descrLike(String... value) {
        addLike(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection descrContains(String... value) {
        addContains(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection descrStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection descrEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.DESCR, value);
        return this;
    }

    public DiifsInfoSelection orderByDescr(boolean desc) {
        orderBy(DiifsInfoColumns.DESCR, desc);
        return this;
    }

    public DiifsInfoSelection orderByDescr() {
        orderBy(DiifsInfoColumns.DESCR, false);
        return this;
    }

    public DiifsInfoSelection requiredstartdate(Long... value) {
        addEquals(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection requiredstartdateNot(Long... value) {
        addNotEquals(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection requiredstartdateGt(long value) {
        addGreaterThan(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection requiredstartdateGtEq(long value) {
        addGreaterThanOrEquals(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection requiredstartdateLt(long value) {
        addLessThan(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection requiredstartdateLtEq(long value) {
        addLessThanOrEquals(DiifsInfoColumns.REQUIREDSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection orderByRequiredstartdate(boolean desc) {
        orderBy(DiifsInfoColumns.REQUIREDSTARTDATE, desc);
        return this;
    }

    public DiifsInfoSelection orderByRequiredstartdate() {
        orderBy(DiifsInfoColumns.REQUIREDSTARTDATE, false);
        return this;
    }

    public DiifsInfoSelection planstartdate(Long... value) {
        addEquals(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection planstartdateNot(Long... value) {
        addNotEquals(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection planstartdateGt(long value) {
        addGreaterThan(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection planstartdateGtEq(long value) {
        addGreaterThanOrEquals(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection planstartdateLt(long value) {
        addLessThan(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection planstartdateLtEq(long value) {
        addLessThanOrEquals(DiifsInfoColumns.PLANSTARTDATE, value);
        return this;
    }

    public DiifsInfoSelection orderByPlanstartdate(boolean desc) {
        orderBy(DiifsInfoColumns.PLANSTARTDATE, desc);
        return this;
    }

    public DiifsInfoSelection orderByPlanstartdate() {
        orderBy(DiifsInfoColumns.PLANSTARTDATE, false);
        return this;
    }

    public DiifsInfoSelection planfinishdate(Long... value) {
        addEquals(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection planfinishdateNot(Long... value) {
        addNotEquals(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection planfinishdateGt(long value) {
        addGreaterThan(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection planfinishdateGtEq(long value) {
        addGreaterThanOrEquals(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection planfinishdateLt(long value) {
        addLessThan(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection planfinishdateLtEq(long value) {
        addLessThanOrEquals(DiifsInfoColumns.PLANFINISHDATE, value);
        return this;
    }

    public DiifsInfoSelection orderByPlanfinishdate(boolean desc) {
        orderBy(DiifsInfoColumns.PLANFINISHDATE, desc);
        return this;
    }

    public DiifsInfoSelection orderByPlanfinishdate() {
        orderBy(DiifsInfoColumns.PLANFINISHDATE, false);
        return this;
    }

    public DiifsInfoSelection signature(String... value) {
        addEquals(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection signatureNot(String... value) {
        addNotEquals(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection signatureLike(String... value) {
        addLike(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection signatureContains(String... value) {
        addContains(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection signatureStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection signatureEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.SIGNATURE, value);
        return this;
    }

    public DiifsInfoSelection orderBySignature(boolean desc) {
        orderBy(DiifsInfoColumns.SIGNATURE, desc);
        return this;
    }

    public DiifsInfoSelection orderBySignature() {
        orderBy(DiifsInfoColumns.SIGNATURE, false);
        return this;
    }

    public DiifsInfoSelection sign(String... value) {
        addEquals(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection signNot(String... value) {
        addNotEquals(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection signLike(String... value) {
        addLike(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection signContains(String... value) {
        addContains(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection signStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection signEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.SIGN, value);
        return this;
    }

    public DiifsInfoSelection orderBySign(boolean desc) {
        orderBy(DiifsInfoColumns.SIGN, desc);
        return this;
    }

    public DiifsInfoSelection orderBySign() {
        orderBy(DiifsInfoColumns.SIGN, false);
        return this;
    }

    public DiifsInfoSelection devicecount(String... value) {
        addEquals(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection devicecountNot(String... value) {
        addNotEquals(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection devicecountLike(String... value) {
        addLike(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection devicecountContains(String... value) {
        addContains(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection devicecountStartsWith(String... value) {
        addStartsWith(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection devicecountEndsWith(String... value) {
        addEndsWith(DiifsInfoColumns.DEVICECOUNT, value);
        return this;
    }

    public DiifsInfoSelection orderByDevicecount(boolean desc) {
        orderBy(DiifsInfoColumns.DEVICECOUNT, desc);
        return this;
    }

    public DiifsInfoSelection orderByDevicecount() {
        orderBy(DiifsInfoColumns.DEVICECOUNT, false);
        return this;
    }

    public DiifsInfoSelection uploadPending(Boolean value) {
        addEquals(DiifsInfoColumns.UPLOAD_PENDING, toObjectArray(value));
        return this;
    }

    public DiifsInfoSelection orderByUploadPending(boolean desc) {
        orderBy(DiifsInfoColumns.UPLOAD_PENDING, desc);
        return this;
    }

    public DiifsInfoSelection orderByUploadPending() {
        orderBy(DiifsInfoColumns.UPLOAD_PENDING, false);
        return this;
    }

    public DiifsInfoSelection isconfirm(Boolean value) {
        addEquals(DiifsInfoColumns.ISCONFIRM, toObjectArray(value));
        return this;
    }

    public DiifsInfoSelection orderByIsconfirm(boolean desc) {
        orderBy(DiifsInfoColumns.ISCONFIRM, desc);
        return this;
    }

    public DiifsInfoSelection orderByIsconfirm() {
        orderBy(DiifsInfoColumns.ISCONFIRM, false);
        return this;
    }

    public DiifsInfoSelection status(DiIfsStatus... value) {
        addEquals(DiifsInfoColumns.STATUS, value);
        return this;
    }

    public DiifsInfoSelection statusNot(DiIfsStatus... value) {
        addNotEquals(DiifsInfoColumns.STATUS, value);
        return this;
    }


    public DiifsInfoSelection orderByStatus(boolean desc) {
        orderBy(DiifsInfoColumns.STATUS, desc);
        return this;
    }

    public DiifsInfoSelection orderByStatus() {
        orderBy(DiifsInfoColumns.STATUS, false);
        return this;
    }
}
