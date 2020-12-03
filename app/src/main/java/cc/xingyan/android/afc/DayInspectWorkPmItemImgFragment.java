package cc.xingyan.android.afc;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.photoutil.FileRecorderUtil;
import cc.xingyan.android.afc.photoutil.album.ImageFileActivity;
import cc.xingyan.android.afc.provider.picture.PictureCursor;
import cc.xingyan.android.afc.provider.picture.PictureSelection;

/**
 * Created by YuJiadeng on 2017/8/18.
 *
 */
public class DayInspectWorkPmItemImgFragment extends BaseFragment {

    @Bind(R.id.diw_pmitemimg_uploaded_count)
    TextView uploadedCountTextView;

    @Bind(R.id.diw_pmitemimg_work_gridview)
    GridView noScrollgridview;

    @Bind(R.id.diw_pmitemimg_to_upload_picture)
    Button toUploadPictureBtn;

    @Bind(R.id.diw_pmitemimg_work_up_pic_layout)
    RelativeLayout upPicLayout;


    public static Uri pictureUri;
    private String diWono;
    private String diPmNo;
    FileRecorderUtil recorderUtil = new FileRecorderUtil();

    private GridAdapter adapter;
    private ArrayList<String> uploadedPicAddrs;

    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 10 ;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 20 ;

    @Inject
    Authenticator mAuthenticator;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);

        diWono = getActivity().getIntent().getStringExtra("WoNo");
        diPmNo = getActivity().getIntent().getStringExtra("PmNo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("update_ok");
        getContext().getApplicationContext().registerReceiver(receiver_diw_pmitemimg_work_update_ok, iFilter);

        View view = inflater.inflate(R.layout.activity_diwork_pmitem_img, null);

        return view;
    }

    private BroadcastReceiver receiver_diw_pmitemimg_work_update_ok = new BroadcastReceiver() {
        public void onReceive(Context context,
                              Intent intent) {

            try {
                updateGridview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
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
        });

        toUploadPictureBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (uploadedCount >= 9) {
                    Toast.makeText(getActivity(), "上传图片已达最大数！", Toast.LENGTH_SHORT).show();
                } else {
                    if (Build.VERSION.SDK_INT >= 23) {

                        int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CAMERA);

                        int checkCallPhonePermission2 = ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {


                            new AlertDialog.Builder(getActivity())
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setTitle("温馨提示")
                                    .setMessage("需要使用相机权限，请赋予该权限！")
                                    .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            ActivityCompat.requestPermissions(getActivity(),
                                                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                                        }
                                    }).setNegativeButton("取消", null)
                                    .show();

                            return;
                        } else if (checkCallPhonePermission2 != PackageManager.PERMISSION_GRANTED) {
                            new AlertDialog.Builder(getActivity())
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setTitle("温馨提示")
                                    .setMessage("需要使用存储设备权限，请赋予该权限！")
                                    .setPositiveButton("赋予权限", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            ActivityCompat.requestPermissions(getActivity(),
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                                        }
                                    }).setNegativeButton("取消", null)
                                    .show();

                            return;
                        } else {
                            try {
                                uploadPic();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        try {
                            uploadPic();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        });
        try {
            updateGridview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int uploadedCount;
    private void updateGridview() throws Exception{
        PictureCursor pictureCursor = new PictureSelection()
                .keyId(diWono)
                .and()
                .type("P")
                .and()
                .remark("D")
                .query(getContext());
        try {
            uploadedPicAddrs = new ArrayList<String>();

            uploadedCount = pictureCursor.getCount();

            if(uploadedCount > 0){
                uploadedCountTextView.setText(uploadedCount + "");
            }

            while(pictureCursor.moveToNext()){
                uploadedPicAddrs.add(pictureCursor.getPictureId());
            }

            if(uploadedPicAddrs.size() > 0 && uploadedPicAddrs.size() < 9){
                for(int i = uploadedPicAddrs.size(); i < 9; i++){
                    uploadedPicAddrs.add("default_pic");
                }
            }

            if(uploadedPicAddrs != null && uploadedPicAddrs.size() > 0){

                noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
                adapter = new GridAdapter(uploadedPicAddrs, getActivity());
                noScrollgridview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                upPicLayout.setVisibility(View.VISIBLE);
            }else{
                upPicLayout.setVisibility(View.GONE);
            }
        } finally {
            pictureCursor.close();
        }
    }

    public class GridAdapter extends BaseAdapter {

        Context context;
        ArrayList<String>uploadedPicAddrs;
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


    private void uploadPic() throws Exception{
        String[] ps = { "拍照上传", "本地照片"};
        new AlertDialog.Builder(getActivity()).setTitle("照片上传")
                .setItems(ps, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                                try {
                                    pictureUri = recorderUtil.cameraTakePic(getActivity(), diWono, 1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getActivity(), "没有SD卡！", Toast.LENGTH_LONG).show();
                            }
                        } else if (which == 1) {

                            Intent intent = new Intent(getActivity(), ImageFileActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("keyID", diWono);
                            bundle.putString("type", "P");
                            bundle.putString("remark", "D");
                            intent.putExtra("getKeyID", bundle);
                            startActivity(intent);
                        }
                    }
                }).show();

    }
}
