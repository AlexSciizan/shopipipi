package com.onlineshop.Components;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.onlineshop.R;

import java.util.List;

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

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    public static Dialog DialogAdd(Context context,
                                   String kode,
                                   String nama,
                                   String stok,
                                   String harga,
                                   String lokasi_di_store,
                                   String jenisLayout,
                                   List<ItemObject> rowListItemCart,
                                   int lokasi_di_cart,
                                   RV_AdapterAllProd adapterCart) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add);
        TextView tv_nama_barang, btn_min, tv_jml_barang, btn_plus, tv_harga_barang, btn_ok;
        tv_nama_barang = dialog.findViewById(R.id.tv_nama_barang);
        btn_min = dialog.findViewById(R.id.btn_min);
        tv_jml_barang = dialog.findViewById(R.id.tv_jml_barang);
        btn_plus = dialog.findViewById(R.id.btn_plus);
        tv_harga_barang = dialog.findViewById(R.id.tv_harga_barang);
        btn_ok = dialog.findViewById(R.id.btn_ok);
        tv_nama_barang.setText(nama);
        tv_jml_barang.setText(stok);//angka
        tv_harga_barang.setText(harga);//angka
        btn_plus.setOnClickListener(v -> {
            tv_jml_barang.setTag(Integer.parseInt("" + tv_jml_barang.getText()) + 1);
            tv_jml_barang.setText("" + tv_jml_barang.getTag());
            tv_harga_barang.setText("" + (
                    Integer.parseInt("" + tv_jml_barang.getTag()) *
                            Integer.parseInt(harga.replaceAll("\\.", ""))
            ));
            tv_harga_barang.setText("" + tv_harga_barang.getText().toString()
                    .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)", "$1.$2$3$4.$5$6$7")
                    .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)", "$1$2$3.$4$5$6")
                    .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)", "$1$2.$3$4$5"));
        });
        btn_min.setOnClickListener(v -> {
                    if (Integer.parseInt("" + tv_jml_barang.getText()) > 1) {
                        tv_jml_barang.setTag(Integer.parseInt("" + tv_jml_barang.getText()) - 1);
                        tv_jml_barang.setText("" + tv_jml_barang.getTag());
                        tv_harga_barang.setText("" +
                                (Integer.parseInt("" + tv_jml_barang.getTag()) *
                                        Integer.parseInt("" + harga.replaceAll("\\.", "")))
                        );
                        tv_harga_barang.setText("" + tv_harga_barang.getText().toString()
                                .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)", "$1.$2$3$4.$5$6$7")
                                .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)", "$1$2$3.$4$5$6")
                                .replaceAll("(\\d)(\\d)(\\d)(\\d)(\\d)", "$1$2.$3$4$5"));
                    }
                }
        );
        btn_ok.setOnClickListener(v -> {
            rowListItemCart.set(lokasi_di_cart,
                    new ItemObject(new String[]{
                            kode,
                            nama,
                            "" + tv_jml_barang.getText(),
                            "" + tv_harga_barang.getText(),
                            lokasi_di_store,
                            jenisLayout
                    }));
            adapterCart.notifyDataSetChanged();
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
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
