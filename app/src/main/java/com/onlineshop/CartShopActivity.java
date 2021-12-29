package com.onlineshop;

import static com.onlineshop.MainActivity.fragmentManager;
import static com.onlineshop.ProductsFragment.rv_adapterAllProd;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onlineshop.Components.ItemObject;
import com.onlineshop.Components.RV_AdapterCartShop;

import java.util.ArrayList;
import java.util.List;

public class CartShopActivity extends Dialog {
    List<ItemObject> rowListItem1;
    List<ItemObject> rowListItem2;
    RV_AdapterCartShop rv_adapterCartShop;
    TextView btn_order_now, btn_back;
    LinearLayoutManager lLayout;
    RecyclerView recyclerView;

    //requestWindowFeature(Window.FEATURE_NO_TITLE);
    @SuppressLint("NotifyDataSetChanged")
    public CartShopActivity(@NonNull Context context) {
        super(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        //String nama harga deskripsi
        setContentView(R.layout.activity_cart_shop);
        btn_order_now = findViewById(R.id.btn_order_now);
        btn_back = findViewById(R.id.btn_back);

        rowListItem1 = new ArrayList<>();
        rowListItem2 = new ArrayList<>();
        rv_adapterCartShop = new RV_AdapterCartShop(rowListItem1);
        lLayout = new LinearLayoutManager(getContext());
        lLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(rv_adapterCartShop);
        btn_back.setOnClickListener(v -> {
            dismiss();
        });
        for (int i = 0; i < rv_adapterAllProd.getItemCount(); i++) {
            System.out.println(rv_adapterAllProd.getItemList().get(i).getItem(4) + " " + rv_adapterAllProd.getItemList().get(i).getItem(1));
            if (!rv_adapterAllProd.getItemList().get(i).getItem(4).matches("0")) {
                //qty, nama, harga, price
                rowListItem1.add(new ItemObject(new String[]{
                        rv_adapterAllProd.getItemList().get(i).getItem(4),
                        rv_adapterAllProd.getItemList().get(i).getItem(1),
                        rv_adapterAllProd.getItemList().get(i).getItem(5),
                        rv_adapterAllProd.getItemList().get(i).getItem(2)}));
                rowListItem2.add(new ItemObject(new String[]{
                        rv_adapterAllProd.getItemList().get(i).getItem(4),
                        rv_adapterAllProd.getItemList().get(i).getItem(1),
                        rv_adapterAllProd.getItemList().get(i).getItem(5),
                        rv_adapterAllProd.getItemList().get(i).getItem(2)}));
            }
        }
        btn_order_now.setOnClickListener(v -> {
            try {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new OrdersFragment(rowListItem2)).commit();
                //recyclerView.removeAllViews();
                Thread.sleep(1000);
                rowListItem1.clear();
                rv_adapterCartShop.notifyDataSetChanged();
                Thread.sleep(1000);
                dismiss();
            } catch (Exception e) {
            }
        });

        show();
    }
}