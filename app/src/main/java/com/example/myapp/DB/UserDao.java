package com.example.myapp.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
public class UserDao {
    private final DatabaseHelper databaseHelper;//设置为一个静态变量；
    private  SQLiteDatabase db;//外部全局变量；
    public UserDao(Context context) {
        //创建数据库；
        databaseHelper = new DatabaseHelper(context);
    }
    //往用户表添加数据
    public void insert( ) {
        //打开该数据库；
        db = databaseHelper.getWritableDatabase();
        //在这里引入表名；插入字段和数据；
        String sql = "insert into " + databaseHelper.getDatabaseName() + "(id,password)values(?,?)";
        Object obj[]= new Object[]{User.getId(), User.getPassword()};
        db.execSQL(sql, obj);
        db.close();
        Log.i("RegisterActivity", "添加了一个用户记录");
    }
    //往用户表删除数据
    public void delete( ) {
        //打开该数据库；
        db = databaseHelper.getWritableDatabase();
        String sql = "delete from " + databaseHelper.getDatabaseName() + "where id=?";
        Object obj[]= new Object[]{User.getId()};
        db.execSQL(sql,obj);
        db.close();
    }
    //更改数据；
    public void update(String id,String password){
        //打开该数据库；
        db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        //参数1:表名 参数2:修改的值 参数三:查询条件 参数4:查询条件需要的参数
        //根据id来修改password；
        db.update("user", contentValues, "id=?", new String[]{id});
        db.close();
        Log.i("ForgetActivity","修改密码成功");

    }
    //判断数据库是否存在该账号
    public boolean find(String id){
        //打开该数据库；
        db = databaseHelper.getWritableDatabase();
        //默认没有该数据
        boolean flag=false;
        //查询所有号码（表名，查询账号，查询条件没有--null）
        Cursor cursor=db.query("users",new String[]{"id"},null,null,null,null,null);
        //如果游标能往下移动
        while (cursor.moveToNext()){
            //遍历Cursor对象，跟传入的id进行比较,如果相同就返回true,说明数据库存在该数据
            if(id.equals(cursor.getString(0))){
                flag=true;
            }
        }
        /*
        一定要关闭游标，回收游标对象
        */
        cursor.close();
        db.close();
        return flag;
    }
}