package com.example.gauravsharma.bestoutofwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gauravsharma.bestoutofwaste.Model.Address;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlaceDonationActivity  extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    DatabaseReference myRef;
    String getAddress;
    Address address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_donation);
        //  super.onCreateDraw();
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
        myRef.child("User").child(mAuth.getUid()).child("address").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getAddress = dataSnapshot.getValue(String.class);
                fillAddress();
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void fillAddress() {
        myRef.child("Address").child(getAddress).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                address = dataSnapshot.getValue(Address.class);
                fillAddress2();
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void fillAddress2() {
        String str = "";
        str = address.streetAddress1 + " ";
        str = str + address.streetAddress2 + "\n";
        str = str + address.pinCode + " " + address.city + "\n";
        str = str + address.state + " " + address.country + "\n";
        TextView add = findViewById(R.id.addressPlaceDonationText);
        add.setText(str);
    }
}
