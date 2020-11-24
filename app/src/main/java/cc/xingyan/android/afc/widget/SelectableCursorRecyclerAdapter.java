package cc.xingyan.android.afc.widget;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by San on 11/20/15.
 */
public abstract class SelectableCursorRecyclerAdapter<C extends Cursor, VH extends SelectableCursorRecyclerAdapter.SelectableViewHolder<C>> extends CursorRecyclerAdapter<C, VH> implements SelectableAdapter {

    private SelectModeListener selectModeListener;
    private SelectHelper selectHelper = new SelectHelper();
    private boolean selectMode;

    private SelectableViewHolder.OnSelectChangeListener onSelectChangeAdapter = (position, selected) -> {
        if (selected) {
            select(position);
        } else {
            unselect(position);
        }
    };

    @Override protected void onBindViewHolder(VH holder, C c, int position) {
        holder.setOnSelectedChangeListener(null);
        final boolean selectable = isItemSelectable(position);
        final boolean selected = isItemSelected(position);
        holder.setItem(c, selectable, selected, isSelectMode());
        if (selectable) {
            holder.setOnSelectedChangeListener(onSelectChangeAdapter);
        }
    }

    protected SelectableCursorRecyclerAdapter(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    public void setSelectModeListener(SelectModeListener listener) {
        this.selectModeListener = listener;
    }

    @Override public void setSelectMode(boolean selectMode) {
        if (this.selectMode != selectMode) {
            this.selectMode = selectMode;
            if (!this.selectMode) {
                selectHelper.unselectAll();
            }
            notifyDataSetChanged();
        }
    }

    @Override public boolean isSelectMode() {
        return selectMode;
    }

    @Override public Set<Long> getSelectedItemIds() {
        return selectHelper.getSelectedItemIds();
    }

    @Override public int getSelectedItemCount() {
        return selectHelper.getSelectedItemCount();
    }

    @Override public void selectAll() {
        selectHelper.selectAll();
        if (selectModeListener != null) {
            selectModeListener.onSelectedItemsChanged();
        }
    }

    @Override public void unselectAll() {
        selectHelper.unselectAll();
        if (selectModeListener != null) {
            selectModeListener.onSelectedItemsChanged();
        }
    }

    @Override public void select(int position) {
        if (!selectMode && selectModeListener != null) {
            selectModeListener.requestSelectMode();
        }
        if (selectMode) {
            selectHelper.select(getItemId(position));
            selectModeListener.onSelectedItemsChanged();
        }
    }

    @Override public void unselect(int position) {
        selectHelper.unselect(getItemId(position));
        if (selectModeListener != null) {
            selectModeListener.onSelectedItemsChanged();
        }
    }

    @Override public boolean isItemSelected(int position) {
        return selectMode && selectHelper.isSelected(getItemId(position));
    }

    private class SelectHelper {
        private Set<Long> set = new HashSet<>();

        void selectAll() {
            for (int i = 0; i < getItemCount(); i++) {
                set.add(getItemId(i));
            }
            notifyDataSetChanged();
        }

        void unselectAll() {
            set.clear();
            notifyDataSetChanged();
        }

        boolean isSelected(long itemId) {
            return set.contains(itemId);
        }

        void select(long itemId) {
            set.add(itemId);
        }

        void unselect(long itemId) {
            set.remove(itemId);
        }

        public Set<Long> getSelectedItemIds() {
            return ImmutableSet.copyOf(set);
        }

        public int getSelectedItemCount() {
            return set.size();
        }
    }

    public static abstract class SelectableViewHolder<C> extends ViewHolder<C> {
        public interface OnSelectChangeListener {
            void onSelectChanged(int position, boolean selected);
        }

        private OnSelectChangeListener onSelectChangeListener;

        public SelectableViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setItem(C c, boolean selectable, boolean selected, boolean selectMode);

        @Override public final void setItem(C c) {
        }

        public void setOnSelectedChangeListener(OnSelectChangeListener listener) {
            this.onSelectChangeListener = listener;
        }

        public final void notifySelectChanged(boolean selected) {
            if (onSelectChangeListener != null) {
                onSelectChangeListener.onSelectChanged(getAdapterPosition(), selected);
            }
        }
    }
}
