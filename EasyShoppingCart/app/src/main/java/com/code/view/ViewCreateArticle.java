package com.code.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.code.controller.Controller;
import com.code.database.Var;
import com.code.util.UtilHttpConnect;
import com.videumcorp.android.tabmain.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewCreateArticle extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_create;
    TextView txt_address, txt_price, txt_description, txt_title, txt_phonenumber, txt_link;
    private ProgressDialog progressDialog;
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_create_article);
        Controller.setActivitySave(ViewCreateArticle.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TypedValue typedValueColorPrimaryDark = new TypedValue();
        ViewCreateArticle.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
        //-------------------------------------------------
        btn_create = (Button) findViewById(R.id.activity_create_article_btn_create);
        txt_address = (TextView) findViewById(R.id.activity_create_article_input_address);
        txt_description = (TextView) findViewById(R.id.activity_create_article_input_des);
        txt_title = (TextView) findViewById(R.id.activity_create_article_input_title);
        txt_price = (TextView) findViewById(R.id.activity_create_article_input_price);
        txt_phonenumber = (TextView) findViewById(R.id.activity_create_article_input_phonenumber);
        txt_link = (TextView) findViewById(R.id.activity_create_article_link);
        progressDialog = new ProgressDialog(ViewCreateArticle.this,
                R.style.Base_Theme_AppCompat_Dialog);
        //------------------------------------------------
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CreateNewArticle().execute();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_create_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //---------------------------------------------------------
    class CreateNewArticle extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Đang xử lý");
            progressDialog.show();

        }

        protected String doInBackground(String... args) {


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("customer_id", Var.curCustomer.getCumtomer_ID() + ""));
            params.add(new BasicNameValuePair("title", txt_title.getText().toString().trim()));
            params.add(new BasicNameValuePair("description", txt_description.getText().toString().trim()));
            params.add(new BasicNameValuePair("price", txt_price.getText().toString().trim()));
            params.add(new BasicNameValuePair("phonenumber", txt_phonenumber.getText().toString().trim()));
            params.add(new BasicNameValuePair("address", txt_address.getText().toString().trim()));
            params.add(new BasicNameValuePair("link", txt_link.getText().toString().trim()));


            JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkaddarticle, UtilHttpConnect.GET, params);


            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success > 0) {
                    return "ok";

                } else {
                    Log.e("Order error", "k thêm được article");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 0);
        }

    }
}
