package com.myicecream.ice_cream1.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myicecream.ice_cream1.R;

import butterknife.BindView;

public class MyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.goToSignUpId) TextView toSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
    }

    @Override
    public void onClick(View account) {
       if (account == toSignUp) {
           Intent accounts = new Intent(MyLoginActivity.this, MysignupActivity.class);
           startActivity(accounts);
           finish();
       }
    }
}
