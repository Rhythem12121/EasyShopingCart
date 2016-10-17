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
import android.widget.Toast;

import com.code.database.Var;
import com.code.util.UtilHttpConnect;
import com.videumcorp.android.tabmain.GetUrlTxt;
import com.videumcorp.android.tabmain.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_order;
    TextView txt_address, txt_type, txt_description, txt_price;
    private ProgressDialog progressDialog;
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        ViewOrderActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
        btn_order = (Button) findViewById(R.id.activity_order_btn_order);
        txt_address = (TextView) findViewById(R.id.activity_order_input_address);
        txt_description = (TextView) findViewById(R.id.activity_order_input_description);
        txt_type = (TextView) findViewById(R.id.activity_order_input_type);
        txt_price = (TextView) findViewById(R.id.activity_order_input_price);
        progressDialog = new ProgressDialog(ViewOrderActivity.this,
                R.style.Base_Theme_AppCompat_Dialog);
        int sizes = 0;
        for (int i = 0; i < Var.productid_cart.size(); i++){
            int index = Integer.parseInt(Var.productid_cart.get(i));
            sizes+= Var.mproduct.get(index).getDetail().getPrice();
        }
        txt_price.setText(sizes+"");
        txt_price.setEnabled(false);


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đang xử lý");

                progressDialog.show();
                String text = "id=" + Var.curCustomer.getCumtomer_ID() + "&update=";
                Var.productid_history.add(Var.curCustomer.getCumtomer_ID()+"");
                for (int i = 0; i < Var.productid_cart.size(); i++)
                    if (i != 0) text = text + "_" + Var.productid_cart.get(i);
                    else text = text + Var.productid_cart.get(i);
                String checkout = GetUrlTxt.gettxtFromUrlHtpp(Var.ip + ":8080/Service_Tuvan/updatecart.jsp?" + text);
                if (checkout.equals("ok"))
                    new CreateNewCart().execute();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_order, menu);
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

    class CreateNewCart extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("customerid", Var.curCustomer.getCumtomer_ID() + ""));
            params.add(new BasicNameValuePair("description", "không rõ"));
            // getting JSON Object
            // Note that create product url accepts POST method

            JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkaddcart, UtilHttpConnect.GET, params);


            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success > 0) {
                    Var.cartID_current = success;
                    return "ok";

                } else {
                    Log.e("Order error", "k thêm được cart");
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
            if (file_url != null) {
                new CreateNewOrder().execute();
            }
        }

    }

    class CreateNewOrder extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {

            // Building Parameters
            if (txt_address.getText().toString() != null && txt_type.getText().toString() != null
                    && txt_description.getText().toString() != null && txt_price.getText().toString() != null
                    ) {


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("CartId", Var.cartID_current + ""));
                params.add(new BasicNameValuePair("Address", txt_address.getText().toString()));
                params.add(new BasicNameValuePair("Card", txt_type.getText().toString()));
                params.add(new BasicNameValuePair("Status", txt_description.getText().toString()));
                params.add(new BasicNameValuePair("Price", txt_price.getText().toString()));
                // getting JSON Object
                // Note that create product url accepts POST method

                JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkaddorder, UtilHttpConnect.GET, params);


                // check log cat fro response
                Log.d("Create Response", json.toString());

                // check for success tag
                try {
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.e("Order error", " thêm được order");

                    } else {
                        Log.e("Order error", "k thêm được order");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "ok";
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            if (file_url != null) {
                Log.d("create order", "cart_size: " + Var.productid_cart.size());
                for (int i = 0; i < Var.productid_cart.size(); i++)
                    new CreateNewCart_item().execute(Integer.parseInt(Var.productid_cart.get(i)) + "");
            }
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(ViewOrderActivity.this, "Đặt hàng thành công", Toast.LENGTH_LONG).show();
                        }
                    }, 2000);


        }

    }

    class CreateNewCart_item extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {

            // Building Parameters
            String index = args[0];
            int int_index = Integer.parseInt(index);
            Log.d("create newcart_item", "index: " + index);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("CartId", Var.cartID_current + ""));
            params.add(new BasicNameValuePair("ProductId", Var.mproduct.get(int_index).getProduct_ID() + ""));
            params.add(new BasicNameValuePair("Quanlity", Var.mproduct.get(int_index).getQuanlity() + ""));

            // getting JSON Object
            // Note that create product url accepts POST method

            JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkaddcart_item, UtilHttpConnect.GET, params);


            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Log.e("Order error", " thêm được cart_item");

                } else {
                    Log.e("Order error", "k thêm được cart_item");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

        }

    }
}
