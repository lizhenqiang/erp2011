package cc.xingyan.android.afc.widget;

import java.util.Set;

/**
 * Created by San on 11/18/15.
 */
public interface SelectableAdapter {

    public interface SelectModeListener {
        /**
         * Ask to enter select mode
         *
         * @return <code>true</code> if entered select mode
         */
        boolean requestSelectMode();

        void onSelectedItemsChanged();
    }

    void setSelectModeListener(SelectModeListener listener);

    void setSelectMode(boolean selectMode);

    boolean isSelectMode();

    void selectAll();

    void unselectAll();

    Set<Long> getSelectedItemIds();

    void select(int position);

    void unselect(int position);

    boolean isItemSelected(int position);

    boolean isItemSelectable(int position);

    int getSelectedItemCount();
}
