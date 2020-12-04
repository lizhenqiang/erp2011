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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowCursor;
import cc.xingyan.android.afc.provider.pmmaterialrow.PmMaterialRowSelection;

/**
 * Created by YuJiadeng on 2016/11/13.
 */
public class PmWorkNewMaterialFragment extends BaseFragment {
    private static final String ARG_PM_ORDER_ID = "pm_order_id";

    private String mPMOrderId;

    @Bind(R.id.pm_work_item_watch_listView) ListView pmFaultMaterialListView;
    public static Fragment newInstance(String mPMOrderId) {
        PmWorkNewMaterialFragment fragment = new PmWorkNewMaterialFragment();
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
        if (mPMOrderId != null) {
            final PmMaterialRowCursor c = new PmMaterialRowSelection().pmOrderId(mPMOrderId).query(getContext());
            ArrayList<PmFaultMaterialItem> pmFaultMaterialItemList = new ArrayList<PmFaultMaterialItem>();
            DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                while (c.moveToNext()){
                    PmFaultMaterialItem pmFaultMaterialItem = new PmFaultMaterialItem();
                    String partdescription = c.getMaterialDescription();
                    String quantityrequired = Integer.toString(c.getMaterialCount() == null ? 0 : c.getMaterialCount());
                    String daterequired = DATE_FORMAT.format(c.getDueDate());

                    pmFaultMaterialItem.setPartdescription(partdescription);
                    pmFaultMaterialItem.setQuantityrequired(quantityrequired);
                    pmFaultMaterialItem.setDaterequired(daterequired);
                    pmFaultMaterialItemList.add(pmFaultMaterialItem);
                }
            } finally {
                c.close();
            }

            if(pmFaultMaterialItemList.size() > 0){
                PmFaultMaterialListViewAdapter pmFaultMaterialListViewAdapter =
                        new PmFaultMaterialListViewAdapter(getActivity(), pmFaultMaterialItemList);
                pmFaultMaterialListView.setAdapter(pmFaultMaterialListViewAdapter);
            }

        }
    }


     class PmFaultMaterialListViewAdapter extends BaseAdapter{
         Context context;
         ArrayList<PmFaultMaterialItem> pmFaultMaterialItemList;
         LayoutInflater inflater;
         public PmFaultMaterialListViewAdapter(Context context, ArrayList<PmFaultMaterialItem> pmFaultMaterialItemList){
             inflater = LayoutInflater.from(context);
             this.context = context;
             this.pmFaultMaterialItemList = pmFaultMaterialItemList;
         }
         @Override
         public int getCount() {
             return pmFaultMaterialItemList.size();
         }

         @Override
         public Object getItem(int position) {
             return pmFaultMaterialItemList.get(position);
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             ViewHolder holder = null;
             if (convertView == null) {
                 convertView = inflater.inflate(R.layout.fragment_pm_work_new_material_item,
                         parent, false);
                 holder = new ViewHolder();
                 holder.partdescriptionTvw = (TextView) convertView
                         .findViewById(R.id.partdescription);
                 holder.quantityrequiredTvw = (TextView) convertView
                         .findViewById(R.id.quantityrequired);
                 holder.daterequiredTvw = (TextView) convertView
                         .findViewById(R.id.daterequired);
                 convertView.setTag(holder);
             } else {
                 holder = (ViewHolder) convertView.getTag();
             }

             holder.partdescriptionTvw.setText(pmFaultMaterialItemList.get(position).getPartdescription());
             holder.quantityrequiredTvw.setText(pmFaultMaterialItemList.get(position).getQuantityrequired());
             holder.daterequiredTvw.setText(pmFaultMaterialItemList.get(position).getDaterequired());

             return convertView;
         }
     }


    public class ViewHolder {
        public TextView partdescriptionTvw;
        public TextView quantityrequiredTvw;
        public TextView daterequiredTvw;
    }

    class PmFaultMaterialItem {
        String partdescription;
        String quantityrequired;
        String daterequired;

        public String getDaterequired() {
            return daterequired;
        }

        public void setDaterequired(String daterequired) {
            this.daterequired = daterequired;
        }

        public String getPartdescription() {
            return partdescription;
        }

        public void setPartdescription(String partdescription) {
            this.partdescription = partdescription;
        }

        public String getQuantityrequired() {
            return quantityrequired;
        }

        public void setQuantityrequired(String quantityrequired) {
            this.quantityrequired = quantityrequired;
        }
    }


}
