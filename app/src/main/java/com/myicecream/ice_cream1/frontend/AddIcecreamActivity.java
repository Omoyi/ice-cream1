package com.myicecream.ice_cream1.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myicecream.ice_cream1.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddIcecreamActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.cardView1) CardView wishList;
    @BindView(R.id.cardView2) CardView favorite;
    @BindView(R.id.cardView3) CardView search;
    @BindView(R.id.cardView4) CardView createAccount;
    @BindView(R.id.cardView5) CardView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_icecream);
        ButterKnife.bind(this);
        wishList.setOnClickListener(this);
        favorite.setOnClickListener(this);
        search.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        home.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == wishList) {
            Intent wish = new Intent(AddIcecreamActivity.this, MyLoginActivity.class);
            startActivity(wish);
        }

        if (v == favorite) {
            Intent favorite = new Intent(AddIcecreamActivity.this, MyLoginActivity.class);
            startActivity(favorite);
        }

        if (v == search) {
            Intent search = new Intent(AddIcecreamActivity.this, icecreamGallery.class);
            startActivity(search);
        }

        if (v == createAccount) {
            Intent createAccount = new Intent(AddIcecreamActivity.this, MysignupActivity.class);
            startActivity(createAccount);
        }

        if (v == home) {
            Intent search = new Intent(AddIcecreamActivity.this, MainActivity.class);
            startActivity(search);
        }

    }
}
