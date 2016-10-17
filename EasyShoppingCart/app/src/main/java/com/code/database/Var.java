package com.code.database;

import com.code.model.Customer;
import com.code.model.Product;
import com.code.model.ProductDetail;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dong on 10/4/2015.
 */
public class Var {
    //link
    //public static String ip = "http://192.168.1.79";
    public static String ip = "http://192.168.43.97";
    public static String linkgetallProduct = ip + "/EasyShopping/getAllProduct.php";
    public static String linkgetallArticle = ip + "/EasyShopping/getAllArticle.php";
    public static String linkchecklogincustomer = ip + "/EasyShopping/checkLoginCustomer.php";
    public static String linkaddcart = ip + "/EasyShopping/createCart.php";
    public static String linkaddorder = ip + "/EasyShopping/createOrder.php";
    public static String linkaddarticle = ip + "/EasyShopping/createArticle.php";
    public static String linkaddcart_item = ip + "/EasyShopping/create_product_cart.php";


    public static String DATABASE_LOGIN_CUSTOMEREMAIL = "email";
    public static String DATABASE_LOGIN_CUSTOMERPASSWORD = "password";
    public static String DATABASE_GETCART_CUSTOMERID = "customerID";
    public static String DATABASE_GETPRODUCT_BY_CART_CARTID = "cartID";

    public static ArrayList<Product> mproduct = new ArrayList<Product>();
    public static ArrayList<ProductDetail> mproductdetail = new ArrayList<ProductDetail>();
    public static Customer curCustomer = new Customer();
    public static int cartID_current;

    public static ArrayList<String> productid_cart = new ArrayList<String>();
    public static ArrayList<String> productid_recommend = new ArrayList<String>();
    public static ArrayList<String> productid_history = new ArrayList<String>(){
        {
            add("4");
            add("7");

        }
    };

    public static HashMap<String,Integer> mapImage = new HashMap<String, Integer>();
    public static void InitImage(){
        mapImage.put("http://img.mediamart.vn/Product/27442_18863_macbook-pro-retina-mjlq2zpa-mid-2015.jpg", R.drawable.item1);
        mapImage.put("http://img.mediamart.vn/Product/24029_16549_macbook-pro-133-mf839zpa.jpg", R.drawable.item2);
        mapImage.put("http://mediamart.vn/Images/Upload/download/2015-7/mf840zpa_26d4467f-2d54-4155-bc69-41dac374cdaf.png", R.drawable.item3);
        mapImage.put("http://img.mediamart.vn/Product/26618_18469_macbook-pro-133-mf841zpa.jpg", R.drawable.item4);
        mapImage.put("http://img.mediamart.vn/Product/21702_15501_macbook-pro-retina-13-md101zpa.jpg", R.drawable.item5);
        mapImage.put("http://img.mediamart.vn/Product/18925_14202_may-tinh-ban-apple-imac-mf883.jpg", R.drawable.item6);
        mapImage.put("http://mediamart.vn/Images/Upload/download/2015-7/me086_26d5e168-6694-4777-8b76-60cdc8c2525f.png", R.drawable.item7);
        mapImage.put("http://mediamart.vn/Images/Upload/download/2014-9/iphone-6_a9eb8ee3-cc87-418f-898c-713b90608448.png", R.drawable.item8);
        mapImage.put("http://img.mediamart.vn/Product/531f605d-4cd4-4037-8490-ea9e7833332b_apple-iphone-5s-champagne-gold.jpg", R.drawable.item9);
        mapImage.put("http://img.mediamart.vn/Product/29133_pin-du-tru-iphone-ipod.jpg", R.drawable.item10);
        mapImage.put("http://img.mediamart.vn/Product/9607_tai-nghe-bluetooth-iphone-4s.jpg", R.drawable.item11);
        mapImage.put("http://img.mediamart.vn/Product/12133_may-nghe-nhac-ipod-shuffle-new-2gb.jpg", R.drawable.item12);

    }
}

