package com.code.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

/**
 * Created by dong on 11/11/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    protected ArrayList<String> mDataset;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageButton btndelete;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.rowcart_title);

//            btndelete = (ImageButton) v.findViewById(R.id.row_cart_delete);
//            btndelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View v) {
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    int position = getPosition();
//                                    Var.productid_cart.remove(position);
//                                    Toast.makeText(v.getContext(), "Sản phẩm đã được xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
//
//                                    break;
//
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    //No button clicked
//                                    break;
//                            }
//                        }
//                    };
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                    builder.setMessage("Bạn muốn xóa sản phẩm khỏi giỏ hàng?").setPositiveButton("Có", dialogClickListener)
//                            .setNegativeButton("Không", dialogClickListener).show();
//
//
//                }
//            });
        }

    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowcart_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
