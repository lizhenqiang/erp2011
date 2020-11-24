package cc.xingyan.android.afc.util;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.view.ActionMode;

import java.util.Set;

import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.widget.SelectableAdapter;

/**
 * Created by San on 11/20/15.
 */
public abstract class UploadActionModeCallback implements ActionMode.Callback {
    protected final MenuInflater menuInflater;
    protected final SelectableAdapter adapter;

    public UploadActionModeCallback(MenuInflater menuInflater, SelectableAdapter selectableAdapter) {
        this.menuInflater = menuInflater;
        this.adapter = selectableAdapter;
    }

    @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        menuInflater.inflate(R.menu.action_mode, menu);
        adapter.setSelectMode(true);
        return true;
    }

    @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_select_all:
                adapter.selectAll();
                break;
            case R.id.action_unselect_all:
                adapter.unselectAll();
                break;
            case R.id.action_upload:
                onUpload(adapter.getSelectedItemIds());
                break;
        }
        return true;
    }

    @Override public void onDestroyActionMode(ActionMode mode) {
        adapter.setSelectMode(false);
    }

    protected abstract void onUpload(Set<Long> itemIds);
}
