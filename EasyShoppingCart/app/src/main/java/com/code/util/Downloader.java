package com.code.util;

import android.os.AsyncTask;
import android.util.Log;

import com.code.database.Var;
import com.code.model.Categories;
import com.code.model.Product;
import com.code.model.ProductDetail;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong on 10/13/2015.
 */
public class Downloader extends AsyncTask<String, String, String>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    /**
     * Getting product details in background thread
     * */
    protected String doInBackground(String... params) {

        if (UtilNetwork.checkNetwork()) {
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkgetallProduct, UtilHttpConnect.GET, param);
            if(json ==null){
                Log.e("tag","null json");
            }

            try {
                int success = json.getInt("success");
                if (success ==1) {
                    JSONArray jsonArray = json.getJSONArray("Product");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Product product = new Product();

                        try {
                            product.setProduct_ID(Integer.parseInt(jsonArray.getJSONObject(i).getString("ProductId")));
                            product.setCategory(new Categories(Integer.parseInt(jsonArray.getJSONObject(i).getString("CategoriesId")),"",""));
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
     * **/
    protected void onPostExecute(String file_url) {


    }


    //------------------------------------------
}
