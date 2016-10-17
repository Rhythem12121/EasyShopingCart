package com.code.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.database.Var;
import com.code.model.Product;
import com.code.util.ImageDownloaderTask;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

/**
 * Created by dong on 11/12/2015.
 */
public class ListviewAdapter extends BaseAdapter {
    private ArrayList<Product> dataSet;
    private Context mContext;

    public ListviewAdapter(Context context, ArrayList<Product> listData) {
        this.dataSet = listData;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_card, parent,
                    false);
            holder = new ViewHolder();
            holder.tvtitle = (TextView) convertView.findViewById(R.id.row_card_Title);
            holder.tvdes = (TextView) convertView.findViewById(R.id.row_card_description);
            holder.tvprice = (TextView) convertView.findViewById(R.id.row_card_price);
            holder.tvstatus = (TextView) convertView.findViewById(R.id.row_card_status);
            holder.imgavatar = (ImageView) convertView
                    .findViewById(R.id.row_card_iconId);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product fp = dataSet.get(position);

        String t = fp.getDetail().getTitle();
        if (t.length() > 20) t = t.substring(0, 18) + "..";
        String des = fp.getDetail().getDescription();
        if (des.length() > 100) des = des.substring(0, 98) + "..";
        holder.tvtitle.setText(t);
        holder.tvdes.setText(des);
        holder.tvprice.setText(String.format("%.0f", fp.getDetail().getPrice()) + "");
        holder.tvstatus.setText((fp.getQuanlity() > 0 ? "Còn hàng" : "Hết hàng"));
        // viewHolder.iconView.setImageResource(fp.getThumbnail());
        // viewHolder.feed = fp;
        String linkimage = fp.getDetail().getLink_image();
        if (Var.mapImage.containsKey(linkimage)) {
            holder.imgavatar.setImageResource(Var.mapImage.get(linkimage));
        } else
            new ImageDownloaderTask(holder.imgavatar).execute(fp.getDetail().getLink_image());
        //------------------------

        return convertView;
    }

    static class ViewHolder {
        public TextView tvtitle;
        public TextView tvdes;
        public TextView tvprice;
        public TextView tvstatus;
        public ImageView imgavatar;
        public Product feed;
    }
}
