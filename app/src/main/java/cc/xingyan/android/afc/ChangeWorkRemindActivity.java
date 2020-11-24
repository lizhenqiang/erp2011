package cc.xingyan.android.afc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;

/**
 * Created by YuJiadeng on 2016/12/6.
 */
public class ChangeWorkRemindActivity extends ThemeActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new ChangeWorkRemindFragment())
                    .commit();
        }
    }

    public static class ChangeWorkRemindFragment extends BaseFragment {
        @Bind(R.id.work_remind_min) EditText mInputRemindMinEdt;

        @Inject
        Authenticator mAuthenticator;
        String userId;

        private SharedPreferences workRemindMinPreferences;

        @Override public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Dagger.inject(this);

            userId = mAuthenticator.getAuthenticatedUserId();

            workRemindMinPreferences = getActivity().getSharedPreferences("workRemindMin", Activity.MODE_PRIVATE);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_change_work_remind, container, false);
        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            String workRemindMinStr = workRemindMinPreferences.getString(userId,"1");

            if(workRemindMinStr.length() < 1){
                workRemindMinStr = "1";
            }
            mInputRemindMinEdt.setText(workRemindMinStr);
        }

        @OnClick({R.id.change_work_remind_ok}) void onchangeWorkRemindMin(View v) {
            String workRemindMinStr = mInputRemindMinEdt.getText().toString().trim();

            while(workRemindMinStr.startsWith("0")){
                try {
                    workRemindMinStr = workRemindMinStr.substring(1,workRemindMinStr.length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            SharedPreferences.Editor workRemindMinPf = workRemindMinPreferences.edit();
            workRemindMinPf.putString(userId, workRemindMinStr);
            boolean isSaveworkRemindMin = workRemindMinPf.commit();
            if(isSaveworkRemindMin){
                Toast.makeText(getActivity(), "设置完毕！", Toast.LENGTH_SHORT).show();



                getActivity().finish();
            }else{
                Toast.makeText(getActivity(), "设置失败，请重试！", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
