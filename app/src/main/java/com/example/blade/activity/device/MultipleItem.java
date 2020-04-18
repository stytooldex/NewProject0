package com.example.blade.activity.device;


public class MultipleItem {

    private String itemType;
    private String spanSize;


    public MultipleItem(String q, String w) {
        itemType = q;
        spanSize = w;
    }


    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(String spanSize) {
        this.spanSize = spanSize;
    }
}