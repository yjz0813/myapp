package com.example.myapp.DB;
import java.io.Serializable;


//用户表的设置；
public class User implements Serializable {

    //用户的信息表只包括两个信息；
    private static String id;//用户的账号
    private static String password;//用户密码

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String id,String password) {
        super();
        this.id = id;
        this.password = password;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public static String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + "]";
    }

}