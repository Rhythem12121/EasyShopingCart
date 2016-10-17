package com.videumcorp.android.tabmain;

/**
 * Created by dong on 10/1/2015.
 */
import android.os.Bundle;
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

public class fragment_main_laptop extends Fragment {
    private RecyclerView recyclerView;
    private CardView cardView;
    private ArrayList<Product> mproductlaptop = new ArrayList<Product>();

    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_laptop, container, false);
        initdata();
        initContrls(v);
        return v;
    }
    public void initdata(){
        for (Product product : Var.mproduct){
            if (product.getCategory().getCategories_ID()==1)
                mproductlaptop.add(product);

        }
        Log.v("laptop",mproductlaptop.size()+"");

    }
    private void initContrls(View v) {

        recyclerView = (RecyclerView) v.findViewById(R.id.fragment_laptop_my_recycler_view);

        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new RowViewDataAdapter(mproductlaptop);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Controller.getInstance().showProductDetailActivity(mproductlaptop.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }
}
