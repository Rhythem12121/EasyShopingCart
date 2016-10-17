package com.code.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.videumcorp.android.tabmain.R;

import java.util.List;

/**
 * Created by dong on 10/3/2015.
 */
public class TestRecyclerViewRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> contents;

    static final int TYPE_HEADER = 0;

    public TestRecyclerViewRecommendAdapter(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_HEADER;

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_comment, parent, false);
                TextView t = (TextView) view.findViewById(R.id.activity_product_detail_comment_top);
                t.setText(contents.get(0));
                return new RecyclerView.ViewHolder(view) {
                };
            }

        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:

                break;

        }
    }
}