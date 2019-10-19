package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class iceCreamList extends AppCompatActivity {

    @BindView(R.id.textView) TextView title;
    @BindView(R.id.iceList) ListView list;
    @BindView(R.id.errorTextView) TextView Error;
    @BindView(R.id.progressBar) ProgressBar Loading;

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

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getIcecreams(getInput, "icecreams");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Business> icyList = response.body().getBusinesses();
                    String[] collection = new String[icyList.size()];
                    String[] categories = new String[icyList.size()];

                    for (int i = 0; i < collection.length; i++){
                        collection[i] = icyList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = icyList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter = new icecreamAdapter(iceCreamList.this, android.R.layout.simple_list_item_1, collection, categories);
                    list.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {

            }
        });

    }

    private void showFailureMessage() {
        Error.setText("Something went wrong. Please check your Internet connection and try again later");
        Error.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        Error.setText("Something went wrong. Please try again later");
        Error.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        list.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        Loading.setVisibility(View.GONE);
    }

}
