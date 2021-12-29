package com.onlineshop.Components;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.onlineshop.R;

public class Utility {
    public static GradientDrawable roundRecWhite(int rad, String strokeColor, String feelColor) {
// return roundRect(Color.rgb(255, 255, 255), rad, Color.parseColor("#000000"));
        return roundRect(rad, Color.parseColor(strokeColor), Color.parseColor(feelColor));
    }

    public static GradientDrawable roundRect(int rad, int strokeColor, int feelColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(feelColor);
        gd.setCornerRadius(rad);
        gd.setStroke(1, strokeColor);
        return gd;
    }

    @SuppressLint("CommitPrefEdits")
    public static void setProducts(Context context, int no_urut, String[] arr) {
        SharedPreferences sf = context.getSharedPreferences("list_poducts_" + no_urut, 0);
        //ownerId, title, imageUrl, description, price, id
        sf.edit().putString("ownerId", arr[0]);
        sf.edit().putString("title", arr[1]);
        sf.edit().putString("imageUrl", arr[2]);
        sf.edit().putString("description", arr[3]);
        sf.edit().putString("price", arr[4]);
        sf.edit().putString("id", arr[5]);
        sf.edit().apply();
    }

    public static String[] getProducts(Context context, int no_urut) {
        SharedPreferences sf = context.getSharedPreferences("list_poducts", 0);
        //ownerId, title, imageUrl, description, price, id
        return new String[]{sf.getString("ownerId", ""),
                sf.getString("title", ""),
                sf.getString("imageUrl", ""),
                sf.getString("description", ""),
                sf.getString("price", ""),
                sf.getString("id", "")};
    }

    public static void delProducts(Context context, int no_urut) {
        SharedPreferences sf = context.getSharedPreferences("list_products_" + no_urut, Context.MODE_PRIVATE);
        sf.edit().clear().apply();
        sf.edit().clear().commit();
    }

    public LinearLayout LinearLayoutChild(Context context, String[] arg) {
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        LinearLayout ll_main = (LinearLayout) mLayoutInflater.inflate(R.layout.cell_list_cart_shop, null);
        ((TextView) ll_main.findViewById(R.id.list_item_qty)).setText(arg[0]);
        ((TextView) ll_main.findViewById(R.id.list_item_product_name)).setText(arg[1]);
        ((TextView) ll_main.findViewById(R.id.list_item_price)).setText(arg[2]);
// ll_main.removeViewAt(3);
        ll_main.findViewById(R.id.list_item_action).setVisibility(View.GONE);
        return ll_main;
    }

    public static Dialog DialogLoading(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_loading);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.biru), android.graphics.PorterDuff.Mode.SRC_ATOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.x = 0;
        lp.y = 0;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    public static int getMyHeight(Context context, int percent) {
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        return (percent * height) / 100;
    }

    public static int getMyWidth(Context context, int percent) {
        DisplayMetrics dm = new DisplayMetrics();
        ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return (percent * width) / 100;
    }

    public static boolean cekKoneksi(Context context, boolean show_snackbar, boolean show_toast) {
        boolean konek = false; /*TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);*/
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE); /*NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);*/ /*NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);*/ /*if (wifiNetwork != null && wifiNetwork.isConnected()){*/ /*konek = true;*/ /*Logd("koneksi ", "cekKoneksi wifi " + konek);*/ /*} else if (mobileNetwork != null && mobileNetwork.isConnected()){*//*konek = true;Logd("koneksi ", "cekKoneksi hp " + konek);}NetworkInfo activeNetwork = cm.getActiveNetworkInfo();if (activeNetwork != null && activeNetwork.isConnected()){konek = true;Logd("koneksi ", "cekKoneksi jaringan aktif " + konek);}*/
        boolean isWifiConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean is3gConnected = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        if ((isWifiConnected || is3gConnected) && activeNetworkInfo != null && activeNetworkInfo.isConnected())
            konek = true;
        else if (show_snackbar) {
            noInternetSnackbar(context).show();
            konek = false;
        } else if (show_toast) {
            Toast.makeText(context, "Anda belum terhubung dengan Internet", Toast.LENGTH_SHORT).show();
            konek = false;
        }
        return konek;
    }

    static Snackbar noInternetSnackbar(Context context) {
        View my_view = ((AppCompatActivity) context).findViewById(android.R.id.content);
        final Snackbar snackbar = Snackbar.make(my_view, "No Connection Internet", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("X", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        return snackbar;
    }

    public static void setToolBar(Toolbar toolBar, AppCompatActivity appCompatActivity, boolean up, boolean home, boolean title) {
        appCompatActivity.setSupportActionBar(toolBar);
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setHomeButtonEnabled(home);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(up);
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(title);
        }
    }
}
