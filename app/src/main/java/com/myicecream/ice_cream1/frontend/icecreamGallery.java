package com.myicecream.ice_cream1.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myicecream.ice_cream1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class icecreamGallery extends AppCompatActivity {

    @BindView(R.id.enterText) EditText myInput;
    @BindView(R.id.galButton) Button galButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icecream_gallery);
        ButterKnife.bind(this);

        galButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = myInput.getText().toString();
                Intent ices = new Intent(icecreamGallery.this, iceCreamList.class);
                ices.putExtra("Rachel's Ice cream", getInput);
                startActivity(ices);
            }
        });

    }
}
