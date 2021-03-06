package com.onlineshop.Components;

import static com.onlineshop.Components.Utility.cekKoneksi;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.ion.Ion;

import java.util.concurrent.ExecutionException;

public class Api {
    public static JsonArray getAllProducts(Context context) {
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

    public static JsonObject getProductsById(Context context, int idProducts) {
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

    public static JsonArray getAllOrder(Context context) {
        JsonArray ja = new JsonArray();
        try {
            if (cekKoneksi(context, false, true)) {
                ja = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders").asJsonArray().get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return ja;
    }

    // static String raw_file = "{\"items\":[{\"quantity\":xxx,\"product\":{\"ownerId\":xxx,\"title\":\"xxx\",\"imageUrl\":\"https://pngimg.com/uploads/smiley/smiley_PNG187.png\",\"description\":\"xxx\",\"price\":xxx,\"productId\":\"xxx\"},\"sum\":xxx},{\"quantity\": xxx,\"product\":{\"ownerId\":xxx,\"title\":\"xxx\",\"imageUrl\":\"https://pngimg.com/uploads/smiley/smiley_PNG187.png\",\"description\": \"xxx\",\"price\": xxx,\"productId\":\"xxx\"},\"sum\":xxx}],\"totalAmount\":xxx,\"date\":\"xxx\",\"id\":\"xxx\"}";
    static String raw_file = "{\"items\":[" +
            "{\"quantity\":25,\"product\":{\"ownerId\":21,\"title\":\"Baju Gamis\",\"imageUrl\":\"https://pngimg.com/uploads/smiley/smiley_PNG187.png\",\"description\":\"xxx\",\"price\":xxx,\"productId\":\"xxx\"},\"sum\":xxx}," +
            "{\"quantity\": xxx,\"product\":{\"ownerId\":xxx,\"title\":\"xxx\",\"imageUrl\":\"https://pngimg.com/uploads/smiley/smiley_PNG187.png\",\"description\": \"xxx\",\"price\": xxx,\"productId\":\"xxx\"},\"sum\":xxx}" +
            "],\"totalAmount\":xxx,\"date\":\"xxx\",\"id\":\"xxx\"}";

    public static JsonObject postAllOrder(Context context) {
        JsonObject jo = new JsonObject();
        Log.d("TAG", "postAllOrder: " + JsonParser.parseString(raw_file).getAsJsonObject());
// ownerId, title, imageUrl, description, price, id
// String raw_file = "{\"registration_ids\":[\"}";
        try {
            if (cekKoneksi(context, false, true)) {
// jo = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders").asJsonObject().get();
                jo = Ion.with(context).load("https://61b05c043c954f001722a369.mockapi.io/Orders")
                        .setJsonObjectBody(JsonParser.parseString(raw_file).getAsJsonObject())
                        .asJsonObject().withResponse().get().getResult();
//.addHeader("Content-type", "application/json")
//.setHeader("Content-Type", "application/x-www-form-urlencoded")
//.setHeader("Accept", "application/json")
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return jo;
    }
}
