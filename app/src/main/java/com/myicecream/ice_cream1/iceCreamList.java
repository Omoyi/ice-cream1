package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = iceCreamList.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_icecream);
        ButterKnife.bind(this);

        Intent ices = getIntent();
        String getInput = ices.getStringExtra("Rachel's Ice cream");
        title.setText(getInput);

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
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Business> icyList = response.body().getBusinesses();
                    String[] icecreams = new String[icyList.size()];
                    String[] categories = new String[icyList.size()];

                    for (int i = 0; i < icecreams.length; i++){
                        icecreams      [i] = icyList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = icyList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

//                    ArrayAdapter adapter = new icecreamAdapter(iceCreamList.this, android.R.layout.simple_list_item_1, collection, categories);
//                    list.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
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
