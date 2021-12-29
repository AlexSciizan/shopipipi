package com.onlineshop.Components;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onlineshop.R;

import java.util.List;

public class RV_AdapterCartShop extends RecyclerView.Adapter<RecyclerViewHolderPro> {
    List<ItemObject> itemList;

    public RV_AdapterCartShop() {
    }

    public RV_AdapterCartShop(List<ItemObject> itemLists) {
        itemList = itemLists;
    }

    public List<ItemObject> getItemList() {
        return itemList;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerViewHolderPro onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layoutView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_cart_shop, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        layoutView.setLayoutParams(lp);
        return new RecyclerViewHolderPro(layoutView);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolderPro holder, int position) {/*final int position*/
        try {
// itemList.get(holder.getAbsoluteAdapterPosition()).getItem(0)
// itemList.get(holder.getAbsoluteAdapterPosition()).getItem(1)
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.tv_qty.setText(itemList.get(holder.getAbsoluteAdapterPosition()).getItem(0));
            holder.tv_product_name.setText(itemList.get(holder.getAbsoluteAdapterPosition()).getItem(1));
            holder.tv_price.setText(itemList.get(holder.getAbsoluteAdapterPosition()).getItem(2));
            holder.harga = itemList.get(holder.getAbsoluteAdapterPosition()).getItem(3);
            holder.tv_action.setOnClickListener(v -> {
                int qty = Integer.parseInt("" + holder.tv_qty.getText());
                qty -= 1;
                holder.tv_qty.setText("" + qty);
                holder.tv_price.setText("" + qty * Float.parseFloat("" + holder.harga));
                if (qty <= 0) {
                    try {
                        itemList.remove(holder.getAbsoluteAdapterPosition());
                        notifyDataSetChanged();
                    } catch (IndexOutOfBoundsException e) {
                        itemList.remove(0);
                    }
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

class RecyclerViewHolderPro extends RecyclerView.ViewHolder {
    LinearLayout root;

    TextView tv_qty, tv_product_name, tv_price, tv_action;
    String harga = "";

    RecyclerViewHolderPro(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        tv_qty = itemView.findViewById(R.id.list_item_qty);
        tv_product_name = itemView.findViewById(R.id.list_item_product_name);
        tv_price = itemView.findViewById(R.id.list_item_price);
        tv_action = itemView.findViewById(R.id.list_item_action);
    }
}
