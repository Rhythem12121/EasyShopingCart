package com.code.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.code.adapter.RowViewDataAdapter;
import com.code.controller.Controller;
import com.code.model.Product;
import com.code.util.RecyclerItemClickListener;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

public class SearchAdvanceActivity extends AppCompatActivity {
    Toolbar toolbar;

    private RecyclerView recyclerView;
    private CardView cardView;
    private ArrayList<Product> mproductother = new ArrayList<Product>();

    private RecyclerView.Adapter mAdapter;

    Spinner sptype, spprice, spcompany;
    String kind[] = {"Điện thoại", "Laptop", "Other"};
    String price[] = {"5000000", "10000000", "Trên 10000000"};
    String company[] = {"Apple", " SamSung", "Other"};
    EditText txt_text;
    Button btn_button;

    ArrayAdapter<CharSequence> adapterkind;
    ArrayAdapter<CharSequence> adapterprice;
    ArrayAdapter<CharSequence> adaptercompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_advance);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        SearchAdvanceActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
        //-------------------------
        sptype = (Spinner) findViewById(R.id.advancesearch_spinnercatalog);
        spprice = (Spinner) findViewById(R.id.advancesearch_spinnerprice);
        spcompany = (Spinner) findViewById(R.id.advancesearch_spinnerproduce);
        txt_text = (EditText)findViewById(R.id.advancesearch_text);
        btn_button = (Button)findViewById(R.id.fragment_advancesearch_btnsearch);
        recyclerView = (RecyclerView)findViewById(R.id.search_my_recycler_view);
        addaction();
        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedtype  = sptype.getSelectedItem().toString();
                String selectedprice  = spprice.getSelectedItem().toString();
                String selectedcompany  = spcompany.getSelectedItem().toString();

//                mproductother.clear();
//                recyclerView.removeAllViews();

                initdata(txt_text.getText().toString().trim(), selectedtype, selectedprice, selectedcompany);
                initContrls();
            }
        });
    }
    public void initdata(String title, String type, String price,String company) {
        mproductother.clear();
        for (Product product : com.code.database.Var.mproduct) {
            if (product.getDetail().getCompany().equals(company)==true && product.getDetail().getTitle().contains(title) ){
                if(type.equals("Điện thoại")&&product.getCategory().getCategories_ID()==2){
                    if(price.equals("5000000")&&product.getDetail().getPrice()<5000000 ){
                        mproductother.add(product);
                    }else if(price.equals("10000000")&&product.getDetail().getPrice()<10000000 &&product.getDetail().getPrice()>5000000 ){
                        mproductother.add(product);
                    }else if(product.getDetail().getPrice()>10000000){
                        mproductother.add(product);
                    }
                }
                else if(type.equals("Laptop")&&product.getCategory().getCategories_ID()==1){
                    if(price.equals("5000000")&&product.getDetail().getPrice()<5000000 ){
                        mproductother.add(product);
                    }else if(price.equals("10000000")&&product.getDetail().getPrice()<10000000 &&product.getDetail().getPrice()>5000000 ){
                        mproductother.add(product);
                    }else if(product.getDetail().getPrice()>10000000){
                        mproductother.add(product);
                    }
                }
                else if(type.equals("Other")&&product.getCategory().getCategories_ID()==4){
                    if(price.equals("5000000")&&product.getDetail().getPrice()<5000000 ){
                        mproductother.add(product);
                    }else if(price.equals("10000000")&&product.getDetail().getPrice()<10000000 &&product.getDetail().getPrice()>5000000 ){
                        mproductother.add(product);
                    }else if(product.getDetail().getPrice()>10000000){
                        mproductother.add(product);
                    }
                }

            }


        }

    }

    public void addaction() {

        adapterkind = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, kind);
        adapterkind.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptype.setAdapter(adapterkind);

        adapterprice = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, price);
        adapterprice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprice.setAdapter(adapterprice);

        adaptercompany = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, company);
        adaptercompany.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcompany.setAdapter(adaptercompany);


    }
    private void initContrls() {



        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchAdvanceActivity.this));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new RowViewDataAdapter(mproductother);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SearchAdvanceActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Controller.getInstance().showProductDetailActivity(mproductother.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }
}
