package com.example.gauravsharma.bestoutofwaste;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SelectActivity extends MenuActivity {

    private Button nextButton;
    Boolean[] selected = new Boolean[10];
    ArrayList<String> list= new ArrayList<>();
    HashMap<String,Integer> items=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        super.onCreateDraw();
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String x:items.keySet())
                {
                    list.add(x);
                }
                Intent intent = new Intent(getApplicationContext(), VerifySelectedActivity.class);
                intent.putStringArrayListExtra("items",list);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Boolean[] selected = new Boolean[10];
        ArrayList<String> list= new ArrayList<>();
        Arrays.fill(selected, false);
        items.clear();
        //GradientDrawable gd = new GradientDrawable();
        //gd.setColor(Color.parseColor("#84FFFF"));
        //((Button)findViewById(R.id.itemNewsPaper)).setBackground(gd);

    }
    public void addItem(View view) {
        ImageButton item = (ImageButton) view;
        if(selected[Integer.parseInt(item.getTag().toString())]) {
            selected[Integer.parseInt(item.getTag().toString())] = false;
            // GradientDrawable gd = new GradientDrawable();
            //   gd.setColor(Color.parseColor("#9C27B0"));
            //   gd.setCornerRadius(5);
            //     gd.setStroke(10, getResources().getColor(R.color.com_facebook_blue));
            // item.setBackground(gd);
            //items.remove(item.getText().toString());
            // items.add(item.getText().toString())
            // item.setBackground(R.drawable.editshape);
        }
        else {
            selected[Integer.parseInt(item.getTag().toString())] = true;
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(Color.parseColor("#B2FF59"));
            //item.setBackground(gd);
            //       items.add(item.getText().toString());
            // items.put(item.getText().toString(),1);
        }
    }
    public void addItem2(View view){
        TextView item=(TextView) view;
        if(selected[Integer.parseInt((item.getTag().toString()))]){
            selected[Integer.parseInt(item.getTag().toString())] = false;



        }
    }
}
