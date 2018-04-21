package com.example.gauravsharma.bestoutofwaste;

import com.example.gauravsharma.bestoutofwaste.Model.Address;
import com.example.gauravsharma.bestoutofwaste.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GlobalMembers {
    static private FirebaseAuth mAuth;
   static FirebaseUser currentUser;
    static DatabaseReference mDatabase;
  static public  DatabaseReference myRef;
    public static Address address;
    public static User user;
    static void initiliase(){
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
    }
    static void updateUser(){

        myRef.child("User").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             user=dataSnapshot.getValue(User.class);
            updateAddress(user.address);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    static void updateAddress(String addressKey){
        myRef.child("Address").child(addressKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                address=dataSnapshot.getValue(Address.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
