package com.example.intramuralsappfinal;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intramuralsappfinal.Tasks.RegisterTask;
import com.example.intramuralsappfinal.models.request.RegisterRequest;
import com.example.intramuralsappfinal.models.response.RegisterResponse;
import com.example.intramuralsappfinal.presenter.RegisterPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;


public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.View, RegisterTask.Observer {

    private static final String LOG_TAG = "Register Activity";

    private RegisterPresenter presenter;
    private Toast registerInToast;

    private FirebaseAuth mAuth;

    public void openLoginActivity() {
        Intent intent = new Intent(this, com.example.intramuralsappfinal.LoginActivity.class);
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);

        Button registerButton = findViewById(R.id.RegisterButton);

        EditText editTextNetID = findViewById(R.id.NetID);
        EditText editTextPassword = findViewById(R.id.Password);
        EditText editTextEmail = findViewById(R.id.Email);
        Spinner editTextSchool = (Spinner) findViewById(R.id.School);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.schools, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextSchool.setAdapter(adapter);
        EditText editTextNumber = findViewById(R.id.Number);
        //TODO: Change to Gender
        //EditText editTextEmail = findViewById(R.id.Email);

        TextView textViewLogin = findViewById(R.id.LoginButton);

        mAuth.createUserWithEmailAndPassword(editTextEmail.toString(), editTextPassword.toString())
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

        registerButton.setClickable(true);
        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Changes to a Register View
             */
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Makes a login request. The user is hard-coded, so it doesn't matter what data we put
             * in the LoginRequest object.
             *
             * @param view the view object that was clicked.
             */
            @Override
            public void onClick(View view) {
                createAccount(editTextEmail.toString(), editTextPassword.toString());
                registerInToast = Toast.makeText(com.example.intramuralsappfinal.RegisterActivity.this, "Logging In, " + editTextNetID.getText().toString(), Toast.LENGTH_LONG);
                registerInToast.show();
            }
        });
    }

    @Override
    public void onBackPressed() {}

    /**
     * The callback method that gets invoked for a successful login. Displays the MainActivity.
     *
     * @param registerResponse the response from the login request.
     */
    @Override
    public void registerSuccessful(RegisterResponse registerResponse) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, registerResponse.getUser());

        registerInToast.cancel();
        startActivity(intent);
    }

    /**
     * The callback method that gets invoked for an unsuccessful login. Displays a toast with a
     * message indicating why the login failed.
     *
     * @param registerResponse the response from the login request.
     */
    @Override
    public void registerUnsuccessful(RegisterResponse registerResponse) {
        Toast.makeText(this, "Failed to register. " + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * A callback indicating that an exception was thrown in an asynchronous method called on the
     * presenter.
     *
     * @param exception the exception.
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, "Failed to register because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}