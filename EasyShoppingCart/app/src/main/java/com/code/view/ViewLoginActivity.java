package com.code.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.code.controller.Controller;
import com.code.database.Var;
import com.code.model.Customer;
import com.code.util.UtilHttpConnect;
import com.videumcorp.android.tabmain.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewLoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUESTSIGNUP = 0;
    private static final String TAG_CUSTOMER = "customer";

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signupLink;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);
        Controller.setActivitySave(ViewLoginActivity.this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        emailText = (EditText) findViewById(R.id.activity_login_input_email);
        passwordText = (EditText) findViewById(R.id.activity_login_input_password);
        loginButton = (Button) findViewById(R.id.activity_login_btn_login);
        signupLink = (TextView) findViewById(R.id.activity_login_link_signup);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
               /* Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUESTSIGNUP);*/
                Controller.getInstance().showSignupActivity();
            }
        });
        //  new Downloader().execute();
        progressDialog = new ProgressDialog(ViewLoginActivity.this,
                R.style.Base_Theme_AppCompat_Dialog);

    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);


        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Đang kiểm tra...");

        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        new CheckLoginCustomer().execute();

    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Controller.getInstance().showMainActivity();
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
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

    class CheckLoginCustomer extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(Var.DATABASE_LOGIN_CUSTOMEREMAIL, emailText.getText().toString()));
            params.add(new BasicNameValuePair(Var.DATABASE_LOGIN_CUSTOMERPASSWORD, passwordText.getText().toString()));
            // getting JSON string from URL
            JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkchecklogincustomer, UtilHttpConnect.GET, params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                int success = json.getInt("success");
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    JSONArray jsonArray = json.getJSONArray(TAG_CUSTOMER);
                    // looping through All Products
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        Var.curCustomer = new Customer();
                        Var.curCustomer.setCumtomer_ID(Integer.parseInt(c.getString("CustomerId")));
                        Var.curCustomer.setFullname(c.getString("FullName"));
                        Var.curCustomer.setUsername(c.getString("UserName"));
                        Var.curCustomer.setPassword(c.getString("Password"));
                        Var.curCustomer.setAddress(c.getString("Address"));
                        Var.curCustomer.setEmail(c.getString("Email"));
                        Var.curCustomer.setGender(c.getString("Gender"));

                        // convert time//
                        String dateofbirth = c.getString("Birthday");
                        try {
                            String year = dateofbirth.substring(0, 4);
                            String month = dateofbirth.substring(5, 7);
                            String day = dateofbirth.substring(8, 10);
                            Log.d(TAG, year + "-" + month + "-" + day);
                            java.sql.Date sql = new java.sql.Date(0);
                            sql.setYear(Integer.parseInt(year));
                            sql.setMonth(Integer.parseInt(month));
                            sql.setDate(Integer.parseInt(day));
                            Var.curCustomer.setDateofbirth(sql);

                        } catch (Exception e) {

                        }

                    }
                } else {
                    Log.d(TAG, "loi k co customer");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 1000);

        }

    }


}
