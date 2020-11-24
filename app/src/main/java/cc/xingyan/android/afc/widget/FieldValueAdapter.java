/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.widget;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import java.util.List;

/**
 * Created by San on 10/8/15.
 */
public class FieldValueAdapter extends ArrayAdapter<FieldValueAdapter.FieldValue> implements Filterable {

    public FieldValueAdapter(Context context, List<FieldValue> objects) {
        super(context, android.R.layout.simple_dropdown_item_1line, objects);
    }

    public static class FieldValue {
        public String code;
        public String value;

        @Override public String toString() {
            return value;
        }
    }
}
