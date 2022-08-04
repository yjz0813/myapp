package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_fail extends AppCompatActivity {
    Button my,home,dianhua,yingyong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fail);
         my=findViewById(R.id.my);
         home=findViewById(R.id.home);
         dianhua=findViewById(R.id.dianhua);
         yingyong=findViewById(R.id.yingyong);
         yingyong.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent();
                 intent.setClass(login_fail.this, login_fail_yingyyong.class);
                 startActivity(intent);
             }
         });
    }
}