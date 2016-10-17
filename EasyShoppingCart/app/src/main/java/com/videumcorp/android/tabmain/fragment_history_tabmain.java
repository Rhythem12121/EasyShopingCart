package com.videumcorp.android.tabmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.adapter.RowViewDataAdapter;
import com.code.controller.Controller;
import com.code.model.Product;
import com.code.util.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by dong on 12/5/2015.
 */
public class fragment_history_tabmain extends
        Fragment implements ActionBar.OnNavigationListener {
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private CardView cardView;
    private ArrayList<Product> mproductother = new ArrayList<Product>();

    private RecyclerView.Adapter mAdapter;

    public fragment_history_tabmain() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container,
                false);
        //-----------------------

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        initdata();
        initContrls(view);

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(int arg0, long arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    //-----------------------------------------------
    public void initdata() {
//        for (Product product : Var.mproduct) {
//            if (product.getCategory().getCategories_ID() == 4 || product.getCategory().getCategories_ID() == 3)
//                mproductother.add(product);
//
//        }
        for (String id : com.code.database.Var.productid_history) {
            mproductother.add(com.code.database.Var.mproduct.get(Integer.parseInt(id)));
        }
    }

    private void initContrls(View v) {

        recyclerView = (RecyclerView) v.findViewById(R.id.history_my_recycler_view);

        recyclerView.setHasFixedSize(true);

        // ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new RowViewDataAdapter(mproductother);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
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