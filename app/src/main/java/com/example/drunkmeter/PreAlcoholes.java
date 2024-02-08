package com.example.drunkmeter;

import android.content.Context;

public class PreAlcoholes {
    public void AutomaticCreator(Context context){

        SQL2 sql=new SQL2(context);
        //DeleteallPres(context.getApplicationContext());
        sql.AddPREAlcohol("Beer","Example Brand","Beer",5,200);
        sql.AddPREAlcohol("Beer","Example Brand","Beer",5,800);
        sql.AddPREAlcohol("Beer","Example Brand","Beer",5,800);
        sql.AddPREAlcohol("Wine","Example Brand","Wine",14,200);
        sql.AddPREAlcohol("Wine","Example Brand","Wine",14,800);
        sql.AddPREAlcohol("Wine","Example Brand","Wine",14,800);
        sql.AddPREAlcohol("Scotch","Example Brand","Scotch",50,200);
        sql.AddPREAlcohol("Scotch","Example Brand","Scotch",50,800);
        sql.AddPREAlcohol("Scotch","Example Brand","Scotch",50,800);

        sql.AddPREAlcohol("Votka","Example Brand","Votka",40,200);
        sql.AddPREAlcohol("Votka","Example Brand","Votka",40,800);
        sql.AddPREAlcohol("Votka","Example Brand","Votka",40,800);

        sql.AddPREAlcohol("Raki","Example Brand","Raki",47,200);
        sql.AddPREAlcohol("Raki","Example Brand","Raki",47,800);
        sql.AddPREAlcohol("Raki","Example Brand","Raki",47,800);

    }
    public void DeleteallPres(Context context){
        SQL2 sql=new SQL2(context);

        for (int i =0;i<50;i++){
            sql.DeletePREAlcohol(i);
        }
    }


    public void DeleteThatAlcohol(Context context,int id){
        SQL2 sql=new SQL2(context);

            sql.DeletePREAlcohol(id);

    }

}
