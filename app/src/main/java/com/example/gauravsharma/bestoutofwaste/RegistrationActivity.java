package com.example.gauravsharma.bestoutofwaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gauravsharma.bestoutofwaste.Model.Address;
import com.example.gauravsharma.bestoutofwaste.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private Button registrationButton;
    private EditText emailEditText;
    private EditText phoneEditText;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationButton = findViewById(R.id.registrationButton);
        emailEditText = findViewById(R.id.emailRegisterInput);
        phoneEditText = findViewById(R.id.phoneRegisterInput);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();

        AutoFillForm();

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = ((EditText) findViewById(R.id.firstNameRegisterInput)).getText().toString().trim();
                final String lastName = ((EditText) findViewById(R.id.lastNameRegisterInput)).getText().toString().trim();
                final String email = emailEditText.getText().toString().trim();
                final String phone = phoneEditText.getText().toString().trim();

                String streetAddress1 = ((EditText) findViewById(R.id.streetAddress1Registerinput)).getText().toString().trim();
                String streetAddress2 = ((EditText) findViewById(R.id.streetAddress2RegisterInput)).getText().toString().trim();
                String landmark = ((EditText) findViewById(R.id.landmarkRegisterInput)).getText().toString().trim();
                String city = ((EditText) findViewById(R.id.cityRegisterInput)).getText().toString().trim();
                String state = ((EditText) findViewById(R.id.stateRegisterInput)).getText().toString().trim();
                String country = ((EditText) findViewById(R.id.countryRegisterInput)).getText().toString().trim();

                final Address address = new Address(currentUser.getUid(),streetAddress1, streetAddress2, landmark, city, state, country);
                registerUser(firstName, lastName, email, phone, address);
            }
        });
    }

    private void AutoFillForm() {
        if (currentUser.getEmail() != null) {
            emailEditText.setText(currentUser.getEmail());
            emailEditText.setEnabled(false);
        }
        if (currentUser.getPhoneNumber() != null) {
            phoneEditText.setText(currentUser.getPhoneNumber());
            phoneEditText.setEnabled(false);
        }
    }

    private void registerUser(String firstName, String lastName, String email, String phone, Address address) {
        mDatabase.child("Address").child(currentUser.getUid()).setValue(address);
        User user = new User(currentUser.getUid(), firstName, lastName, email, phone, address, true);
        mDatabase.child("User").child(currentUser.getUid()).setValue(user);
        startActivity(new Intent(RegistrationActivity.this, IntroActivity.class));
    }
}
