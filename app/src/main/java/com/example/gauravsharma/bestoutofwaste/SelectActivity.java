package com.example.gauravsharma.bestoutofwaste;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectActivity extends MenuActivity {

    private Button nextButton;
    Boolean[] selected = new Boolean[10];
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        super.onCreateDraw();

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.isEmpty()) return;
                Intent intent = new Intent(getApplicationContext(), VerifySelectedActivity.class);
                intent.putStringArrayListExtra("items",items);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Arrays.fill(selected, false);
        items.clear();
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getResources().getColor(R.color.colorAccent));
        ((Button)findViewById(R.id.itemNewsPaper)).setBackground(gd);
        ((Button)findViewById(R.id.itemElectronics)).setBackground(gd);
    }

    public void addItem(View view) {
        Button item = (Button) view;
        if(selected[Integer.parseInt(item.getTag().toString())]) return;
        selected[Integer.parseInt(item.getTag().toString())] = true;
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getResources().getColor(R.color.colorAccent));
        gd.setCornerRadius(5);
        gd.setStroke(10, getResources().getColor(R.color.com_facebook_blue));
        item.setBackground(gd);
        items.add(item.getText().toString());
    }
}
