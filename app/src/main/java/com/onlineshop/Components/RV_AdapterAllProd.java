package com.onlineshop.Components;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.ion.Ion;
import com.onlineshop.R;

import java.util.List;

public class RV_AdapterAllProd extends RecyclerView.Adapter<RV_HolderAllProd> {
    public List<ItemObject> itemList;

    public RV_AdapterAllProd(List<ItemObject> itemLists) {
        itemList = itemLists;
    }

    public List<ItemObject> getItemList() {
        return itemList;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public RV_HolderAllProd onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layoutView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_all_prod, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        layoutView.setLayoutParams(lp);
        return new RV_HolderAllProd(layoutView);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull RV_HolderAllProd holder, int position) {/*final int position*/
        try {
            holder.root.setOnLongClickListener(v -> {
// itemList.remove(holder.getAbsoluteAdapterPosition());
// notifyDataSetChanged();
// Toast.makeText(v.getContext(), "Berhasil menghapus " + holder.tv_nama.getText(), Toast.LENGTH_SHORT).show();
                return false;
            });
            Resources res = holder.root.getResources();
            try {
                Bitmap bitmap = Ion.with(holder.root.getContext()).load("GET", itemList.get(holder.getAbsoluteAdapterPosition()).getItem(0)).asBitmap().get();
                RoundedBitmapDrawable roundBMP = RoundedBitmapDrawableFactory.create(res, bitmap);
                roundBMP.setCornerRadius(25f);
                holder.tv_image.setBackground(roundBMP);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            holder.tv_nama_barang.setText(itemList.get(holder.getAbsoluteAdapterPosition()).getItem(1));
            holder.tv_harga_barang.setText(itemList.get(holder.getAbsoluteAdapterPosition()).getItem(2));
            holder.btn_view_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.btn_to_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return itemList.size();
        } catch (NullPointerException ignored) {
        }
        return 0;
    }
}

class RV_HolderAllProd extends RecyclerView.ViewHolder {
    LinearLayout root;
    TextView tv_image, tv_nama_barang, tv_harga_barang, btn_view_detail, btn_to_cart;

    RV_HolderAllProd(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        tv_image = itemView.findViewById(R.id.tv_image);
        tv_nama_barang = itemView.findViewById(R.id.tv_nama_barang);
        tv_harga_barang = itemView.findViewById(R.id.tv_harga_barang);
        btn_view_detail = itemView.findViewById(R.id.btn_view_detail);
        btn_to_cart = itemView.findViewById(R.id.btn_to_cart);
    }
}