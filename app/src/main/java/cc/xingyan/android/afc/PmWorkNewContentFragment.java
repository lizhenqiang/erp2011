package cc.xingyan.android.afc;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemCursor;
import cc.xingyan.android.afc.provider.pmifsworkitem.PmifsWorkItemSelection;

/**
 * Created by YuJiadeng on 2016/11/17.
 */
public class PmWorkNewContentFragment extends BaseFragment {
    private static final String ARG_PM_ORDER_ID = "pm_order_id";

    private String mPMOrderId;


    @Bind(R.id.pm_work_item_watch_listView) ListView pmWorkItemListView;
    public static Fragment newInstance(String mPMOrderId) {
        PmWorkNewContentFragment fragment = new PmWorkNewContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PM_ORDER_ID, mPMOrderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        mPMOrderId = getArguments().getString(ARG_PM_ORDER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_item_watch_public, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        queryAndBindData();

    }

    private void queryAndBindData() {
        ArrayList<String> pmifsWorkItemList = new ArrayList<>();
        if (mPMOrderId != null) {
            PmifsWorkItemCursor pmifsWorkItemCursor =
                    new PmifsWorkItemSelection()
                            .orderId(mPMOrderId)
                            .query(getContext());

            try {
                while (pmifsWorkItemCursor.moveToNext()) {
                    String pmifsWorkItem = pmifsWorkItemCursor.getItemDes();
                    pmifsWorkItemList.add(pmifsWorkItem);
                }
            } finally {
                pmifsWorkItemCursor.close();
            }

            if (pmifsWorkItemList.size() > 0) {
                PmWorkItemListViewAdapter pmWorkItemListViewAdapter =
                        new PmWorkItemListViewAdapter(getActivity(), pmifsWorkItemList);
                pmWorkItemListView.setAdapter(pmWorkItemListViewAdapter);
            }
        }
    }


    class PmWorkItemListViewAdapter extends BaseAdapter {
        Context context;
        ArrayList<String> pmWorkItemList;
        LayoutInflater inflater;
        public PmWorkItemListViewAdapter(Context context, ArrayList<String> pmWorkItemList){
            inflater = LayoutInflater.from(context);
            this.context = context;
            this.pmWorkItemList = pmWorkItemList;
        }
        @Override
        public int getCount() {
            return pmWorkItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return pmWorkItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_pm_work_new_content_item,
                        parent, false);
                holder = new ViewHolder();
                holder.pmworkitemcount = (TextView) convertView.findViewById(R.id.pmworkitemcount);
                holder.pmWorkItemText = (TextView) convertView.findViewById(R.id.pmworkitemtext);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.pmworkitemcount.setText((position + 1) + ". ");
            holder.pmWorkItemText.setText(pmWorkItemList.get(position));

            return convertView;
        }
    }


    public class ViewHolder {
        public TextView pmworkitemcount;
        public TextView pmWorkItemText;
    }

}
