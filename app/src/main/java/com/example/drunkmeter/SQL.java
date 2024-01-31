package com.example.drunkmeter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQL extends SQLiteOpenHelper {
   public static final String DatabaseName="ALCOHOLDATABASE";
    public SQL(Context context) {
        super(context, DatabaseName,null,1,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ALCOHOLES (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, BRAND TEXT , TYPE TEXT ,PERCENTAGE INTEGER , PROMIL INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS PREALCOHOLES (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, BRAND TEXT , TYPE TEXT ,PERCENTAGE INTEGER , PROMIL INTEGER)");

    }

    public void AddAlcohol(String Name,String Brand,String Type,int percentageofalcohol,int promil){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO ALCOHOLES (NAME,BRAND,TYPE,PERCENTAGE,PROMIL) VALUES (' "+Name+" ',' "+Brand+" ',' "+Type+" ', "+percentageofalcohol+" , " +promil+ " )");
        db.close();
    }

    public void UpdateAlcohol(int Changeid,String Name,String Brand,String Type,int percentageofalcohol,int promil){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE ALCOHOLES SET NAME= ' "+Name+ " ', BRAND= ' "+Brand+" ', TYPE=' "+Type+ " ', PERCENTAGE= "+percentageofalcohol+" , PROMIL= " +promil+ " WHERE ID= "+Changeid+" ");
        db.close();
    }

    public void DeleteAlcohol(int Changeid){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM ALCOHOLES WHERE ID = "+Changeid+" ");
        db.close();
    }

    public List<Alcohol> ToList(){
        List<Alcohol> alcoholList=new ArrayList<>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query("ALCOHOLES",new String[]{"ID","NAME","BRAND","TYPE","PERCENTAGE","PROMIL"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Alcohol alcohol=new Alcohol(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5));
            alcohol.id=cursor.getInt(0);
            alcoholList.add(alcohol);
        }
        db.close();
        return alcoholList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
