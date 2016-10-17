package com.code.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.code.controller.Controller;


public class UtilNetwork {
    public static boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) Controller.getActivitySave().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null
                && connManager.getActiveNetworkInfo().isAvailable()
                && connManager.getActiveNetworkInfo().isConnected())
            return true;
        return false;

    }
}
