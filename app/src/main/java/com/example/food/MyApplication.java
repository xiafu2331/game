package com.example.food;

import android.app.Activity;
import android.app.Application;

import org.litepal.LitePal;

public class MyApplication extends Application {
    public static MyApplication Instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        LitePal.initialize(this);//初始化LitePal数据库
    }
    private Activity mMainActivity;

    public Activity getMainActivity() {
        return mMainActivity;
    }

    public  void setMainActivity(Activity mainActivity) {
        mMainActivity = mainActivity;
    }
}
