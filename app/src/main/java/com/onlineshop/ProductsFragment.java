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
import com.onlineshop.Components.RV_AdapterAllProd;

import java.util.ArrayList;
import java.util.List;
public class ProductsFragment extends Fragment {
    List<ItemObject> rowListItem = new ArrayList<>();
    public static RV_AdapterAllProd rv_adapterAllProd;
    View view;

    public ProductsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_products, container, false);
        JsonArray ja = Api.getAllProducts(getActivity());
        for (int i = 0; i < ja.size(); i++) {
            rowListItem.add(new ItemObject(new String[]{
                    ja.get(i).getAsJsonObject().get("imageUrl").getAsString(),
                    ja.get(i).getAsJsonObject().get("title").getAsString(),
                    ja.get(i).getAsJsonObject().get("price").getAsString(),
                    ja.get(i).getAsJsonObject().get("description").getAsString(),
                    "0", "0"
            }));
        }
// "imageUrl": "https://images.pexels.com/photos/6292/blue-pattern-texture-macro.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
// "title":
// "price": 99.99,
// "ownerId"
// "description"
// "id"
        rv_adapterAllProd = new RV_AdapterAllProd(rowListItem);
        LinearLayoutManager lLayout = new LinearLayoutManager(getActivity());
        lLayout.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(rv_adapterAllProd);
        return view;
    }
}