package com.myicecream.ice_cream1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import butterknife.ButterKnife;

public class wishedList extends ArrayAdapter<WishedIcecream> {

    private Context context;
    private List<WishedIcecream> icelist;

    public wishedList(Context context, List<WishedIcecream> icelist) {
        super(context, R.layout.wish_list, icelist);
        this.context = context;
        this.icelist = icelist;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View iceview =LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list, parent, false);

        TextView textViewSize = (TextView) iceview.findViewById(R.id.textViewSize);
        TextView textViewType = (TextView) iceview.findViewById(R.id.textViewtype);
        WishedIcecream wish = icelist.get(position);

        textViewSize.setText(wish.getSize());
        textViewType.setText(wish.getType());
        return iceview;
    }

//    private LayoutInflater getLayoutInflator() {
//        return null;
//    }
}
