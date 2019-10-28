package com.myicecream.ice_cream1.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myicecream.ice_cream1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.goToSignUpId) TextView toSignUp;
    @BindView(R.id.homeButton) Button LoginButton;
    @BindView(R.id.emailId) EditText EmailEText;
    @BindView(R.id.pswdId) EditText PasswordEText;

    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private ProgressDialog ProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);
        createAuthProgressDialog();

        Auth = FirebaseAuth.getInstance();
        toSignUp.setOnClickListener(this);
        LoginButton.setOnClickListener(this);


        AuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(MyLoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createAuthProgressDialog() {
        ProgressDialog = new ProgressDialog(this);
        ProgressDialog.setTitle("coming soon...");
        ProgressDialog.setMessage("Authenticating with Firebase...");
        ProgressDialog.setCancelable(false);
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

    @Override
    public void onStart() {
        super.onStart();
        Auth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListener != null) {
            Auth.removeAuthStateListener(AuthListener);
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
        ProgressDialog.show();
        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            ProgressDialog.dismiss();
                            Toast.makeText(MyLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
