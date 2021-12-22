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
import java.util.concurrent.ExecutionException;

public class ProductsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProductsFragment() {
    }

    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        List<ItemObject> rowListItem = new ArrayList<>();
        RV_AdapterAllProd rv_adapterAllProd;
        try {
            JsonArray ja = Api.getAllProducts(getActivity());
            for (int i = 0; i < ja.size(); i++) {
                System.out.println(" "+ja.get(i).getAsJsonObject().get("imageUrl").getAsString());
                System.out.println(" "+ja.get(i).getAsJsonObject().get("title").getAsString());
                System.out.println(" "+ja.get(i).getAsJsonObject().get("price").getAsString());
                rowListItem.add(new ItemObject(new String[]{
                        ja.get(i).getAsJsonObject().get("imageUrl").getAsString(),
                        ja.get(i).getAsJsonObject().get("title").getAsString(),
                        ja.get(i).getAsJsonObject().get("price").getAsString()}));
            }
//            "imageUrl": "https://images.pexels.com/photos/6292/blue-pattern-texture-macro.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
//                    "title"
//            "price": 99.99,
//                    "ownerId"
//            "description"
//            "id"
            rv_adapterAllProd = new RV_AdapterAllProd(rowListItem);
            LinearLayoutManager lLayout = new LinearLayoutManager(getActivity());
            lLayout.setOrientation(RecyclerView.VERTICAL);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(lLayout);
            recyclerView.setAdapter(rv_adapterAllProd);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        return view;
    }
}