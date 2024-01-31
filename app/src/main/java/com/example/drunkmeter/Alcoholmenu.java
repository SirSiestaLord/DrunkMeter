package com.example.drunkmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alcoholmenu extends AppCompatActivity {
    List<Alcohol> alcoholList;
    public  Context context;
    public  int weight;
    public  char gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcoholmenu);
         weight=getIntent().getIntExtra("weight",99);
         gender=getIntent().getCharExtra("gender",'u');


        if(Objects.equals(getIntent().getStringExtra("prod"), "myalcohol")){
            context=this;
            SQL sql=new SQL(this);
            alcoholList=new ArrayList<>();
            alcoholList=sql.ToList();
            ListView listx=(ListView) findViewById(R.id.listviw);
            SpecialAdapte spec=new SpecialAdapte(this,alcoholList);
            listx.setAdapter(spec);
            ImageButton button=findViewById(R.id.addtolist);
            button.setVisibility(View.VISIBLE);
        }
        else if(Objects.equals(getIntent().getStringExtra("prod"), "prealcohol")){
            context=this;
            SQL2 sql=new SQL2(this);
            alcoholList=new ArrayList<>();
            PreAlcoholes alcoholes=new PreAlcoholes();
            alcoholes.AutomaticCreator(this);
            alcoholList=sql.PREToList();
            ListView listx=(ListView) findViewById(R.id.listviw);
            SpecialAdapte spec=new SpecialAdapte(this,alcoholList);
            listx.setAdapter(spec);
            ImageButton button=findViewById(R.id.addtolist);
            button.setVisibility(View.INVISIBLE);
        }

    }

    public void AddNewAlcohol(View view) {
        Intent i =new Intent(this,AddAlcohol.class);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        startActivity(i);
    }

    public void ShowResults(View view) {
        Intent i =new Intent(this,Results.class);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        startActivity(i);
    }


    public void Returntomain(View view) {
        Intent i =new Intent(this,MainActivity.class);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        startActivity(i);
    }
}