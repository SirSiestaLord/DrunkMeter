package com.example.drunkmeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Objects;
import java.util.Timer;

public class Results extends AppCompatActivity {
    public Context context;
    public float finalpromil=0;
    float timeleft=0;
    int hour=0,minuteleft=0;
    TextView finaller;
    float promil=0;
    int weight;
    char gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //total promil is dose in grams
        TextView textView=findViewById(R.id.textView2);
        SharedPreferences preferences =getPreferences(MODE_PRIVATE);
         weight=getIntent().getIntExtra("weight",99);
         gender=getIntent().getCharExtra("gender",'u');
        System.out.println(weight);
        System.out.println(gender);
        finaller=findViewById(R.id.timeleft);
        System.out.println(SpecialAdapte.totalpromil);
        if(SpecialAdapte.totalpromil!=0){
            finalpromil=weight*100;
            if(Objects.equals(gender, 'f')){
                finalpromil*=55;
            }
            else {
                finalpromil*=68;
            }
            System.out.println(finalpromil);
            finalpromil= (float) (SpecialAdapte.totalpromil/finalpromil);
            System.out.println(finalpromil);
            finalpromil*=10000;
            System.out.println(finalpromil);
            if(weight>0){
                promil=Float.parseFloat((String) String.valueOf(finalpromil).subSequence(0,5))/100;
                System.out.println(promil);
                textView.setText("%"+(promil));
                /*PROMİL YÜZDESİ DOĞRU ZAMAN KAYMASI VAR DAKİKA SIKINTILI*/
                timeleft= (int) (promil*10/0.016);
                System.out.println("Tİmeleft:"+timeleft);
                //float alltime=timeleft;
                //timeleft=Float.parseFloat((String) String.valueOf(timeleft).subSequence(0,4));
                System.out.println(timeleft/120);
                hour = (int) Math.floor(timeleft/120);
                System.out.println(hour);
                minuteleft=(int)(timeleft-(hour*120));
                if(minuteleft>=60){
                    minuteleft-=60;
                }
                System.out.println(minuteleft);
                if(minuteleft>9){
                    finaller.setText(String.valueOf(hour+":"+minuteleft+" time after you're going to be 0 promil"));

                }
                else{
                    finaller.setText(String.valueOf(hour+":0"+minuteleft+" time after you're going to be 0 promil"));

                }
            }
            else{
                finaller.setText(" ");
                textView.setText("%"+("Empty"));

            }



            }
            else{
            Toast.makeText(context, "Please Enter Your Weight !", Toast.LENGTH_SHORT).show();
            gotomenuagain(new View(this));
            }

        Button resultButton=(Button) findViewById(R.id.resulttomenu);
        context=getApplicationContext();



    }

    public void gotomenuagain(View view) {
        Intent i =new Intent(this,Alcoholmenu.class);
        System.out.println(i.getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        startActivity(i);
    }

    public void resetresult(View view) {
        SpecialAdapte.totalpromil=0f;
        hour=0;
        minuteleft=0;
        SpecialAdapte.sayac=0;
        Intent i =new Intent(this,Alcoholmenu.class);
        System.out.println(i.getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);
        i.putExtra("prod",getIntent().getStringExtra("prod"));
        startActivity(i);

    }
}