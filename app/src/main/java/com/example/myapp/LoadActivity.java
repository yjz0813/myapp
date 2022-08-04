package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;


import androidx.appcompat.app.AppCompatActivity;

public class LoadActivity extends AppCompatActivity {
    Timer timer;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_layout);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView=findViewById(R.id.login);
        timer=new Timer(true);     //注意：这里我已将timer设置为全局变量
        TimerTask hello=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(LoadActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(hello,2000);
    }
}