package cc.xingyan.android.afc;

import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.CmService;
import cc.xingyan.android.afc.api.model.CmConfirm;
import cc.xingyan.android.afc.api.model.CmConfirms;
import cc.xingyan.android.afc.api.model.CmWorkTime;
import cc.xingyan.android.afc.api.model.CmWorkTimes;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkContentValues;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.util.LogUtil;
import cc.xingyan.android.afc.util.NetUtil;

/**
 * Created by YuJiadeng on 2016/10/26.
 */
public class PmWorkNewFragment extends BaseFragment {
    private static final String TAG = "pm_confirm";
    private static final String ARG_URI = "uri";

    private Uri mUri;

    private String mPMOrderId;

    @Bind(R.id.tab_pm_fault_report_confirm)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager_pm_fault_report_confirm)
    ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private List<Page> pages = new ArrayList<>();

    public static Fragment newInstance(Uri uri) {
        PmWorkNewFragment fragment = new PmWorkNewFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        mUri = getArguments().getParcelable(ARG_URI);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pm_work_new, container, false);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        setHasOptionsMenu(true);

        final PmifsWorkCursor work = new PmifsWorkCursor(getContext().getContentResolver().query(mUri, null, null, null, null));
        try {
            if (work.moveToNext()) {
                mPMOrderId = work.getOrderId();
                getActivity().setTitle("["+work.getWorkTypeId()+"工单] # " + work.getOrderId());
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(work.getDeviceCode());
            }
        } finally {
            work.close();
        }

        final TabLayout.ViewPagerOnTabSelectedListener l = new TabLayout.ViewPagerOnTabSelectedListener(mViewPager);
        mTabLayout.setOnTabSelectedListener(l);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(mPagerAdapter = new PagerAdapter());
        mPagerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {

                mTabLayout.setOnTabSelectedListener(null);
                mTabLayout.removeAllTabs();
                for (int i = 0, count = pages.size(); i < count; i++) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(pages.get(i).title));
                }
                mTabLayout.setOnTabSelectedListener(l);
            }
        });
        loadPages();

    }

    private void loadPages() {
        pages.clear();
        pages.add(new Page(Page.TAB_PM_DEVICEINFO, "设备信息", mPMOrderId));
        pages.add(new Page(Page.TAB_PM_CONTENT, "工作内容", mPMOrderId));
        pages.add(new Page(Page.TAB_PM_MATERIAL, "工作物料", mPMOrderId));
        mPagerAdapter.notifyDataSetChanged();
    }

    private static class Page {
        public static final int TAB_PM_DEVICEINFO = 0;
        public static final int TAB_PM_CONTENT = 1;
        public static final int TAB_PM_MATERIAL = 2;

        int type;
        String title;
        Object attachment;

        public Page(@NonNull int type, @NonNull String title, @NonNull Object attachment) {
            this.type = type;
            this.title = title;
            this.attachment = attachment;
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter() {
            super(getChildFragmentManager());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages.get(position).title;
        }

        @Override
        public Fragment getItem(int position) {
            final Page p = pages.get(position);
            switch (p.type) {
                default:
                case Page.TAB_PM_DEVICEINFO:
                    return PmWorkNewDeviceInfoFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_CONTENT:
                    return PmWorkNewContentFragment.newInstance((String) p.attachment);

                case Page.TAB_PM_MATERIAL:
                    return PmWorkNewMaterialFragment.newInstance((String) p.attachment);
            }
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }



    @Inject static CmService cmService;
    private boolean t;

    @OnClick(R.id.pm_fault_report_confirm) void pmConfirm() {


        boolean isNetworkAvailable =  NetUtil.isNetworkAvailable(getActivity());
        if(!isNetworkAvailable){
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle("温馨提示")
                    .setMessage("没有网络！")
                    .setPositiveButton(R.string.ok, null)
                    .show();
            return;
        }

        CmConfirms list = new CmConfirms();
        CmConfirm c = new CmConfirm();
        c.setOrderId(mPMOrderId);
        c.setconfirm("RELEASED");
        List<CmConfirm> cmConfirmlist =  Arrays.asList(c);
        list.setCmConfirms(cmConfirmlist);

        t = false;
        uploadTime(() -> {
            confirm(mPMOrderId, list, getActivity());
        });
    }

    private boolean uploadTime(final Runnable next) {
        Long acceptTime = null;
        Long arriveTime = null;

        arriveTime = System.currentTimeMillis();


        new PmifsWorkContentValues()
                .putArriveTime(arriveTime)
                .update(getContext().getContentResolver(), new PmifsWorkSelection().orderId(mPMOrderId));

        final PmifsWorkCursor pmifsWorkCursor = new PmifsWorkSelection().orderId(mPMOrderId).query(getContext());
        if (pmifsWorkCursor.moveToFirst()) {
            acceptTime = pmifsWorkCursor.getOrderReceiveTime();
        }
        pmifsWorkCursor.close();

        if (acceptTime == null) {
            Toast.makeText(getContext(), "接单时间为空，不能确认或取消", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (arriveTime == null) {
            Toast.makeText(getContext(), "到达现场时间为空，不能确认或取消。请扫描设备二维码打开PM工单", Toast.LENGTH_SHORT).show();
            return false;
        }


        CmWorkTime c = new CmWorkTime();
        List<CmWorkTime> list = Arrays.asList(c);
        CmWorkTimes list1 = new CmWorkTimes();
        c.setOrderId(mPMOrderId);
        c.setAcceptTime(new Date(acceptTime));
        c.setArriveTime(new Date(arriveTime));
        list1.setcmWorkTimes(list);
        subscribe(cmService.postUploadTimeCMWorks(list1), resp -> {
            if (resp.size() > 0) {
                t = true;
                if (next != null) {
                    next.run();
                }
                LogUtil.debug(TAG, "confirm a pm is ok");
            } else {
                Toast.makeText(getContext(), "上传接单时间失败", Toast.LENGTH_SHORT).show();
            }
        }, e -> {
            LogUtil.debug(TAG, "confirm a pm is failed");
            Toast.makeText(getContext(), "上传接单时间失败", Toast.LENGTH_SHORT).show();
        });
        return t;
    }


    private ProgressDialog progress;
    private void confirm(String mPmOrderId, CmConfirms list, FragmentActivity fa) {

        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_settings_white_24dp);
        progress.setTitle("正在确认");
        progress.setMessage("请稍后...");
        progress.setIndeterminate(false);
        progress.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                subscribe(cmService.postConfirmCMWorks(list), resp -> {
                    if(resp != null && resp.size() > 0){
                        new PmifsWorkContentValues().putStatus(PmifsWorkStatus.RELEASED)
                                .update(getActivity().getContentResolver(), new PmifsWorkSelection().orderId(mPmOrderId));
                        progress.dismiss();
                        Message msg = new Message();
                        msg.what = 0x001;
                        myHandler.sendMessage(msg);
                    }else if(resp != null && resp.size() == 0){
                        progress.dismiss();
                        Toast.makeText(getActivity(), "确认PM工单失败！", Toast.LENGTH_SHORT).show();
                    }else if(resp == null){
                        progress.dismiss();
                        Toast.makeText(getActivity(), "确认PM工单失败了！", Toast.LENGTH_SHORT).show();
                    }
                }, e -> {
                    LogUtil.debug(TAG, "confirm a pm is failed");
                    Toast.makeText(fa, "确认工单失败！", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();


    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                t = true;
                try {
                    getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    };

}
