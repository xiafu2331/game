package com.example.food.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.food.MyApplication;
import com.example.food.R;
import com.example.food.ui.activity.BrowseActivity;
import com.example.food.ui.activity.LoginActivity;
import com.example.food.ui.activity.PasswordActivity;
import com.example.food.ui.activity.PersonActivity;
import com.example.food.util.SPUtils;


/**
 * 个人中心
 */
public class UserFragment extends Fragment {
    private Activity mActivity;
    private LinearLayout llPerson;
    private LinearLayout llSecurity;
    private LinearLayout llBrowse;
    private Button btnLogout;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        llPerson = view.findViewById(R.id.person);
        llSecurity = view.findViewById(R.id.security);
        llBrowse = view.findViewById(R.id.browse);
        btnLogout = view.findViewById(R.id.logout);
        initView();
        return view;
    }

    private void initView() {
        Boolean isAdmin = (Boolean) SPUtils.get(mActivity,SPUtils.IS_ADMIN,false);
        llBrowse.setVisibility(isAdmin? View.GONE: View.VISIBLE);
        //个人信息
        llPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                Intent intent = new Intent(mActivity, PersonActivity.class);
                startActivity(intent);
            }
        });
        //账号安全
        llSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                Intent intent = new Intent(mActivity, PasswordActivity.class);
                startActivity(intent);
            }
        });
        //浏览记录
        llBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                Intent intent = new Intent(mActivity, BrowseActivity.class);
                startActivity(intent);
            }
        });
        //退出登录
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.Instance.getMainActivity().finish();
                SPUtils.remove(mActivity,SPUtils.IS_ADMIN);
                SPUtils.remove(mActivity,SPUtils.ACCOUNT);
                startActivity(new Intent(mActivity, LoginActivity.class));
            }
        });
    }
}
