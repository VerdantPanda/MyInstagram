package com.example.myinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //TODO: To make fields private you must remove ButterKnife. Do so if necessary later!!
    @BindView(R.id.etUsernameInput)
    EditText etUsernameInput;
    @BindView(R.id.etPasswordInput)
    EditText etPasswordInput;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btSignUp)
    Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //getSupportActionBar().setLogo(R.drawable.nav_logo_whiteout);
        //getSupportActionBar().setBackgroundDrawable();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "LOGIN ATTEMPT OCCURED", Toast.LENGTH_LONG).show();
                final String username = etUsernameInput.getText().toString();
                final String password = etPasswordInput.getText().toString();
                login(username, password);
                etPasswordInput.clearFocus();
            }
        });


        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "sign up clicked", Toast.LENGTH_LONG).show();
                Intent toSignUpActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toSignUpActivity);
                final String username = etUsernameInput.getText().toString();
                final String password = etPasswordInput.getText().toString();
                //registerUser(username, password);


            }
        });


//        // user persist
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser != null) {
//            Intent toHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
//            startActivity(toHomeActivity);
//            finish();
//        }

    }


    /*
        Login Function
     */
    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity", "Login Successful!");

                    Intent toHomeActivity = new Intent(MainActivity.this, HomeActivity.class);

                    ParseUser.getCurrentUser().saveEventually();
                    startActivity(toHomeActivity);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed. Try Again!", Toast.LENGTH_LONG).show();
                    Log.d("LoginActivity", "Failed");
                    Log.d("LoginActivity", e.toString());

                }
            }
        });
    }

    private void registerUser(String username, String password){
        Toast.makeText(getApplicationContext(), "Sign up 2 clicked", Toast.LENGTH_LONG).show();
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        //user.setEmail(etEmail.getText().toString());
        user.put("handle", username);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    login(etUsernameInput.getText().toString(), etPasswordInput.getText().toString());
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong

                    Toast.makeText(getApplicationContext(), "Sign up FAILED", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
