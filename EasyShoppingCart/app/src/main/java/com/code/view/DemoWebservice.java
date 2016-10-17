package com.code.view;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.videumcorp.android.tabmain.GetUrlTxt;
import com.videumcorp.android.tabmain.R;

public class DemoWebservice extends ActionBarActivity {
    //http://localhost:8080/Service_Tuvan/tu_van?Tester
    private static String URL = "http://192.168.1.79:8080/Service_Tuvan/tu_van?WSDL";
    private static String NAMESPACE = "http://tuvan.com";
    private static String METHOD_NAME = "hello";
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_webservice);
        txtView = (TextView) findViewById(R.id.demowebservice);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        AsyncCallWS task = new AsyncCallWS();
        //Call execute
//        task.execute();
        String txt = GetUrlTxt.gettxtFromUrlHtpp("http://192.168.1.79:8080/Service_Tuvan/tuvan.jsp?id=6");
        txtView.setText(txt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo_webservice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
