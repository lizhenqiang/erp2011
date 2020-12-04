package cc.xingyan.android.afc;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.Bind;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;

/**
 * Created by YuJiadeng on 2016/11/22.
 */
public class PmWorkUploadImageFragment extends BaseFragment {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String ARG_PM_ORDER_ID = "pm_order_id";
    private String mPMOrderId;


    @Bind(R.id.pm_work_item_upload_before_work_uploaded_count)
    TextView upload_before_workTextView;

    @Bind(R.id.pm_work_item_upload_before_work_gridview)
    GridView upload_before_workGridview;

    @Bind(R.id.pm_work_item_before_work_upload_layout)
    RelativeLayout pm_work_item_before_work_upPicLayout;


    @Bind(R.id.pm_work_item_upload_after_work_uploaded_count)
    TextView upload_after_workTextView;

    @Bind(R.id.pm_work_item_upload_after_work_gridview)
    GridView upload_after_workGridview;

    @Bind(R.id.pm_work_item_after_work_upload_layout)
    RelativeLayout pm_work_item_after_work_upPicLayout;

    private GridAdapter adapter;
    private ArrayList<String> pm_work_item_upload_before_work_uploadedPicAddrs;
    private ArrayList<String> pm_work_item_upload_after_work_uploadedPicAddrs;

    public static Fragment newInstance(String mPMOrderId) {
        PmWorkUploadImageFragment fragment = new PmWorkUploadImageFragment();
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
        return inflater.inflate(R.layout.fragment_pm_work_upload_image, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        queryAndBindData();


        upload_before_workGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                gridViewOnItemClick(pm_work_item_upload_before_work_uploadedPicAddrs, position);
            }
        });

        upload_after_workGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                gridViewOnItemClick(pm_work_item_upload_after_work_uploadedPicAddrs, position);

            }
        });

    }


    private void queryAndBindData() {
        PictureCursor pm_work_item_upload_before_work_pictureCursor = new PictureSelection()
                .keyId(mPMOrderId)
                .and()
                .type("P")
                .and()
                .remark("0")
                .query(getContext());

        try {
            gridViewBindData(pm_work_item_upload_before_work_uploadedPicAddrs,
                    pm_work_item_upload_before_work_pictureCursor,
                    upload_before_workTextView, upload_before_workGridview,
                    pm_work_item_before_work_upPicLayout, 0);
        } finally {
            pm_work_item_upload_before_work_pictureCursor.close();
        }


        PictureCursor pm_work_item_upload_after_work_pictureCursor = new PictureSelection()
                .keyId(mPMOrderId)
                .and()
                .type("P")
                .and()
                .remark("1")
                .query(getContext());

        try {
            gridViewBindData(pm_work_item_upload_after_work_uploadedPicAddrs,
                    pm_work_item_upload_after_work_pictureCursor,
                    upload_after_workTextView,
                    upload_after_workGridview,
                    pm_work_item_after_work_upPicLayout, 1);
        } finally {
            pm_work_item_upload_after_work_pictureCursor.close();
        }

    }



    private void gridViewOnItemClick(ArrayList<String> uploadedPicAddrs, int position) {
        String picPath = uploadedPicAddrs.get(position);


        String columns[] = new String[]{MediaStore.Images.Media.DATA};

        ContentResolver cr = getContext().getContentResolver();
        Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);


        ArrayList<String> albumPathList = new ArrayList<String>();
        while (cur.moveToNext()) {
            String albumPath = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            albumPathList.add(albumPath);
        }

        if (!picPath.contains("default_pic")) {
            if (albumPathList.contains(picPath)) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + picPath), "image/*");
                startActivity(intent);
            } else {
                new android.app.AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("该图片在本地已经删除！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false)
                        .show();
            }
        }
    }



    private void gridViewBindData(ArrayList<String> uploaded_PicAddrs, PictureCursor pictureCursor,
                                  TextView uploaded_TextView, GridView uploaded_Gridview, RelativeLayout uploaded_Layout, int remark){
            if(uploaded_PicAddrs == null){
                uploaded_PicAddrs = new ArrayList<String>();
            }

            int pm_work_item_upload_after_work_uploadedCount = pictureCursor.getCount();

            if(pm_work_item_upload_after_work_uploadedCount > 0){
                uploaded_TextView.setText(pm_work_item_upload_after_work_uploadedCount + "");
            }

            while(pictureCursor.moveToNext()){
                uploaded_PicAddrs.add(pictureCursor.getPictureId());
            }


            if(uploaded_PicAddrs.size() > 0 && uploaded_PicAddrs.size() < 9){
                for(int i = uploaded_PicAddrs.size(); i < 9; i++){
                    uploaded_PicAddrs.add("default_pic");
                }
            }


            if(uploaded_PicAddrs != null && uploaded_PicAddrs.size() > 0){

                uploaded_Gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
                adapter = new GridAdapter(uploaded_PicAddrs, getActivity());
                uploaded_Gridview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                uploaded_Layout.setVisibility(View.VISIBLE);
            }else{
                uploaded_Layout.setVisibility(View.GONE);
            }

        if(remark == 0){
            pm_work_item_upload_before_work_uploadedPicAddrs = uploaded_PicAddrs;
        }else if(remark == 1){
            pm_work_item_upload_after_work_uploadedPicAddrs = uploaded_PicAddrs;
        }
    }

    public class GridAdapter extends BaseAdapter {

        Context context;
        ArrayList<String> uploadedPicAddrs;
        LayoutInflater inflater;

        public GridAdapter(ArrayList<String>uploadedPicAddrs, Context context) {
            this.uploadedPicAddrs = uploadedPicAddrs;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        public int getCount() {
            return uploadedPicAddrs.size();
        }

        public Object getItem(int position) {
            return uploadedPicAddrs.get(position);
        }

        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.fragment_cm_report_photo_item,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (SimpleDraweeView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String picUri = uploadedPicAddrs.get(position);
            String columns[] = new String[] { MediaStore.Images.Media.DATA};

            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);


            ArrayList<String> albumPathList = new ArrayList<String>();
            while(cur.moveToNext()){
                String albumPath = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                albumPathList.add(albumPath);
            }

            if(picUri.contains("default_pic")){
                Uri uri = Uri.parse("res://cc.xingyan.android.afc/" + R.drawable.icon_addpic_unfocused);
                holder.image.setImageURI(uri);
            }else {
                if(albumPathList.contains(picUri)){
                    Uri uri = Uri.parse("file://" + picUri);
                    holder.image.setImageURI(uri);
                }else{
                    Uri uri = Uri.parse("res://cc.xingyan.android.afc/" + R.drawable.plugin_camera_no_pictures);
                    holder.image.setImageURI(uri);
                }
            }


            return convertView;
        }
    }

    public class ViewHolder {
        public SimpleDraweeView image;
    }


}
