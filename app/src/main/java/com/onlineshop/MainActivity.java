package com.onlineshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    JsonObject jo = new JsonObject();
    public static Fragment fragmentMain;
    public static FragmentManager fragmentManager;
    TextView btn_burger, tv_title, tv_cart;
//    public static List<String[]> list_cart = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_burger = findViewById(R.id.btn_burger);
        tv_title = findViewById(R.id.tv_title);
        tv_cart = findViewById(R.id.tv_cart);

        tv_cart.setOnClickListener(v ->
        {
            new CartShopActivity(v.getContext());
        });
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setVisibility(View.VISIBLE);

        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ProductsFragment()).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new OrdersFragment()).commit();

        tv_title.setText("All Products");
        LinearLayout ll = (LinearLayout) navigationView.getHeaderView(0);
        ((TextView) ll.getChildAt(1)).setText("Admin Name");
        ((TextView) ll.getChildAt(2)).setText("admin.admin@yahoo.com");

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(
                    menuItem -> {
                        menuItem.setChecked(true);
                        if (menuItem.getItemId() == R.id.item_list_products) {
                            fragmentMain = new ProductsFragment();
                            tv_title.setText("All Products");
                        }
                        if (menuItem.getItemId() == R.id.item_list_orders) {
                            fragmentMain = new OrdersFragment();
                            tv_title.setText("All Orders");
                        }
                        if (menuItem.getItemId() == R.id.item_list_admin) {
                            fragmentMain = new AdminFragment();
                            tv_title.setText("Admin");
                        }
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentMain).commit();
                        mDrawerLayout.closeDrawers();
                        return true;
                    });
            navigationView.getMenu().getItem(0).setVisible(true);
            navigationView.getMenu().getItem(1).setVisible(true);
            navigationView.getMenu().getItem(2).setVisible(true);
        }
        btn_burger.setOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
//  System.out.println("postAllOrder Result " + Api.postAllOrder(MainActivity.this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
// else if (item.getItemId() == R.id.item_refresh) {
// } else if (item.getItemId() == R.id.item_edit_profile) {
// } else if (item.getItemId() == R.id.item_logout) {
// } else if (item.getItemId() == R.id.item_about) {}
        return super.onOptionsItemSelected(item);
    }
}