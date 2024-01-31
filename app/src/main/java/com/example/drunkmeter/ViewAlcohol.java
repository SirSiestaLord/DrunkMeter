package com.example.drunkmeter;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class ViewAlcohol extends AppCompatActivity {
    TextView name,brand,promil,gram;
    ImageView foto;
    Context maincontext;
    Alcohol alcohol;

    public  int weight;
    public  char gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alcohol);
        name=findViewById(R.id.Name);
        brand=findViewById(R.id.Brand);
        promil=findViewById(R.id.Promil);
        gram=findViewById(R.id.Gram);
        foto=findViewById(R.id.imageView);
        weight=getIntent().getIntExtra("weight",99);
        gender=getIntent().getCharExtra("gender",'u');
        maincontext=this;
        name.setText(getIntent().getStringExtra("name"));
        brand.setText(getIntent().getStringExtra("brand"));
        if(Objects.equals(getIntent().getStringExtra("prod"), "prealcohol")){
            promil.setText(getIntent().getStringExtra("gram"));
            gram.setText(getIntent().getStringExtra("promil")+" ml");
        }
        else{
            promil.setText(getIntent().getStringExtra("promil"));
            gram.setText(getIntent().getStringExtra("gram")+" ml");
        }

        String [] namelist=getResources().getStringArray(R.array.AlcoholesTurkishe);

        for (int x=0;x<namelist.length;x++){
            if(getIntent().getStringExtra("type").trim().equals(namelist[x])) foto.setImageResource(DataClass.imagelist[x]);
        }
    }


    public void Returntomenu(View view) {
        Intent i =new Intent(this,Alcoholmenu.class);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        startActivity(i);
    }
}