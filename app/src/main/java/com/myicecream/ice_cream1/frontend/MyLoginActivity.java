package com.myicecream.ice_cream1.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.myicecream.ice_cream1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.goToSignUpId) TextView toSignUp;
    @BindView(R.id.homeButton) Button LoginButton;
    @BindView(R.id.emailEditText) EditText EmailEText;
    @BindView(R.id.pswdId) EditText PasswordEText;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);

        Auth = FirebaseAuth.getInstance();
        toSignUp.setOnClickListener(this);
        LoginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       if (v == toSignUp) {
           Intent accounts = new Intent(MyLoginActivity.this, MysignupActivity.class);
           startActivity(accounts);
           finish();
       }

        if (v == LoginButton) {
            loginWithPassword();
        }
    }

    private void loginWithPassword() {
        String email = EmailEText.getText().toString().trim();
        String password = PasswordEText.getText().toString().trim();
        if (email.equals("")) {
            EmailEText.setError("Do enter your email");
            return;
        }
        if (password.equals("")) {
            PasswordEText.setError("fill the Password space");
            return;
        }
    }
}
