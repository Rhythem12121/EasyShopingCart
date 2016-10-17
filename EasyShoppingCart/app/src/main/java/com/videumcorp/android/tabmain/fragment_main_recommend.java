package com.videumcorp.android.tabmain;

/**
 * Created by dong on 10/1/2015.
 */

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.adapter.RowViewDataAdapter;
import com.code.controller.Controller;
import com.code.database.Var;
import com.code.model.Product;
import com.code.util.RecyclerItemClickListener;

import java.util.ArrayList;

public class fragment_main_recommend extends Fragment {

    private RecyclerView recyclerView;
    private CardView cardView;
    private ArrayList<Product> mproductrecommend = new ArrayList<Product>();

    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_recommend, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        String txt = GetUrlTxt.gettxtFromUrlHtpp(Var.ip + ":8080/Service_Tuvan/tuvan.jsp?id=" + Var.curCustomer.getCumtomer_ID());
        //   String txt = GetUrlTxt.gettxtFromUrlHtpp(Var.ip + ":8080/Service_Tuvan/tuvan.jsp?id=6");
        if(txt==null){

        }else
        if (txt.length() == 0) {
            for (int i = 1; i <= 3; i++) {
                mproductrecommend.add(Var.mproduct.get(Var.mproduct.size() - i));
            }
        } else {
            String[] items = txt.split(" ");
            for (int i = 0; i < items.length; i++)
                System.out.println("-" + items[i] + "-");

            initdata(items);
        }
        initContrls(v);
        return v;
    }

    public void initdata(String[] items) {
        for (int i = 0; i < items.length; i++) {

            if (items[i].equals("") == true) continue;
            int id = 0;
            try {
                id = Integer.parseInt(items[i]);
            } catch (Exception ex) {

            }
            if (id > 0) {
                for (Product product : Var.mproduct) {
                    if (product.getProduct_ID() == id){
                        Var.productid_recommend.add(id+"");
                        mproductrecommend.add(product);
                    }


                }

            }

        }


    }

    private void initContrls(View v) {

        recyclerView = (RecyclerView) v.findViewById(R.id.fragment_recommend_my_recycler_view);

        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        //  if(mproductsmartphone)
        // create an Object for Adapter
        mAdapter = new RowViewDataAdapter(mproductrecommend);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("errorcheck",mproductrecommend.size()+"");
                Controller.getInstance().showProductDetailActivity(mproductrecommend.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

}
