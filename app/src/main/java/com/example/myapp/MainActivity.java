package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.DB.User;

public class MainActivity extends AppCompatActivity {
    //分别声明布局文件中用到的变量
    private EditText account;
    private EditText password;
    private CheckBox arrge;
    private Button login,look;
    User user=new User("20050539131","12345678");
    //声明数据存储对象
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean hide =true;   //设置为隐藏
    private ImageView eye;  //定义控件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //绑定控件，这样变量就代表了被绑定的控件
        account = findViewById(R.id.et_account);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.login);
        arrge=findViewById(R.id.argge);
        look=findViewById(R.id.look);
        //获取键为remember_password的值，若不存在则为false
        boolean isRemember = pref.getBoolean("remember_password", false);
        eye = findViewById(R.id.eye);   //绑定控件
        eye.setImageResource(R.drawable.closeeye);   //选择初始样貌为闭眼
        if (isRemember) {
            //将账号和密码都设置到文本框中
            String acc = pref.getString("account", "");
            String pass = pref.getString("password", "");
            account.setText(acc);
            password.setText(pass);
        }
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.eye:
                        if (hide == true){
                            eye.setImageResource(R.drawable.opereye);  //可见样貌
                            HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance(); //可见
                            password.setTransformationMethod(method);
                            hide = false;
                        }else{
                            eye.setImageResource(R.drawable.closeeye);
                            TransformationMethod method = PasswordTransformationMethod.getInstance();  //隐藏
                            password.setTransformationMethod(method);
                            hide = true;
                        }
                        int index = password.getText().toString().length();
                        password.setSelection(index);
                        break;
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_account = account.getText().toString();
                String ed_password = password.getText().toString();
                if (ed_account.equals(user.getId()) && ed_password.equals(user.getPassword())) {
                    if(!arrge.isChecked()){
                        Toast.makeText(MainActivity.this, "请勾选同意", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, login_success.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                } else {
                    //设置对话框
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("账号或密码输入有误")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("请输入正确的账号和密码")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    MainActivity.this.finish();
                                }
                            });
                    dialog = builder.create();
                    dialog.show();
                }
            }
        });
        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, login_fail.class);
                startActivity(intent);
            }
        });
    }
    }
