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

public class RV_AdapterAllOrders extends RecyclerView.Adapter<RV_HolderAllOrders> {
    public List<ItemObject> itemList;

    public RV_AdapterAllOrders(List<ItemObject> itemLists) {
        itemList = itemLists;
    }

    public List<ItemObject> getItemList() {
        return itemList;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public RV_HolderAllOrders onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layoutView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_all_orders, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 10);
        layoutView.setLayoutParams(lp);
        return new RV_HolderAllOrders(layoutView);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull RV_HolderAllOrders holder, int position) {/*final int position*/
        try {
            holder.root.setOnLongClickListener(v -> false);
            holder.tv_total_price.setText("" + itemList.get(holder.getAbsoluteAdapterPosition()).getItem(0));
            holder.tv_tanggal_order.setText("" + itemList.get(holder.getAbsoluteAdapterPosition()).getItem(1));
            holder.btn_show_details.setText("Hide Details");
            List<String[]> list = itemList.get(holder.getAbsoluteAdapterPosition()).getListStringArr();
            for (int i = 0; i < list.size(); i++) {
                holder.ll_child.addView(new Utility().LinearLayoutChild(holder.root.getContext(), list.get(i)));
            }
            holder.btn_show_details.setOnClickListener(v -> {
                if (holder.ll_child.getVisibility() == View.VISIBLE) {
                    holder.btn_show_details.setText("Show Details");
                    holder.ll_child.setVisibility(View.GONE);
                } else {
                    holder.btn_show_details.setText("Hide Details");
                    holder.ll_child.setVisibility(View.VISIBLE);
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

class RV_HolderAllOrders extends RecyclerView.ViewHolder {
    LinearLayout root;
    TextView tv_total_price, tv_tanggal_order, btn_show_details;
    LinearLayout ll_child;

    RV_HolderAllOrders(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        tv_total_price = itemView.findViewById(R.id.tv_total_price);
        tv_tanggal_order = itemView.findViewById(R.id.tv_tanggal_order);
        btn_show_details = itemView.findViewById(R.id.btn_show_details);
        ll_child = itemView.findViewById(R.id.ll_child);
    }
}