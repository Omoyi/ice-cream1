package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class iceCreamList extends AppCompatActivity {

    @BindView(R.id.textView) TextView title;
    @BindView(R.id.iceList) TextView list;

    private String[] collection = new String[] {
            "Vanilla Ice Cream.", "Cookies Ice Cream.", "Cream Ice Cream.", "", ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_icecream);
        ButterKnife.bind(this);

        Intent ices = getIntent();
        String getInput = ices.getStringExtra("Rachel's Ice cream");
        title.setText(getInput);

//        icecreamAdapter listMaker = new icecreamAdapter(this, android.R.layout.simple_selectable_list_item, collection );
//        list.setAdapter(listMaker);


    }
}
