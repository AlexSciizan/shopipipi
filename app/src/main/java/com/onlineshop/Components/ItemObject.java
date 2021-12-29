package com.onlineshop.Components;

import java.util.List;

public class ItemObject {
    private String[] isiList;
    private List<String[]> arrList;

    public ItemObject(String[] isi_list) {
        isiList = isi_list;
    }

    public ItemObject(String[] isi_list, List<String[]> arr_list) {
        isiList = isi_list;
        arrList = arr_list;
    }

    public String getItem(int i) {
        return isiList[i];
    }

    public List<String[]> getListStringArr() {
        return arrList;
    }
}