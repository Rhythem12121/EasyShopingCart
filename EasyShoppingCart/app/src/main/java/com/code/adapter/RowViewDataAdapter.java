package com.code.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.database.Var;
import com.code.model.Product;
import com.code.util.ImageDownloaderTask;
import com.code.view.ViewDetailProductActivity;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

/**
 * Created by dong on 10/4/2015.
 */
public class RowViewDataAdapter extends RecyclerView.Adapter<RowViewDataAdapter.ViewHolder> {

    private ArrayList<Product> dataSet;

    public RowViewDataAdapter(ArrayList<Product> os_versions) {

        this.dataSet = os_versions;
    }

    @Override
    public RowViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_card, viewGroup, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Product fp = dataSet.get(i);
        String t = fp.getDetail().getTitle();
        if (t.length() > 20) t = t.substring(0, 18) + "..";
        String des = fp.getDetail().getDescription();
        if (des.length() > 100) des = des.substring(0, 98) + "..";
        viewHolder.tvtitle.setText(t);
        viewHolder.tvdes.setText(des);
        viewHolder.tvprice.setText(String.format("%.0f", fp.getDetail().getPrice()) + "");
        viewHolder.tvstatus.setText((fp.getQuanlity() > 0 ? "Còn hàng" : "Hết hàng"));
        // viewHolder.iconView.setImageResource(fp.getThumbnail());
        // viewHolder.feed = fp;
        String linkimage = fp.getDetail().getLink_image();
        if (Var.mapImage.containsKey(linkimage)) {
            viewHolder.imgavatar.setImageResource(Var.mapImage.get(linkimage));
        } else
            new ImageDownloaderTask(viewHolder.imgavatar).execute(fp.getDetail().getLink_image());
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }

    // inner class to hold a reference to each item of RecyclerView


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtitle;
        public TextView tvdes;
        public TextView tvprice;
        public TextView tvstatus;
        public ImageView imgavatar;
        public Product feed;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvtitle = (TextView) itemLayoutView.findViewById(R.id.row_card_Title);
            tvdes = (TextView) itemLayoutView.findViewById(R.id.row_card_description);
            tvprice = (TextView) itemLayoutView.findViewById(R.id.row_card_price);
            tvstatus = (TextView) itemLayoutView.findViewById(R.id.row_card_status);
            imgavatar = (ImageView) itemLayoutView
                    .findViewById(R.id.row_card_iconId);

            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ViewDetailProductActivity.class);
                    v.getContext().startActivity(intent);
                    //Toast.makeText(v.getContext(), "os version is: " + feed.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}