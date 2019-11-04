package com.myicecream.ice_cream1.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myicecream.ice_cream1.R;
import com.myicecream.ice_cream1.WishedIcecream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.button) Button button;
    @BindView(R.id.iceListview) ListView icelistView;
    DatabaseReference wishList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        wishList = FirebaseDatabase.getInstance().getReference("icecreams");
        ButterKnife.bind(this);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            addWishedIcecream();
        }
    }

    // Add data to the database

    private void addWishedIcecream() {
        String Iwish = editText.getText().toString().trim();
        String type = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(Iwish)){
            String id = wishList.push().getKey();

            WishedIcecream wish = new WishedIcecream(id, Iwish, type);
            wishList.child(id).setValue(wish);
            Toast.makeText(this, "Saved ", Toast.LENGTH_LONG).show();

        }

        else {
            Toast.makeText(this,"please, provide the size you would like!", Toast.LENGTH_LONG).show();
        }
    }
}
