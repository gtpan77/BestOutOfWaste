package com.example.gauravsharma.bestoutofwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gauravsharma.bestoutofwaste.Model.UserScrapItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDonationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    List<UserScrapItem> listItems;
    public ArrayList<String> userOrderIDs;
    public int ctr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_donation);

        Log.i("msg", "entering");

        recyclerView = (RecyclerView) findViewById(R.id.donationsRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listItems = new ArrayList<>();


//        for(int i=0;i<=10;i++){
//            UserScrapItem item = new UserScrapItem("Heading " + (i+1), "Lorem Ipsum Bacon Ipsum");
//            listItems.add(item);
//        }

        // specify an adapter
        get();
//        adapter = new MyAdapter(listItems);
//        recyclerView.setAdapter(adapter);
    }

    public void get() {
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef.child("User").child("userDonation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
                };
                userOrderIDs = dataSnapshot.getValue(t);
                addDonations();
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void addDonations() {
        for (String x : userOrderIDs) {
            myRef = FirebaseDatabase.getInstance().getReference();
            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            myRef.child("UserScrapItem").child(x).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserScrapItem temp = dataSnapshot.getValue(UserScrapItem.class);
                    listItems.add(temp);
                    ctr++;
                    if (ctr == userOrderIDs.size())
                        call();
                    return;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    void call() {
        adapter = new MyAdapter(listItems);
        recyclerView.setAdapter(adapter);
    }
}
