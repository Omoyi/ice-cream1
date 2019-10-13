package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class iceCreamList extends AppCompatActivity {

    @BindView(R.id.textView) TextView title;
    @BindView(R.id.iceList) ListView list;

    private String[] collection = new String[] {
            "Vanilla Ice Cream.", "Cookies Ice Cream.", "Cream Ice Cream.", "Birthday Cake Ice Cream.", "Bubble Gum Ice Cream.", "Strawberry Ice Cream.",
            "Black Cherry Ice Cream.", "Sundae Ice Cream", "Chocolate Peanut Butter Ice Cream.", "Cookie Dough"
            , "Mint Chocolate Chip."
    };
    private String[] place = new String[] {
            "Sweet Hereafter", "Cricket", "Red Square", "Kigali heights", "Slappy Cakes", "Chez lando",
            "Fast food hub", "Horse Brass", "la Palis", "Bambino"
            , "Digest ltd"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_icecream);
        ButterKnife.bind(this);

        Intent ices = getIntent();
        String getInput = ices.getStringExtra("Rachel's Ice cream");
        title.setText(getInput);

        icecreamAdapter listMaker = new icecreamAdapter(this, android.R.layout.simple_selectable_list_item, collection, place );
        list.setAdapter(listMaker);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String collection = ((TextView)view).getText().toString();
                Toast.makeText(iceCreamList.this, collection, Toast.LENGTH_LONG).show();
            }
        });
    }
}
