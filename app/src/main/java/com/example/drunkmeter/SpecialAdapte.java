package com.example.drunkmeter;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class SpecialAdapte extends BaseAdapter {
    public static double totalpromil;
    LayoutInflater inflater;
    List<Alcohol> alcoholList;
    int weight;
    char gender;
    public static int sayac;

    public Alcoholmenu context;
    String[] namelist;
    public SpecialAdapte(Alcoholmenu alcoholmenu, List<Alcohol> alcoholList) {
        inflater= (LayoutInflater) alcoholmenu.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.alcoholList=alcoholList;
        context=alcoholmenu;
        namelist=alcoholmenu.getResources().getStringArray(R.array.AlcoholesTurkishe);
        weight=context.getIntent().getIntExtra("weight",99);
        gender=context.getIntent().getCharExtra("gender",'u');

    }

    @Override
    public int getCount() {
        return alcoholList.size();
    }

    @Override
    public Object getItem(int position) {
        return alcoholList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view=inflater.inflate(R.layout.listviw,null);
        LinearLayout layout=view.findViewById(R.id.LAyout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewer(position);
            }
        });
        ImageButton buttonx=view.findViewById(R.id.imageViewlist);
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewer(position);
            }
        });
        TextView name =view.findViewById(R.id.alcoholnamelist);
        TextView promil =view.findViewById(R.id.promillist);
        TextView ml =view.findViewById(R.id.milileter);

        name.setText(alcoholList.get(position).Name);
        if(Objects.equals(context.getIntent().getStringExtra("prod"), "prealcohol")) {
            promil.setText("%"+String.valueOf(alcoholList.get(position).percentageofalcohol));
            ml.setText(String.valueOf(alcoholList.get(position).promil)+"ml");
        }
        else{
            promil.setText("%"+String.valueOf(alcoholList.get(position).promil));
            ml.setText(String.valueOf(alcoholList.get(position).percentageofalcohol)+"ml");
        }



        ImageButton button=view.findViewById(R.id.button3list);

            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayac++;
                totalpromil+=alcoholList.get(position).percentageofalcohol*alcoholList.get(position).promil*0.7899;
                Toast.makeText(context, alcoholList.get(position).Name+" added to your list !\n Total Alcohol num:"+sayac, Toast.LENGTH_SHORT).show();

            }
        });

        ImageButton deleter=view.findViewById(R.id.deleter);
        if(Objects.equals(context.getIntent().getStringExtra("prod"), "prealcohol")) {
            deleter.setVisibility(View.INVISIBLE);//ACTIVATED FOR DEBUG
        }
        else{
            deleter.setVisibility(View.VISIBLE);
        }
        deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(context.getIntent().getStringExtra("prod"));
                if(Objects.equals(context.getIntent().getStringExtra("prod"), "prealcohol")){
                    SQL2 sql=new SQL2(parent.getContext());
                    System.out.println(alcoholList.get(position).id);
                    sql.DeletePREAlcohol(alcoholList.get(position).id);
                    alcoholList.remove(alcoholList.get(position));
                    Intent i =new Intent(context,Alcoholmenu.class);
                    i.putExtra("gender",gender);
                    i.putExtra("weight",weight);
                    i.putExtra("prod",context.getIntent().getStringExtra("prod"));
                    context.startActivity(i);
                }else{
                    SQL sql=new SQL(parent.getContext());
                    System.out.println(alcoholList.get(position).id);
                    sql.DeleteAlcohol(alcoholList.get(position).id);
                    alcoholList.remove(alcoholList.get(position));
                    Intent i =new Intent(context,Alcoholmenu.class);
                    i.putExtra("gender",gender);
                    i.putExtra("weight",weight);
                    i.putExtra("prod",context.getIntent().getStringExtra("prod"));
                    context.startActivity(i);
                }



            }
        });

        System.out.println("TİPİ:"+alcoholList.get(position).Type);
            for (int x=0;x<namelist.length;x++){
                if(alcoholList.get(position).Type.trim().equals(namelist[x])) buttonx.setImageResource(DataClass.imagelist[x]);
        }

        return view;
    }
    public void viewer(int position){
        Intent i =new Intent(context,ViewAlcohol.class);
        i.putExtra("name",alcoholList.get(position).Name);
        i.putExtra("brand",alcoholList.get(position).Brand);
        i.putExtra("promil",String.valueOf(alcoholList.get(position).promil));
        i.putExtra("gram",String.valueOf(alcoholList.get(position).percentageofalcohol));
        i.putExtra("type",alcoholList.get(position).Type);
        i.putExtra("prod",context.getIntent().getStringExtra("prod"));
        i.putExtra("gender",gender);
        i.putExtra("weight",weight);

        context.startActivity(i);
    }

}
