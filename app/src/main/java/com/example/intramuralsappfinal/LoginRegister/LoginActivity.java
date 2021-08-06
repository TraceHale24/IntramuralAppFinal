
package com.example.intramuralsappfinal.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intramuralsappfinal.activities.MainActivity;
import com.example.intramuralsappfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LoginActivity";

    private Toast loginInToast;


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }

        Button loginButton = findViewById(R.id.LoginButton);

        EditText editTextEmail = findViewById(R.id.Email);
        EditText editTextPassword = findViewById(R.id.Password);

        TextView editTextRegister = findViewById(R.id.Register);
        TextView textViewReset = findViewById(R.id.Reset);
        editTextRegister.setClickable(true);
        editTextRegister.setOnClickListener(new View.OnClickListener() {
            /**
             * Changes to a Register View
             */
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });

        textViewReset.setClickable(true);
        textViewReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("YOUR EMAIL IS HERE HOES: " + editTextEmail.getText().toString());
                forgotPassword(editTextEmail.getText().toString());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                //loginInToast = Toast.makeText(com.example.intramuralsappfinal.LoginRegister.LoginActivity.this, "Logging In, " + editTextEmail.getText().toString(), Toast.LENGTH_LONG);
                //loginInToast.show();

                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
                signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());

            }
        });
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

    private void forgotPassword(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Email Has Been Sent", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(task.getResult());
                    Toast.makeText(LoginActivity.this, "Failed, Please try again later!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            String result = mDatabase.child("users").child(user.getUid()).toString();
                            Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                            loginSuccessful();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    public void loginSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        //loginInToast.cancel();
        startActivity(intent);
    }



}