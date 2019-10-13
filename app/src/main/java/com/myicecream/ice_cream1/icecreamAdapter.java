package com.myicecream.ice_cream1;

import android.content.Context;
import android.widget.ArrayAdapter;

public class icecreamAdapter extends ArrayAdapter {
    private Context type;
    private String[] collection;
    private String[] place;

    public icecreamAdapter(Context type,  int resource, String[] collection, String[] place) {
        super(type, resource);
        this.type = type;
        this.collection = collection;
        this.place = place;

    }

    @Override
    public Object getItem(int position) {
        String restaurant = collection[position];
        String cuisine = place[position];
        return String.format("%s \nFound at: %s", restaurant, cuisine);
    }

    @Override
    public int getCount() {
        return collection.length;
    }

}
