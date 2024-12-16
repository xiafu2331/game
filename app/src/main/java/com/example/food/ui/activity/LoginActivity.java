package com.example.food.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.R;
import com.example.food.bean.User;
import com.example.food.util.SPUtils;
import com.example.food.widget.ActionBar;

import org.litepal.crud.DataSupport;


/**
 * 登录页面
 */
public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private Activity activity;
    private ActionBar mTitleBar;//标题栏
    private EditText etAccount;//手机号
    private EditText etPassword;//密码
    private TextView tvRegister;//注册
    private Button btnLogin;//登录按钮
    private RadioGroup rgType;//用户类型
    private RadioButton rbUser;//用户类型
    private RadioButton rbAdmin;//用户类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.activity_login);//加载页面
        etAccount =(EditText) findViewById(R.id.et_account);//获取手机号
        etPassword=(EditText)findViewById(R.id.et_password);//获取密码
        tvRegister=(TextView)findViewById(R.id.tv_register);//获取注册
        btnLogin=(Button)findViewById(R.id.btn_login);//获取登录
        rgType = findViewById(R.id.rg_type);
        rbUser = findViewById(R.id.rb_user);
        rbAdmin = findViewById(R.id.rb_admin);
        mTitleBar = (ActionBar)findViewById(R.id.myActionBar);
        mTitleBar.setData(activity,"登录",0, 0, 0,getResources().getColor(R.color.colorPrimary), new ActionBar.ActionBarClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
            }
        });

        //手机号注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent=new Intent(activity, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //选择类型
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SPUtils.put(activity,SPUtils.IS_ADMIN,checkedId == R.id.rb_admin);
            }
        });
        //设置点击按钮
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭虚拟键盘
                InputMethodManager inputMethodManager= (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                //获取请求参数
                String account= etAccount.getText().toString();
                String password=etPassword.getText().toString();
                Boolean isAdmit = (Boolean) SPUtils.get(activity,SPUtils.IS_ADMIN,false);
                if ("".equals(account)){//账号不能为空
                    Toast.makeText(activity,"Account cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)){//密码为空
                    Toast.makeText(activity,"Password is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = DataSupport.where("account = ?", account).findFirst(User.class);
                if (user != null) {
                    if (!password.equals(user.getPassword())) {
                        Toast.makeText(activity, "Wrong password", Toast.LENGTH_SHORT).show();
                    }else{
                        if (isAdmit && !"admin".equals(user.getAccount())){
                            Toast.makeText(activity,"This account is not an administrator account", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!isAdmit && "admin".equals(user.getAccount())){
                            Toast.makeText(activity,"This account is not a normal user account", Toast.LENGTH_LONG).show();
                            return;
                        }
                        SPUtils.put(LoginActivity.this,"account",account);
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(activity, "Account does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
