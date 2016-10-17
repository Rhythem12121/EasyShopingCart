package com.code.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.controller.Controller;
import com.code.database.Var;
import com.code.model.Article;
import com.code.model.Product;
import com.code.util.ImageDownloaderTask;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

/**
 * Created by dong on 11/12/2015.
 */
public class BroadrowitemAdapter extends RecyclerView.Adapter<BroadrowitemAdapter.ViewHolder> {
    private ArrayList<Article> dataSet;

    public BroadrowitemAdapter(ArrayList<Article> os_versions) {

        this.dataSet = os_versions;
    }

    @Override
    public BroadrowitemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.broad_row_card, viewGroup, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Article fp = dataSet.get(i);
        String t = fp.getTitle();
        if (t.length() > 20) t = t.substring(0, 18) + "..";
        String des = fp.getDescription();
        if (des.length() > 60) des = des.substring(0, 58) + "..";
        viewHolder.tvtitle.setText(t);
        viewHolder.tvdes.setText(des);
        final String phone_number = fp.getPhone_number();
        viewHolder.tvphonenumber.setText("SDT: " + phone_number);
        // viewHolder.tvstatus.setText((fp.getQuanlity() > 0 ? "Còn hàng" : "Hết hàng"));
        // viewHolder.iconView.setImageResource(fp.getThumbnail());
        // viewHolder.feed = fp;


        String linkimage = fp.getLink_image();
        if (Var.mapImage.containsKey(linkimage)) {
            viewHolder.imgavatar.setImageResource(Var.mapImage.get(linkimage));
        } else
            new ImageDownloaderTask(viewHolder.imgavatar).execute(fp.getLink_image());

        //--------------------------
        viewHolder.tvcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_number));
                Controller.getActivitySave().startActivity(callIntent);
            }
        });
        viewHolder.tvmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.getActivitySave().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + phone_number)));
            }
        });
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
        public TextView tvphonenumber;
        public TextView tvcall;
        public TextView tvmessage;
        public ImageView imgavatar;
        public Product feed;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvtitle = (TextView) itemLayoutView.findViewById(R.id.broad_row_card_title);
            tvdes = (TextView) itemLayoutView.findViewById(R.id.broad_row_card_description);
            tvphonenumber = (TextView) itemLayoutView.findViewById(R.id.broad_row_card_phone);
            tvcall = (TextView) itemLayoutView.findViewById(R.id.broad_row_card_call);
            tvmessage = (TextView) itemLayoutView.findViewById(R.id.broad_row_card_message);
            imgavatar = (ImageView) itemLayoutView
                    .findViewById(R.id.broad_row_card_image);

//            itemLayoutView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(v.getContext(), ViewDetailProductActivity.class);
//                    v.getContext().startActivity(intent);
//                    //Toast.makeText(v.getContext(), "os version is: " + feed.getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

}
