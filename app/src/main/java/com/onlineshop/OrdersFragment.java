package com.onlineshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.onlineshop.Components.Api;
import com.onlineshop.Components.ItemObject;
import com.onlineshop.Components.RV_AdapterAllOrders;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    View view;
    List<ItemObject> rowListItem = new ArrayList<>();
    RV_AdapterAllOrders rv_adapterAllOrders;
    LinearLayoutManager lLayout;
    RecyclerView recyclerView;

    public OrdersFragment() {
    }

    public OrdersFragment(List<ItemObject> rowListItemCart) {
        rowListItem = rowListItemCart;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        rv_adapterAllOrders = new RV_AdapterAllOrders(rowListItem);
        lLayout = new LinearLayoutManager(getActivity());
        lLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(rv_adapterAllOrders);
        JsonArray ja = Api.getAllOrder(getActivity());
        List<String[]> row = new ArrayList<>();
        for (int i = 0; i < ja.size(); i++) {

            for (int j = 0; j < ja.get(i).getAsJsonObject().get("items").getAsJsonArray().size(); j++) {
                row.add(new String[]{
                        "" + ja.get(i).getAsJsonObject().get("items").getAsJsonArray().get(j).getAsJsonObject().get("quantity"),
                        "" + ja.get(i).getAsJsonObject().get("items").getAsJsonArray().get(j).getAsJsonObject().get("product").getAsJsonObject().get("title"),
                        "" + ja.get(i).getAsJsonObject().get("items").getAsJsonArray().get(j).getAsJsonObject().get("sum")
                });
            }
            rowListItem.add(new ItemObject(new String[]{"" + ja.get(i).getAsJsonObject().get("totalAmount"),
                    "" + ja.get(i).getAsJsonObject().get("date")}, row));
        }
// rowListItem.add(new ItemObject(new String[]{"$ 2315.98", "8th December 2021, 09.24"}, new String[]{"32", "Sporty Shoes", "$ 500"}));
        return view;
    }
//  JsonArray ja = Api.getAllProducts(getActivity());
//  for (int i = 0; i < ja.size(); i++) {
//  rowListItem.add(new ItemObject(new String[]{
//  ja.get(i).getAsJsonObject().get("imageUrl").getAsString(),
//  ja.get(i).getAsJsonObject().get("title").getAsString(),
//  ja.get(i).getAsJsonObject().get("price").getAsString()}));
//  }
// "imageUrl": "https://images.pexels.com/photos/6292/blue-pattern-texture-macro.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
// "title"
// "price": 99.99,
// "ownerId"
// "description"
// "id"
}