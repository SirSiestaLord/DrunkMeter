package com.example.drunkmeter;

import android.content.Context;

public class PreAlcoholes {
    public void AutomaticCreator(Context context){

        SQL2 sql=new SQL2(context);
        sql.AddPREAlcohol("Bira","Maltepe","Beer",9,200);
        sql.AddPREAlcohol("Bira","Maltepe","Beer",9,800);
        sql.AddPREAlcohol("Bira","Maltepe","Beer",15,800);

       // DeleteallPres(context.getApplicationContext());
    }
    public void DeleteallPres(Context context){
        SQL2 sql=new SQL2(context);

        for (int i =0;i<20;i++){
            sql.DeletePREAlcohol(i);
        }
    }


    public void DeleteThatAlcohol(Context context,int id){
        SQL2 sql=new SQL2(context);

            sql.DeletePREAlcohol(id);

    }

}
