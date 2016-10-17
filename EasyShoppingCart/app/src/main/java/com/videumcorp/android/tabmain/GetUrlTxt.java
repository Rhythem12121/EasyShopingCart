/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videumcorp.android.tabmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author TRITUEVIET
 */
public class GetUrlTxt {

    
    public static String gettxtFromUrlHtpp(String url) {
    	int type=0;
        String txt = "";
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            // default is GET
            conn.setRequestMethod("GET");
            conn.setUseCaches(true);
            // act like a browser
            conn.setRequestProperty("User-Agent", Var.USER_AGENT);
            conn.setRequestProperty("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            if (type == 1) {
                for (String cookie : Var.cookies2) {
                    conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
                }
            }
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            txt = response.toString();

            if (type == 0) {
                Var.cookies = conn.getHeaderFields().get("Set-Cookie");
            }

            if (Var.cookies != null) {
                System.out.println(" size cookie: " + Var.cookies.size());
                System.out.println(" size cookie: " + Var.cookies.size() + "");
                System.out.println(" cookie get txt from url: " + Var.cookies.get(0));
            }
            return txt;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}