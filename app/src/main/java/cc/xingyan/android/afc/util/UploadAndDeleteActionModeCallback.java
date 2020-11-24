package cc.xingyan.android.afc.util;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.view.ActionMode;

import java.util.Set;

import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.widget.SelectableAdapter;

/**
 * Created by San on 11/23/15.
 */
public abstract class UploadAndDeleteActionModeCallback extends UploadActionModeCallback {

    public UploadAndDeleteActionModeCallback(MenuInflater menuInflater, SelectableAdapter selectableAdapter) {
        super(menuInflater, selectableAdapter);
    }

    @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        try {
            return super.onCreateActionMode(mode, menu);
        } finally {
            menuInflater.inflate(R.menu.delete, menu);
        }
    }

    @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            onDelete(adapter.getSelectedItemIds());
            return true;
        } else {
            return super.onActionItemClicked(mode, item);
        }
    }

    protected abstract void onDelete(Set<Long> selectedItemIds);
}
