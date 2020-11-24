package cc.xingyan.android.afc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cc.xingyan.android.afc.R;
import cc.xingyan.android.afc.api.model.PartYunshuGetHeadInfoReturn;

/**
 * Created by YuJiadeng on 2017/12/20.
 *
 */
public class LvAdapter extends BaseAdapter {
    private List<PartYunshuGetHeadInfoReturn> list;
    private Context context;
    LayoutInflater inflater;

    public LvAdapter(List<PartYunshuGetHeadInfoReturn> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        YunshuHeadInfoHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.part__yunshu_uploaded_head_adapter_item, parent, false);
            holder = new YunshuHeadInfoHolder();
            holder.taskId = (TextView) convertView.findViewById(R.id.yunshu_uploaded_head_adapter_id_tx);
            holder.createDate = (TextView) convertView.findViewById(R.id.yunshu_uploaded_head_adapter_date_tx);

            holder.packNumber = (TextView) convertView.findViewById(R.id.yunshu_uploaded_head_adapter_packnumber_tx);
            holder.fromName = (TextView) convertView.findViewById(R.id.yunshu_uploaded_head_adapter_from_tx);
            holder.toName = (TextView) convertView.findViewById(R.id.yunshu_uploaded_head_adapter_to_tx);
            convertView.setTag(holder);
        } else {
            holder = (YunshuHeadInfoHolder) convertView.getTag();
        }


        holder.taskId.setText(list.get(position).getTaskId());


        long createDate = list.get(position).getCreateDate().getTime();
        SimpleDateFormat sdfCreateDate = new SimpleDateFormat("yyyy/MM/dd");
        String transportPlanDate = sdfCreateDate.format(createDate);
        holder.createDate.setText(transportPlanDate);


        holder.packNumber.setText(list.get(position).getPackNumber());
        holder.fromName.setText(list.get(position).getFromName());
        holder.toName.setText(list.get(position).getToName());

        return convertView;
    }


    public class YunshuHeadInfoHolder {
        public TextView taskId;
        public TextView createDate;

        public TextView packNumber;
        public TextView fromName;
        public TextView toName;
    }
}