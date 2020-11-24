/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import butterknife.Bind;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.provider.diifsinfo.DiIfsStatus;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoCursor;
import cc.xingyan.android.afc.provider.diifsinfo.DiifsInfoSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkCursor;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkSelection;
import cc.xingyan.android.afc.provider.pmifswork.PmifsWorkStatus;
import cc.xingyan.android.afc.provider.workorder.SyncStatus;
import cc.xingyan.android.afc.provider.workorder.WorkOrderCursor;
import cc.xingyan.android.afc.provider.workorder.WorkOrderSelection;
import cc.xingyan.android.afc.receiver.WorkNoConfirmAlarmReceiver;
import cc.xingyan.android.afc.service.SyncService;
import cc.xingyan.android.afc.service.WorkRemindService;
import cc.xingyan.android.afc.util.TService;
import icepick.State;

public class MainActivity extends TitleActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_USERNAME = "username";
    public static MainActivity myMMainActivity;
    public static boolean isMustExit = false;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView mNavigationView;

    View headerView;

    TextView mUsernameView;

    @State int mCheckedNavItemId;

    @Inject
    Authenticator mAuthenticator;
    private boolean mFinishPending;
    private Handler mHandler = new Handler();
    private Toast mToast;
    private SharedPreferences loginPreferences;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dagger.inject(this);
        myMMainActivity = this;

        setContentView(R.layout.activity_main);
        headerView = mNavigationView.getHeaderView(0);
        mUsernameView = headerView.findViewById(R.id.username);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(mNavigationView);

        String userName = "";
        final String userNameTemp = "";

        do{
            userName = mAuthenticator.getAuthenticatedUserName();
        }while(userName == null || userName.length() < 1);

        mUsernameView.setText(userName);

        if (mCheckedNavItemId == 0) {
            mCheckedNavItemId = R.id.nav_home;
        }
        mNavigationView.post(() -> {
            final Menu menu = mNavigationView.getMenu();

            MenuItem chartmenu = menu.findItem(R.id.nav_chart);

            SharedPreferences chartPreferences = getSharedPreferences("chart", Activity.MODE_PRIVATE);

            String userId = mAuthenticator.getAuthenticatedUserId();

            String chartStr = chartPreferences.getString(userId, "noinfo");
            if(!chartStr.equals("chart")){
                chartmenu.setVisible(false);
            }

            menu.performIdentifierAction(mCheckedNavItemId, 0);
        });


        Intent intent = new Intent(MainActivity.this, WorkRemindService.class);
        intent.setAction("remind");
        startService(intent);
        //由loginactivity移到mainactivity
            Intent intent2 = new Intent(MainActivity.this, TService.class);
            startService(intent2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isMustExit){

            Intent intentStopLoc = new Intent(MainActivity.this, TService.class);
            stopService(intentStopLoc);

            finish();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(MainActivity.this, WorkNoConfirmAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent,0);
        AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.cancel(pi);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

        final int itemId = menuItem.getItemId();
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (itemId == R.id.nav_home) {
            mCheckedNavItemId = itemId;
            if (!(fragment instanceof HomeFragment)) {
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commitAllowingStateLoss();
            }
        }
        else if (itemId == R.id.nav_workorder) {
            mCheckedNavItemId = itemId;
            if (!(fragment instanceof WorkOrderFragment)) {
                fm.beginTransaction().replace(R.id.fragment_container, new WorkOrderFragment()).commitAllowingStateLoss();
            }
        }
//        else if(itemId == R.id.nav_cm){
//            mCheckedNavItemId = itemId;
//            if (!(fragment instanceof CmFragment)) {
//                fm.beginTransaction().replace(R.id.fragment_container, new CmFragment()).commitAllowingStateLoss();
//            }
//        }else if (itemId == R.id.nav_pm) {
//            mCheckedNavItemId = itemId;
//            if (!(fragment instanceof PmFragment)) {
//                fm.beginTransaction().replace(R.id.fragment_container, new PmFragment()).commitAllowingStateLoss();
//            }
//        } else if (itemId == R.id.nav_di) {
//            mCheckedNavItemId = itemId;
//            if (!(fragment instanceof DayInspectFragment)) {
//                fm.beginTransaction().replace(R.id.fragment_container, new DayInspectWorkFragment()).commitAllowingStateLoss();
//            }
//        }
//
        else if (itemId == R.id.nav_sync) {
            startActivity(new Intent(this, SyncSettingsActivity.class));
        } else if (itemId == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (itemId == R.id.nav_logout) {
            if (queryNoUploadTask()) {
                startActivity(new Intent(this, LoginActivity.class));
                this.finish();
            }
        }else if (itemId == R.id.nav_chart) {
            startActivity(new Intent(this, Main4ChartForWebActivity.class));
        }else if(itemId == R.id.nav_part){
            startActivity(new Intent(this,  Main4PartActivity.class));
        }
        return true;
    }

    public void performNavigationAction(int navigationItemId) {
        final Menu menu = mNavigationView.getMenu();
        final MenuItem menuItem = menu.findItem(navigationItemId);
        if (menuItem != null) {
            menu.performIdentifierAction(navigationItemId, 0);
        }
    }

    private long exitTime = 0;
    @Override public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
        } else if (mFinishPending) {
            super.onBackPressed();
            if (mToast != null) {
                mToast.cancel();
            }
            clearShowMSGSharedPreference();

        } else {

            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);


            if (fragment instanceof HomeFragment) {
                if(queryNoUploadTask() == false){
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setTitle(R.string.login_out)
                            .setMessage("你有已完成的工单还没上传,是否现在上传?")
                            .setPositiveButton(R.string.Immediately_exit, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    clearShowMSGSharedPreference();

                                    Intent intentStopLoc = new Intent(MainActivity.this, TService.class);
                                    stopService(intentStopLoc);

                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.return_nav_home, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final Intent intent = new Intent(getApplicationContext(), SyncActivity.class);
                                    intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, SyncService.SYNC_UPLOAD_ALL);
                                    intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, false);
                                    intent.putExtra("source_page","MainActivity");
                                    startActivity(intent);
                                    clearShowMSGSharedPreference();
                                }
                            })
                            .show();
                }else {
                    if((System.currentTimeMillis() - exitTime) > 2000){
                        Toast.makeText(getApplicationContext(), R.string.message_press_back_again_to_exit, Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        mFinishPending = true;
                        finish();
                        System.exit(0);
                    }
                }

            } else {
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commitAllowingStateLoss();
                mFinishPending = false;
            }

        }
    }

    private boolean queryNoUploadTask() {
        String WorkOrderNum, PMOrderNum, DayInspectNum;
        final WorkOrderCursor cur = new WorkOrderSelection().userId(mAuthenticator.getAuthenticatedUserId()).and().syncStatus(SyncStatus.LOCAL).query(getContentResolver());
        WorkOrderNum = String.valueOf(cur.getCount());
        final PmifsWorkCursor curPM = new PmifsWorkSelection().userId(mAuthenticator.getAuthenticatedUserId()).and().status(PmifsWorkStatus.WORKDONE).query(getContentResolver());
        PMOrderNum = String.valueOf(curPM.getCount());
        final DiifsInfoCursor diifsInfoCursor = new DiifsInfoSelection().sign(mAuthenticator.getAuthenticatedUserId()).and().status(DiIfsStatus.DONE).query(getContentResolver());
        DayInspectNum = String.valueOf(diifsInfoCursor.getCount());
        if (WorkOrderNum.equals("0") && PMOrderNum.equals("0") && DayInspectNum.equals("0")) {
            return true;
        }else{
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_info_black_24dp)
                    .setTitle(R.string.login_out)
                    .setMessage("你有已完成的工单还没上传,是否现在上传?")
                    .setPositiveButton(R.string.Immediately_exit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearShowMSGSharedPreference();

                            Intent intentStopLoc = new Intent(MainActivity.this, TService.class);
                            stopService(intentStopLoc);

                            finish();
                        }
                    })
                    .setNegativeButton(R.string.return_nav_home, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Intent intent = new Intent(getApplicationContext(), SyncActivity.class);
                            intent.putExtra(SyncActivity.EXTRA_SYNC_OPTIONS, SyncService.SYNC_UPLOAD_ALL);
                            intent.putExtra(SyncActivity.EXTRA_RESET_AUTO_SYNC, false);
                            intent.putExtra("source_page","MainActivity");
                            startActivity(intent);
                            clearShowMSGSharedPreference();
                        }
                    })
                    .show();
            return false;
        }
    }

    private void clearShowMSGSharedPreference() {
        loginPreferences = getSharedPreferences("show_msg_sp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor show_msg_ed = loginPreferences.edit();
        show_msg_ed.clear();
        show_msg_ed.commit();
    }

    @Override protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override protected void onTitleChanged(CharSequence title, int color, boolean respectColor) {
        super.onTitleChanged(title, color, respectColor);
        if (mNavigationView != null && respectColor) {
            mNavigationView.getHeaderView(0).setBackgroundColor(color);
        }
    }

    @Override
    protected void onConnectionStatusChanged(int connectionStatus, String connectionStatusText) {
        super.onConnectionStatusChanged(connectionStatus, connectionStatusText);
        if (mNavigationView != null) {
            ((TextView) mNavigationView.getHeaderView(0).findViewById(R.id.connection_status)).setText(connectionStatusText);
        }
    }

    @Override public void finish() {
        super.finish();
        mAuthenticator.setAuthenticatedUser(null);

        Intent intentStopLoc = new Intent(MainActivity.this, TService.class);
        stopService(intentStopLoc);
    }
}