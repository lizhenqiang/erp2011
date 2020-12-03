package cc.xingyan.android.afc;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cc.xingyan.android.afc.app.BaseFragment;

/**
 * Created by San on 12/11/15.
 */
public class CmSparePartFragment extends BaseFragment {
    private static final String ARG_PART_ID = "part_id";

    public static final long NEW_SPARE_PART = -1;
    private long mCmSparePartId;

    public static Fragment newInstance(long materialId) {
        CmSparePartFragment fragment = new CmSparePartFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PART_ID, materialId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCmSparePartId = getArguments().getLong(ARG_PART_ID);

        if (NEW_SPARE_PART == mCmSparePartId) {
        } else {
        }
    }
}
