package com.code.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.adapter.ListviewAdapter;
import com.code.database.Var;
import com.code.model.Product;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong on 10/3/2015.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_DESCRIPTION = 1;
    static final int TYPE_INFOMATION = 2;
    static final int TYPE_RECOMMEND = 3;

    public TestRecyclerViewAdapter(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            case 1:
                return TYPE_DESCRIPTION;
            case 2:
                return TYPE_INFOMATION;
            case 3:
                return TYPE_RECOMMEND;
            default:
                return TYPE_INFOMATION;
        }
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
                        .inflate(R.layout.list_item_card_price, parent, false);
                TextView t = (TextView) view.findViewById(R.id.row_card_price);
                t.setText(contents.get(0));
                ImageButton btn_buy = (ImageButton) view.findViewById(R.id.row_card_buying);
                btn_buy.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View view) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Yes button clicked
                                        //Var.productid_cart.add("" + contents.get(3));
                                        //Toast.makeText(view.getContext(),"Sản phẩm đã được thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };
                        final EditText input = new EditText(view.getContext());
                        input.setHint("nhập số lượng cần mua");
                        input.setInputType(InputType.TYPE_CLASS_NUMBER);

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("Bạn muốn thêm sản phẩm vào giỏ hàng?").setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String result = input.getText().toString();
                                int iresult = Integer.parseInt(result);
                                if (iresult > 20) {
                                    showDialogWarning(view);
                                } else {
                                    Var.productid_cart.add("" + contents.get(3));
                                    Toast.makeText(view.getContext(), "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).setNegativeButton("Không", dialogClickListener)
                                .setView(input).show();

                    }
                });
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_DESCRIPTION: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_decription, parent, false);
                TextView t = (TextView) view.findViewById(R.id.activity_product_detail_description_top);
                t.setText(contents.get(1));
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_INFOMATION: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_infomation, parent, false);
                TextView t = (TextView) view.findViewById(R.id.activity_product_detail_infomation);
                t.setText(contents.get(2));
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_RECOMMEND: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_recommend, parent, false);
                initdata();
                initContrls(view);

                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }

    public void showDialogWarning(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Bạn nhập quá số lượng sản phẩm, vui lòng nhập lại?").setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();

    }

    // tab 3
    private ListView recyclerView;
    private CardView cardView;
    private ArrayList<Product> mproductother = new ArrayList<Product>();

    private ListviewAdapter mAdapter;

    public void initdata() {
        for (String id : Var.productid_recommend)
            for (Product product : Var.mproduct) {
                if (product.getProduct_ID() == Integer.parseInt(id))
                    mproductother.add(product);

            }

    }
    private void initContrls(View v) {
        System.out.println("check log recycle view" + mproductother.size());

        recyclerView = (ListView) v.findViewById(R.id.activity_product_detail_recommend_my_recycler_view);

        // recyclerView.setHasFixedSize(true);

        // ListView
        //  recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        //Grid View
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2,1,false));

        //StaggeredGridView
        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        // create an Object for Adapter
        mAdapter = new ListviewAdapter(v.getContext(), mproductother);

        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(v.getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Controller.getInstance().showProductDetailActivity(mproductother.get(position));
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        }));

    }
    //-----------------------------------------------------------------

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:

                break;
            case TYPE_DESCRIPTION:

                break;
            case TYPE_INFOMATION:
                break;
            case TYPE_RECOMMEND:
                break;
        }
    }
}