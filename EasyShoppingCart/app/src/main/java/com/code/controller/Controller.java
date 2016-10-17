package com.code.controller;

import android.app.Activity;
import android.content.Intent;

import com.code.model.Product;
import com.code.view.SearchAdvanceActivity;
import com.code.view.ViewBroadActivity;
import com.code.view.ViewCartActivity;
import com.code.view.ViewCreateArticle;
import com.code.view.ViewDetailProductActivity;
import com.code.view.ViewHistoryActivity;
import com.code.view.ViewLoginActivity;
import com.code.view.ViewOrderActivity;
import com.code.view.ViewSignupActivity;
import com.videumcorp.android.tabmain.MainActivity;


public class Controller {

    private static Controller controller = null;
    private static Activity activitySave = null;

    public static Controller getInstance() {
        if (controller == null) {
            return new Controller();
        }
        return controller;
    }

    public void showMainActivity() {
        log("--run-- main");
        Intent intent = new Intent(getActivitySave(), MainActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showSignupActivity() {
        log("--run-- Signup");
        Intent intent = new Intent(getActivitySave(), ViewSignupActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showLoginActivity() {
        log("--run--login");
        Intent intent = new Intent(getActivitySave(), ViewLoginActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showProductDetailActivity(Product product) {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), ViewDetailProductActivity.class);
        intent.putExtra("product", product);
        getActivitySave().startActivity(intent);
    }

    public void showSearchActivity() {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), SearchAdvanceActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showCartActivity() {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), ViewCartActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showCreateArticle() {
        Intent intent = new Intent(getActivitySave(), ViewCreateArticle.class);
        getActivitySave().startActivity(intent);
    }

    public void showOrderActivity() {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), ViewOrderActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showHistoryActivity() {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), ViewHistoryActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void showBroadActivity() {
        log("--run-- product detail");
        Intent intent = new Intent(getActivitySave(), ViewBroadActivity.class);
        getActivitySave().startActivity(intent);
    }

    public void exitApp() {
        getActivitySave().moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }

    public static void log(String txt) {
        android.util.Log.v("log app: ", txt);
    }

    public static Activity getActivitySave() {
        return activitySave;
    }

    public static void setActivitySave(Activity activitySave) {
        Controller.activitySave = activitySave;
    }

}
