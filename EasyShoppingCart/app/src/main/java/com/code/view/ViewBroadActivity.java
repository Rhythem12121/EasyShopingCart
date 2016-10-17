package com.code.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.code.adapter.BroadrowitemAdapter;
import com.code.controller.Controller;
import com.code.database.Var;
import com.code.model.Article;
import com.code.util.RecyclerItemClickListener;
import com.code.util.UtilHttpConnect;
import com.code.util.UtilNetwork;
import com.videumcorp.android.tabmain.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ViewBroadActivity extends ActionBarActivity {
    Toolbar toolbar;

    private RecyclerView recyclerView;
    private CardView cardView;
    private ArrayList<Article> marticle = new ArrayList<Article>();

    private RecyclerView.Adapter mAdapter;
    private ProgressDialog progressDialog;
    FloatingActionButton cartbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_broad);
        Controller.setActivitySave(ViewBroadActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        ViewBroadActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
        //-----------------------------------
        initdata();
        cartbutton = (FloatingActionButton) findViewById(R.id.create_button);
        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.getInstance().showCreateArticle();

            }
        });


    }

    public void initdata() {
//        for (Product product : Var.mproduct) {
//            if (product.getCategory().getCategories_ID() == 4 || product.getCategory().getCategories_ID() == 3)
//                mproductother.add(product);
//
//        }
        progressDialog = new ProgressDialog(ViewBroadActivity.this,
                R.style.Base_Theme_AppCompat_Dialog);
        new Downloader().execute();

    }

    private void initContrls() {

        recyclerView = (RecyclerView) findViewById(R.id.broad_my_recycler_view);

        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new BroadrowitemAdapter(marticle);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //  Controller.getInstance().showProductDetailActivity(mproductother.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_broad, menu);
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

    class Downloader extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Đang xử lý");

            progressDialog.show();


        }

        /**
         * Getting product details in background thread
         */
        protected String doInBackground(String... params) {

            if (UtilNetwork.checkNetwork()) {
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                JSONObject json = UtilHttpConnect.makeHttpRequest(Var.linkgetallArticle, UtilHttpConnect.GET, param);
                if (json == null) {
                    Log.e("tag", "null json");
                }

                try {
                    int success = json.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = json.getJSONArray("Article");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Article article = new Article();

                            try {
                                article.setId(Integer.parseInt(jsonArray.getJSONObject(i).getString("ArticleId")));
                                article.setCustomer_id(Integer.parseInt(jsonArray.getJSONObject(i).getString("CustomerId")));
                                article.setTitle(jsonArray.getJSONObject(i).getString("Title"));
                                article.setDescription(jsonArray.getJSONObject(i).getString("Description"));
                                article.setPrice(Float.parseFloat(jsonArray.getJSONObject(i).getString("Price")));
                                article.setPhone_number(jsonArray.getJSONObject(i).getString("Phonenumber"));
                                article.setAddress(jsonArray.getJSONObject(i).getString("Address"));
                                article.setLink_image(jsonArray.getJSONObject(i).getString("Link"));

                                // new detail //

                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            marticle.add(article);
                        }
                        System.out.println("tag: broadactivity" + marticle.size());
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
            initContrls();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 0);
        }

    }

}
