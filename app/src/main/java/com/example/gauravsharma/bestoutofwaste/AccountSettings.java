package com.example.gauravsharma.bestoutofwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.gauravsharma.bestoutofwaste.Model.Address;
import com.example.gauravsharma.bestoutofwaste.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountSettings extends AppCompatActivity {
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    DatabaseReference myRef;
    //Inputs
    private Button save;
    private EditText fName, lName, email, phoneNumber, DOB, streetAddress1, streetAddress2, landMark, city, pinCode, country, state;
    //Firebase-data
    private String addressKey;
    User user;
    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();

        fName = findViewById(R.id.firstNameAccountSettingsInput);
        lName = findViewById(R.id.lastNameAccountSettingsInput);
        phoneNumber = findViewById(R.id.phoneAccountSettingsInput);
        DOB = findViewById(R.id.DOBAccountSettingsInput);
        email = findViewById(R.id.emailAccountSettingsInput);
        streetAddress1 = findViewById(R.id.streetAddress1AccountSettingsInput);
        streetAddress2 = findViewById(R.id.streetAddress2AccountSettingsInput);
        landMark = findViewById(R.id.landmarkAccountSettingsInput);
        city = findViewById(R.id.cityAccountSettingsInput);
        pinCode = findViewById(R.id.pinCodeAccountSettingsInput);
        country = findViewById(R.id.countryAccountSettingsInput);
        state = findViewById(R.id.stateAccountSettingsInput);
        getUser();
    }

    public void getUser() {

        myRef.child("User").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                getAddress();
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getAddress() {
        myRef.child("Address").child(user.address).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                address = dataSnapshot.getValue(Address.class);
                autoFill();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void autoFill() {
        if (user.firstName != null) {
            fName.setText(user.firstName);
        }
        if (user.lastName != null) {
            lName.setText(user.lastName);
        }
        if (user.emailAddress != null) {
            email.setText(user.emailAddress);
        }
        if (user.phoneNumber != null) {
            phoneNumber.setText(user.phoneNumber);
        }
        if (address.streetAddress1 != null) {
            streetAddress1.setText(address.streetAddress1);
        }
        if (address.streetAddress2 != null) {
            streetAddress2.setText(address.streetAddress2);
        }
        if (address.state != null)
            state.setText(address.state);
        if (address.city != null)
            city.setText(address.city);
        if (address.country != null)
            country.setText(address.country);
        if (address.pinCode != null)
            pinCode.setText(address.pinCode);

        save = findViewById(R.id.saveAccountSeetingsButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    public void update() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        if (fName.getText().toString().length() != 0) {
            String f = fName.getText().toString();
            mDatabase.child("User").child(currentUser.getUid()).child("firstName").setValue(f);
        }
        if (lName.getText().toString().length() != 0) {
            String l = fName.getText().toString();
            mDatabase.child("User").child(currentUser.getUid()).child("lasttName").setValue(l);
        }
        if (phoneNumber.getText().toString().length() != 0)
            mDatabase.child("User").child(currentUser.getUid()).child("phoneNumber").setValue(phoneNumber.getText().toString());
        if (email.getText().toString().length() != 0) {
            mDatabase.child("User").child(currentUser.getUid()).child("emailAddress").setValue(email.getText().toString());
        }
        addressKey = user.address;
        updateAddress();
    }

    public void updateAddress() {

        String streetAddress = streetAddress1.getText().toString();
        if (streetAddress.length() != 0) {
            myRef.child("Address").child(addressKey).child("streetAddress1").setValue(streetAddress);
        }
        if (streetAddress2.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("streetAddress2").setValue(streetAddress2.getText().toString());
        }
        if (country.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("country").setValue(country.getText().toString());
        }
        if (city.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("city").setValue(city.getText().toString());
        }
        if (landMark.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("landMark").setValue(landMark.getText().toString());
        }
        if (pinCode.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("pinCode").setValue(pinCode.getText().toString());
        }
        if (state.getText().toString().length() != 0) {
            myRef.child("Address").child(addressKey).child("state").setValue(state.getText().toString());
        }
        finish();
    }
}
