package com.code.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class UtilHttpConnect {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    public static String POST = "POST";
    public static String GET = "GET";

    /*
     * List<NameValuePair> params = new ArrayList<NameValuePair>(); JSONObject
     * json = jParser.makeHttpRequest(url_all_products, "GET", params);
     *
     *
     * List<NameValuePair> params = new ArrayList<NameValuePair>();
     * params.add(new BasicNameValuePair("pid", pid));
     * JSONObject json =jsonParser.makeHttpRequest(url_product_detials, "GET", params);
     */
    public static JSONObject makeHttpRequest(String url, String method,
                                             List<NameValuePair> params) {
        try {
            if (method == "POST") {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } else if (method == "GET") {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            
            json = sb.toString();
            Log.e("tag",json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        try {
            jObj = new JSONObject(json);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return jObj;

    }
}
