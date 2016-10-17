package com.code.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.code.controller.Controller;
import com.code.database.Var;
import com.code.model.Categories;
import com.code.model.Product;
import com.code.model.ProductDetail;
import com.code.util.UtilHttpConnect;
import com.code.util.UtilNetwork;
import com.trncic.library.DottedProgressBar;
import com.videumcorp.android.tabmain.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong on 10/19/2015.
 */
public class ViewLoadingActivity extends Activity {
    DottedProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Controller.setActivitySave(ViewLoadingActivity.this);
        setContentView(R.layout.activity_view_loading);
        if(UtilNetwork.checkNetwork()==false){
            Toast.makeText(ViewLoadingActivity.this,"Không có liên kết mạng",Toast.LENGTH_SHORT).show();
            finish();
            return ;

        }
        com.code.database.Var.InitImage();

        progressBar = (DottedProgressBar) findViewById(R.id.progress);
        progressBar.startProgress();
        new Downloader().execute();

    }

    class Downloader extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * Getting product details in background thread
         */
        protected String doInBackground(String... params) {

            if (UtilNetwork.checkNetwork()) {
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkgetallProduct, UtilHttpConnect.GET, param);
                if (json == null) {
                    Log.e("tag", "null json");
                }

                try {
                    int success = json.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = json.getJSONArray("Product");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Product product = new Product();

                            try {
                                product.setProduct_ID(Integer.parseInt(jsonArray.getJSONObject(i).getString("ProductId")));
                                product.setCategory(new Categories(Integer.parseInt(jsonArray.getJSONObject(i).getString("CategoriesId")), "", ""));
                                // new detail //
                                ProductDetail detail = new ProductDetail();
                                detail.setProductdetail_ID(Integer.parseInt(jsonArray.getJSONObject(i).getString("ProductDetailId")));
                                detail.setComment(jsonArray.getJSONObject(i).getString("Comment"));
                                detail.setCompany(jsonArray.getJSONObject(i).getString("Company"));
                                detail.setDescription(jsonArray.getJSONObject(i).getString("Description"));
                                detail.setInfomation(jsonArray.getJSONObject(i).getString("Infomation"));
                                detail.setLink_image(jsonArray.getJSONObject(i).getString("Image"));
                                detail.setTitle(jsonArray.getJSONObject(i).getString("Title"));
                                detail.setPrice(Float.parseFloat(jsonArray.getJSONObject(i).getString("Price")));

                                product.setDetail(detail);
                                product.setQuanlity(Integer.parseInt(jsonArray.getJSONObject(i).getString("Quanlity")));

                                //  Var.mproductdetail.add(detail);
                                Var.mproduct.add(product);
                            } catch (Exception e) {
                                e.printStackTrace();

                            }


                        }

                        android.util.Log.v("size", "" + Var.mproduct.size());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            progressBar.stopProgress();
            Controller.getInstance().showLoginActivity();
            finish();

        }

    }
}
