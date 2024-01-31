package com.example.drunkmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAlcohol extends AppCompatActivity {
    EditText NameEditText,BrandEditText;
    Spinner spinnerSpin;

    EditText percentageEditText,gramEditText;

    String Name,Brand,Type;
    int percentage,gram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alcohol);
    }

    public void AddTheAlcohol(View view) throws InterruptedException {
        NameEditText=findViewById(R.id.name);
        BrandEditText=findViewById(R.id.brand);
        spinnerSpin=findViewById(R.id.spinner);
        percentageEditText=findViewById(R.id.volume2);
        gramEditText=findViewById(R.id.volume);

        Name= String.valueOf(NameEditText.getText());
        Brand= String.valueOf(BrandEditText.getText());
        Type= (String) spinnerSpin.getSelectedItem();
        percentage=Integer.parseInt(percentageEditText.getText().toString());
        gram=Integer.parseInt(gramEditText.getText().toString());

        SQL sql=new SQL(this);
        sql.AddAlcohol(Name,Brand,Type,percentage,gram);
        Toast tost=Toast.makeText(this, "Your Drink Added to our Database", Toast.LENGTH_SHORT);
        tost.show();
        Intent i =new Intent(this,Alcoholmenu.class);
        i.putExtra("prod","myalcohol");
        startActivity(i);
    }

    public void BackToMenu(View view) {
        Intent i =new Intent(this,Alcoholmenu.class);
        i.putExtra("prod","myalcohol");
        startActivity(i);
    }

    }