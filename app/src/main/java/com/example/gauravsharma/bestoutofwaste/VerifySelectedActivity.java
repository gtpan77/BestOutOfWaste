package com.example.gauravsharma.bestoutofwaste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class VerifySelectedActivity extends MenuActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_selected);
        super.onCreateDraw();

        Intent intent = getIntent();

        ArrayList<String> items = new ArrayList<>(intent.getStringArrayListExtra("items"));

        ListView itemListView = findViewById(R.id.itemsListView);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);

        itemListView.setAdapter(arrayAdapter);

        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
