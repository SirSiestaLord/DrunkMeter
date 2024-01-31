package com.example.drunkmeter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SQL2 extends SQLiteOpenHelper {
    public static final String DatabaseName="PREALCOHOLDATABASE";

    public SQL2(Context context) {
        super(context, DatabaseName,null,1,null);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS PREALCOHOLES (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, BRAND TEXT , TYPE TEXT ,PERCENTAGE INTEGER , PROMIL INTEGER)");

    }
    public void AddPREAlcohol(String Name,String Brand,String Type,int percentageofalcohol,int promil){
        int i=0;
        List<Alcohol> alcoholList=new ArrayList<>();
        alcoholList=PREToList();

        SQLiteDatabase dbx=getWritableDatabase();

        for ( i=0;i<alcoholList.size();i++){
            if(Objects.equals(alcoholList.get(i).Name.trim(), Name.trim())){
                if(Objects.equals(alcoholList.get(i).promil, promil)){
                    if(Objects.equals(alcoholList.get(i).percentageofalcohol, percentageofalcohol)){
                    System.out.println("KIRILMA NOKTASI ID:"+i);
                break;}}
            }

        }
        if(i==alcoholList.size()){
            dbx.execSQL("INSERT INTO PREALCOHOLES (NAME,BRAND,TYPE,PERCENTAGE,PROMIL) VALUES (' "+Name+" ',' "+Brand+" ',' "+Type+" ', "+percentageofalcohol+" , " +promil+ " )");

        }


    }

    public void DeletePREAlcohol(int Changeid){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM PREALCOHOLES WHERE ID = "+Changeid+" ");
        db.close();
    }

    public List<Alcohol> PREToList(){
        List<Alcohol> alcoholList=new ArrayList<>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query("PREALCOHOLES",new String[]{"ID","NAME","BRAND","TYPE","PERCENTAGE","PROMIL"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Alcohol alcohol=new Alcohol(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5));
            alcohol.id=cursor.getInt(0);
            alcoholList.add(alcohol);
        }
        db.close();
        cursor.close();
        return alcoholList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
