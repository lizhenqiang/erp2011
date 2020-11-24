package cc.xingyan.android.afc.provider.picture;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import cc.xingyan.android.afc.provider.base.AbstractSelection;

/**
 * Selection for the {@code picture} table.
 */
public class PictureSelection extends AbstractSelection<PictureSelection> {
    @Override
    protected Uri baseUri() {
        return PictureColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PictureCursor} object, which is positioned before the first entry, or null.
     */
    public PictureCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PictureCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PictureCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PictureCursor} object, which is positioned before the first entry, or null.
     */
    public PictureCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PictureCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PictureCursor query(Context context) {
        return query(context, null);
    }


    public PictureSelection id(long... value) {
        addEquals("picture." + PictureColumns._ID, toObjectArray(value));
        return this;
    }

    public PictureSelection idNot(long... value) {
        addNotEquals("picture." + PictureColumns._ID, toObjectArray(value));
        return this;
    }

    public PictureSelection orderById(boolean desc) {
        orderBy("picture." + PictureColumns._ID, desc);
        return this;
    }

    public PictureSelection orderById() {
        return orderById(false);
    }

    public PictureSelection keyId(String... value) {
        addEquals(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection keyIdNot(String... value) {
        addNotEquals(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection keyIdLike(String... value) {
        addLike(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection keyIdContains(String... value) {
        addContains(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection keyIdStartsWith(String... value) {
        addStartsWith(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection keyIdEndsWith(String... value) {
        addEndsWith(PictureColumns.KEY_ID, value);
        return this;
    }

    public PictureSelection orderByKeyId(boolean desc) {
        orderBy(PictureColumns.KEY_ID, desc);
        return this;
    }

    public PictureSelection orderByKeyId() {
        orderBy(PictureColumns.KEY_ID, false);
        return this;
    }

    public PictureSelection type(String... value) {
        addEquals(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection typeNot(String... value) {
        addNotEquals(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection typeLike(String... value) {
        addLike(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection typeContains(String... value) {
        addContains(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection typeStartsWith(String... value) {
        addStartsWith(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection typeEndsWith(String... value) {
        addEndsWith(PictureColumns.TYPE, value);
        return this;
    }

    public PictureSelection orderByType(boolean desc) {
        orderBy(PictureColumns.TYPE, desc);
        return this;
    }

    public PictureSelection orderByType() {
        orderBy(PictureColumns.TYPE, false);
        return this;
    }

    public PictureSelection pictureId(String... value) {
        addEquals(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection pictureIdNot(String... value) {
        addNotEquals(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection pictureIdLike(String... value) {
        addLike(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection pictureIdContains(String... value) {
        addContains(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection pictureIdStartsWith(String... value) {
        addStartsWith(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection pictureIdEndsWith(String... value) {
        addEndsWith(PictureColumns.PICTURE_ID, value);
        return this;
    }

    public PictureSelection orderByPictureId(boolean desc) {
        orderBy(PictureColumns.PICTURE_ID, desc);
        return this;
    }

    public PictureSelection orderByPictureId() {
        orderBy(PictureColumns.PICTURE_ID, false);
        return this;
    }

    public PictureSelection addr(String... value) {
        addEquals(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection addrNot(String... value) {
        addNotEquals(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection addrLike(String... value) {
        addLike(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection addrContains(String... value) {
        addContains(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection addrStartsWith(String... value) {
        addStartsWith(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection addrEndsWith(String... value) {
        addEndsWith(PictureColumns.ADDR, value);
        return this;
    }

    public PictureSelection orderByAddr(boolean desc) {
        orderBy(PictureColumns.ADDR, desc);
        return this;
    }

    public PictureSelection orderByAddr() {
        orderBy(PictureColumns.ADDR, false);
        return this;
    }

    public PictureSelection url(String... value) {
        addEquals(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection urlNot(String... value) {
        addNotEquals(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection urlLike(String... value) {
        addLike(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection urlContains(String... value) {
        addContains(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection urlStartsWith(String... value) {
        addStartsWith(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection urlEndsWith(String... value) {
        addEndsWith(PictureColumns.URL, value);
        return this;
    }

    public PictureSelection orderByUrl(boolean desc) {
        orderBy(PictureColumns.URL, desc);
        return this;
    }

    public PictureSelection orderByUrl() {
        orderBy(PictureColumns.URL, false);
        return this;
    }

    public PictureSelection dateCurrent(Long... value) {
        addEquals(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection dateCurrentNot(Long... value) {
        addNotEquals(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection dateCurrentGt(long value) {
        addGreaterThan(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection dateCurrentGtEq(long value) {
        addGreaterThanOrEquals(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection dateCurrentLt(long value) {
        addLessThan(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection dateCurrentLtEq(long value) {
        addLessThanOrEquals(PictureColumns.DATE_CURRENT, value);
        return this;
    }

    public PictureSelection orderByDateCurrent(boolean desc) {
        orderBy(PictureColumns.DATE_CURRENT, desc);
        return this;
    }

    public PictureSelection orderByDateCurrent() {
        orderBy(PictureColumns.DATE_CURRENT, false);
        return this;
    }

    public PictureSelection remark(String... value) {
        addEquals(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection remarkNot(String... value) {
        addNotEquals(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection remarkLike(String... value) {
        addLike(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection remarkContains(String... value) {
        addContains(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection remarkStartsWith(String... value) {
        addStartsWith(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection remarkEndsWith(String... value) {
        addEndsWith(PictureColumns.REMARK, value);
        return this;
    }

    public PictureSelection orderByRemark(boolean desc) {
        orderBy(PictureColumns.REMARK, desc);
        return this;
    }

    public PictureSelection orderByRemark() {
        orderBy(PictureColumns.REMARK, false);
        return this;
    }
}
