package com.code.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.model.Product;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong on 10/20/2015.
 */
public class RecyclerViewRecommendFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Product mproduct;
    private static final int ITEM_COUNT = 1;

    private List<String> mContentItems = new ArrayList<>();

    public static RecyclerViewRecommendFragment newInstance(Product product) {
        return new RecyclerViewRecommendFragment(product);
    }

    public RecyclerViewRecommendFragment() {
    }

    public RecyclerViewRecommendFragment(Product product) {
        this.mproduct = product;

    }

    //    public RecyclerViewFragment(Product product){
//
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewRecommend);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewRecommendAdapter(mContentItems));
        mRecyclerView.setAdapter(mAdapter);

        {

            mContentItems.add(mproduct.getDetail().getComment());

            mAdapter.notifyDataSetChanged();
        }

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);
    }
}
