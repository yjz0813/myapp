package com.example.myapp.DB;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;//数据库版本号
    private static String DB_NAME="user";//数据库名称
    private static String TAG="DatabaseHelper";//数据库名称

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //当第一次建库的时候，调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库的时候把用户表创建好
        String sql = "create table user(id int,password varchar(20))";
        db.execSQL(sql);
        Log.i(TAG, "创建数据库.....");
    }

    //当更新数据库版本号的时候就会执行该方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "更新数据库.....");
    }
}