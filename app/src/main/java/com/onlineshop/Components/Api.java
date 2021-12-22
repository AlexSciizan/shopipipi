package com.onlineshop.Components;

import static com.onlineshop.Components.Utility.cekKoneksi;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.concurrent.ExecutionException;

public class Api {
    public static JsonArray getAllProducts(Context context) throws ExecutionException, InterruptedException {
        JsonArray ja = new JsonArray();
        try {
            if (cekKoneksi(context, false, true)) {
                ja = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Products").asJsonArray().get();
            }
// ownerId, title, imageUrl, description, price, id
// "ownerId": 1,
// "title": "Blue Carpet",
// "imageUrl": "https://images.pexels.com/photos/6292/blue-pattern-texture-macro.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
// "description": "Fits your red shirt perfectly. To stand on. Not to wear it.",
// "price": 99.99,
// "id": "2"
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return ja;
    }

    public static JsonObject getProductsById(Context context, int idProducts) throws ExecutionException, InterruptedException {
        JsonObject jo = new JsonObject();
        try {
            if (cekKoneksi(context, false, true)) {
                jo = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Products/" + idProducts).asJsonObject().get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return jo;
    }

    public static JsonObject getAllOrder(Context context) throws ExecutionException, InterruptedException {
        JsonObject jo = new JsonObject();
        try {
            if (cekKoneksi(context, false, true)) {
                jo = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders").asJsonObject().get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return jo;
    }

    public static JsonObject postAllOrder(Context context) throws ExecutionException, InterruptedException {
        JsonObject jo = new JsonObject();
// ownerId, title, imageUrl, description, price, id
        String raw_file = "\"ownerId\": 1,\n" +
                " \"title\": \"Blue Print\",\n" +
                " \"imageUrl\": \"https://images.pexels.com/photos/6292/blue-pattern-texture-macro.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260\",\n" +
                " \"description\": \"Fits your red shirt perfectly. To stand on. Not to wear it.\",\n" +
                " \"price\": 99.99,\n" +
                " \"id\": \"2\"";
// String raw_file = "{\"registration_ids\":[\"}";
        try {
            if (cekKoneksi(context, false, true)) {
                jo = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders").asJsonObject().get();
                Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders")
                        .setHeader("Content-Type", "application/x-www-form-urlencoded")
                        .setHeader("Accept", "application/json")
                        .setStringBody(raw_file)
                        .asString().withResponse().get();
//                  .addHeader("Content-type", "application/json")
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return jo;
    }

}
