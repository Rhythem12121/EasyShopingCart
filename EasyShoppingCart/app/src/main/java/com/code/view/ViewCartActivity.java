package com.code.view;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.code.adapter.MyAdapter;
import com.code.controller.Controller;
import com.code.database.Var;
import com.code.util.RecyclerItemClickListener;
import com.videumcorp.android.tabmain.R;

import java.util.ArrayList;

public class ViewCartActivity extends ActionBarActivity {
    Toolbar toolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<String> myDataset = new ArrayList<String>();

    public void init() {
        Log.e("size", Var.productid_cart.size() + "");
        for (int i = 0; i < Var.productid_cart.size(); i++)
            myDataset.add(Var.mproduct.get(Integer.parseInt(Var.productid_cart.get(i))).getDetail().getTitle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        init();
        Controller.setActivitySave(ViewCartActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        ViewCartActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }

        //
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Button fab = (Button) findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.getInstance().showOrderActivity();
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // specify an adapter
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ViewCartActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                //Toast.makeText(ViewCartActivity.this, "toast action", Toast.LENGTH_SHORT).show();
                ImageButton btndelete = (ImageButton) v.findViewById(R.id.row_cart_delete);
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Var.productid_cart.remove(position);
                                        Toast.makeText(v.getContext(), "Sản phẩm đã được xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();

                                        myDataset.remove(position);
                                        mRecyclerView.removeViewAt(position);
                                        mAdapter.notifyItemRemoved(position);
                                        mAdapter.notifyItemRangeChanged(position, myDataset.size());

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Bạn muốn xóa sản phẩm khỏi giỏ hàng?").setPositiveButton("Có", dialogClickListener)
                                .setNegativeButton("Không", dialogClickListener).show();


                    }
                });


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


        //--------------------------------


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
