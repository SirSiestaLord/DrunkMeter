package com.example.drunkmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch GenderSwitch;
    EditText weighteditor;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    public  int weight;
    public  char gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences =getPreferences(MODE_PRIVATE);
        editor=preferences.edit();
        weighteditor=findViewById(R.id.weightedit);
        if(preferences.getInt("weight",999)==999){
        weight=0;
            weighteditor.setText("0");
        }
        else{
           weight= preferences.getInt("weight",0);
            weighteditor.setText(String.valueOf(weight));

        }

        GenderSwitch=findViewById(R.id.genderedit);
        System.out.println("cins:"+preferences.getString("gender", "nogen"));
        if(preferences.getString("gender", "nogen").equals("Female")) {
            GenderSwitch.setChecked(false);
            gender='f';
        }
        else{
            GenderSwitch.setChecked(true);
            gender='m';
        }
        GenderSwitch.setText(preferences.getString("gender", "Male"));
        GenderSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GenderSwitch.isChecked()){
                    GenderSwitch.setText(GenderSwitch.getTextOn());
                    gender='m';
                }
                else {
                    GenderSwitch.setText(GenderSwitch.getTextOff());
                    gender='f';
                }
            }
        });



    }
    public void Editorsetter(){
        editor.putString("gender",GenderSwitch.getText().toString());
        editor.putInt("weight",Integer.parseInt(weighteditor.getText().toString()));
        weight=Integer.parseInt(weighteditor.getText().toString());
        editor.commit();
    }
    @Override
    protected void onStop() {
        Editorsetter();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Editorsetter();
        super.onDestroy();
    }

    public void gotomenu(View view) {
        Intent i =new Intent(this,Alcoholmenu.class);
        i.putExtra("prod","myalcohol");

        Editorsetter();
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);

        startActivity(i);
    }

    public void gotomenupre(View view) {
        Intent i =new Intent(this,Alcoholmenu.class);
        i.putExtra("prod","prealcohol");
        Editorsetter();
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);

        startActivity(i);
    }
}