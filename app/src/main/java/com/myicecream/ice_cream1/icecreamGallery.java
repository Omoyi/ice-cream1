package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


public class icecreamGallery extends AppCompatActivity {

    private EditText myInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecream_gallery);
        String icecreams = myInput.getText().toString();

        Intent ices = new Intent(icecreamGallery.this, iceCreamList.class);
        startActivity(ices);

    }
}
