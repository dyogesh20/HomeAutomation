package com.example.yogesh.homeautomation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yogesh on 11/20/2016.
 */

public class UserDatabase {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    private static final int NAME_COLUMN = 1;
    private String ID;
    private String User_name;
    private String Password;
    private String Email;
    private String Security_Mode;
    private String Door_Status;
    private String Bulb_Status;
    private String Switch_Status;


    private static final String DATABASE_Homecontrol = "create table " + "HomeAutomation" +
            "( " + "ID" + " integer primary key autoincrement," + "SecurityMode text, DoorStatus text, BulbStatus text, SwitchStatus text); ";
    private static final String DATABASE_User = "create table " + "UserDatabse" +
            "( " + "ID" + " integer primary key autoincrement," + "login text, password text, email text); ";


    public String getUserName(){
        return User_name;
    }
    public String getID(){
        return ID;
    }
    public String getPassword(){
        return Password;
    }
    public String getEmail(){
        return Email;
    }
    public String getSecurityMode(){
        return Security_Mode;
    }
    public String getDoor_Status(){
        return Door_Status;
    }
    public String getBulbStatus(){
        return Bulb_Status;
    }
    public String getSwitchStatus(){
        return Switch_Status;
    }

    public static String getDatabaseStringHome() {

        return DATABASE_Homecontrol;
    }

    public static String getDatabaseStringUser() {

        return DATABASE_User;
    }

    public void setID(String id){
        ID = id;
    }
    public void setUserName(String userName){
        User_name = userName;
    }
    public void setPassword(String password){
        Password = password;
    }
    public void setEmail(String email){
        Email = email;
    }
    public void setSecurityMode(String security_mode){
        Security_Mode = security_mode;
    }
    public void setDoorStatus(String door_status){
        Door_Status = door_status;
    }
    public void setBulbStatus(String bulb_status){
        Bulb_Status = bulb_status;
    }
    public void setSwitchStatus(String switch_status){
        Switch_Status = switch_status;
    }

}


