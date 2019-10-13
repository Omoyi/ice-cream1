package com.myicecream.ice_cream1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class icecreamGallery extends AppCompatActivity {

    private EditText myInput;
    private Button galButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecream_gallery);

        galButton = (Button) findViewById(R.id.galButton);
        galButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ices = new Intent(icecreamGallery.this, iceCreamList.class);
                startActivity(ices);
            }
        });

    }
}
