package com.onlineshop;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class DetailProductsActivity extends Dialog {
    public DetailProductsActivity(@NonNull Context context, Drawable tv_background, String[] data) {
        super(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        //String nama harga deskripsi
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_products);
        TextView btn_back, tv_title, tv_image, tv_description, tv_add_to_chart, tv_price;

        btn_back = findViewById(R.id.btn_back);
        tv_title = findViewById(R.id.tv_title);
        tv_image = findViewById(R.id.tv_image);
        tv_description = findViewById(R.id.tv_description);

        tv_add_to_chart = findViewById(R.id.tv_add_to_chart);
        tv_price = findViewById(R.id.tv_price);
        tv_image.setBackground(tv_background);
        tv_title.setText(data[0]);
        tv_price.setText(data[1]);
        tv_description.setText(data[2]);
        tv_add_to_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }
}