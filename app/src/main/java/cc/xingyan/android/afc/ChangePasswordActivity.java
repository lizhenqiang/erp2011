package cc.xingyan.android.afc;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.moshi.JsonDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cc.xingyan.android.afc.api.Authenticator;
import cc.xingyan.android.afc.api.ChangePassword;
import cc.xingyan.android.afc.app.BaseFragment;
import cc.xingyan.android.afc.inject.Dagger;
import cc.xingyan.android.afc.util.LogUtil;

/**
 * Created by 1 on 2015/10/22.
 */
public class ChangePasswordActivity extends ThemeActivity {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new ChangePwdFragment())
                    .commit();
        }
    }

    public static class ChangePwdFragment extends BaseFragment {
        @Bind(R.id.input_password_old) TextInputLayout mInputOldPassword;
        @Bind(R.id.input_password_new) TextInputLayout mInputNewPassword;
        @Bind(R.id.input_password_new2) TextInputLayout mInputConfirmNewPassword;
        @Bind(R.id.edit_password_old) EditText mEditOldPassword;
        @Bind(R.id.edit_password_new) EditText mEditNewPassword;
        @Bind(R.id.edit_confirm_new_password) EditText mEditConfirmNewPassword;

        @Inject
        Authenticator mAuthenticator;
        @Inject
        ChangePassword mChangePassword;

        @Override public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Dagger.inject(this);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_change_pwd, container, false);
    }

        @OnTextChanged(value = R.id.edit_password_old, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        void onPWDoldChanged() {
            mInputOldPassword.setError(null);
            mInputOldPassword.setErrorEnabled(false);
        }

        @OnTextChanged(value = R.id.edit_password_new, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        void onPWDnewChanged() {
            mInputNewPassword.setError(null);
            mInputNewPassword.setErrorEnabled(false);
        }

        @OnTextChanged(value = R.id.edit_confirm_new_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        void onPWDnew2Changed() {
            mInputConfirmNewPassword.setError(null);
            mInputConfirmNewPassword.setErrorEnabled(false);
        }

        @OnClick({R.id.action_change_pwd_commit}) void onchangePWD(View v) {
            final String oldPassword = mEditOldPassword.getText().toString();
            final String newPassword = mEditNewPassword.getText().toString();
            final String confirmPassword = mEditConfirmNewPassword.getText().toString();
            if (TextUtils.isEmpty(oldPassword)) {
                mInputOldPassword.setErrorEnabled(true);
                mInputOldPassword.setError(getString(R.string.error_required));
            } else if (TextUtils.isEmpty(newPassword)) {
                mInputNewPassword.setErrorEnabled(true);
                mInputNewPassword.setError(getString(R.string.error_required));
            } else if (newPassword.length() < 4) {
                mInputNewPassword.setErrorEnabled(true);
                mInputNewPassword.setError("4到8位密码");
            } else if (newPassword.length() > 8) {  //
                mInputNewPassword.setErrorEnabled(true);
                mInputNewPassword.setError("4到8位密码");
            } else if( !isNumeric(newPassword) ){
                mInputNewPassword.setErrorEnabled(true);
                mInputNewPassword.setError("只能是数字");
            } else if (TextUtils.isEmpty(confirmPassword)) {
                mInputConfirmNewPassword.setErrorEnabled(true);
                mInputConfirmNewPassword.setError(getString(R.string.error_required));
            } else if (!newPassword.equals(confirmPassword)) {
                mInputConfirmNewPassword.setErrorEnabled(true);
                mInputConfirmNewPassword.setError(getString(R.string.pwd_no_match));
            } else {
                switch (v.getId()) {
                    default:
                    case R.id.action_change_pwd_commit:
                        changePWDcommit(oldPassword, newPassword);
                        break;
                }
            }
        }

        private boolean isNumeric(String str){
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if( !isNum.matches() ){
                return false;
            }
            return true;
        }
        private void changePWDcommit(String pwdOld, String pwdNew) {
            final String userId = mAuthenticator.getAuthenticatedUserId();
            subscribe(mChangePassword.changPassword(userId, pwdOld, pwdNew), (changePasswordResp) -> {
                if (changePasswordResp.size() == 1) {
                    if (changePasswordResp.get(0).getErrorInfo().equals("密码修改成功")) {
                        final Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setIcon(R.drawable.ic_lock_black_24dp)
                                .setTitle(R.string.error_change_pwd)
                                .setMessage(changePasswordResp.get(0).getErrorInfo())
                                .setPositiveButton(R.string.ok, null).show();
                    }
                } else {
                    new AlertDialog.Builder(getContext())
                            .setIcon(R.drawable.ic_lock_black_24dp)
                            .setTitle(R.string.error_login_failed)
                            .setMessage("改密码失败")
                            .setPositiveButton(R.string.ok, null).show();
                }

            }, (err) -> {
                LogUtil.error(TAG, err.getMessage(), err);

                if (err instanceof JsonDataException) {
                    new AlertDialog.Builder(getContext())
                            .setIcon(R.drawable.ic_lock_black_24dp)
                            .setTitle(R.string.error_change_pwd)
                            .setMessage(R.string.error_invalidate_authentication)
                            .setPositiveButton(R.string.ok, null).
                            show();
                } else {
                    new AlertDialog.Builder(getContext())
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setTitle(R.string.error_change_pwd)
                            .setMessage(R.string.error_service_unavailable)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                }
            });
        }
    }


}
