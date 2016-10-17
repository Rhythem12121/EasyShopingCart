package com.code.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.videumcorp.android.tabmain.R;

/**
 * Created by dong on 10/4/2015.
 */
public class ViewSignupActivity extends Activity {
    private static final String TAG = "SignupActivity";
    private static final int REQUESTSIGNUP = 1;

    private EditText emailText;
    private EditText passwordText;
    private EditText fullnameText;
    private EditText usernameText;
    private Button loginSignup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_signup);
        emailText = (EditText) findViewById(R.id.activity_signup_input_email);
        passwordText = (EditText) findViewById(R.id.activity_signup_input_password);
        fullnameText = (EditText) findViewById(R.id.activity_signup_input_fullname);
        usernameText = (EditText) findViewById(R.id.activity_signup_input_name);
        loginSignup = (Button) findViewById(R.id.activity_signup_btn_signup);

        loginSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    public void signUp() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        loginSignup.setEnabled(false);

        // TODO: Implement your own authentication logic here.

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTSIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onSignupSuccess() {
        loginSignup.setEnabled(true);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
