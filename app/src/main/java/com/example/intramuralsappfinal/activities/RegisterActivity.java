package com.example.intramuralsappfinal.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.models.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;


public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Register Activity";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private EditText editTextName;
    private EditText editTextNetID;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Spinner editTextSchool;
    private EditText editTextNumber;
    private RadioGroup rGroup;
    private RadioButton rButton;
    private Button btnDisplay;


    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mDatabase.child("users").child(user.getUid()).child("email").setValue(editTextEmail.getText().toString());
            mDatabase.child("users").child(user.getUid()).child("gender").setValue(rButton.getText().toString());
            mDatabase.child("users").child(user.getUid()).child("name").setValue(editTextName.getText().toString());
            mDatabase.child("users").child(user.getUid()).child("netid").setValue(editTextNetID.getText().toString());
            mDatabase.child("users").child(user.getUid()).child("phoneNumber").setValue(editTextNumber.getText().toString());
            mDatabase.child("users").child(user.getUid()).child("school").setValue(editTextSchool.getSelectedItem().toString());
        }
    }
    public void registerNewUser() {
        mAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("We made it into onComplete");
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            System.out.println("This is after updateUI()");
                            boolean isMale = true;
                            if(!rButton.getText().toString().equals("Male")) {
                                isMale = false;
                            }
                            User newUser = new User(editTextName.getText().toString(), editTextNetID.getText().toString(), editTextEmail.getText().toString(), editTextNumber.getText().toString(), isMale ? "Male" : "Female", editTextSchool.toString(), null);
                            registerSuccessful();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.RegisterButton);
        editTextName = findViewById(R.id.Name);
        editTextNetID = findViewById(R.id.NetID);
        editTextPassword = findViewById(R.id.Password);
        editTextEmail = findViewById(R.id.Email);
        editTextSchool = (Spinner) findViewById(R.id.School);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.schools, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextSchool.setAdapter(adapter);
        editTextNumber = findViewById(R.id.Number);
        rGroup = (RadioGroup) findViewById(R.id.radioGrp);
        int selectedID = rGroup.getCheckedRadioButtonId();
        rButton = (RadioButton) findViewById(selectedID);

        TextView textViewLogin = findViewById(R.id.LoginButton);

        registerButton.setClickable(true);
        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Changes to a Register View
             */
            @Override
            public void onClick(View view) {
                //Register
                registerNewUser();

            }
        }) ;


    }


    public void registerSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}