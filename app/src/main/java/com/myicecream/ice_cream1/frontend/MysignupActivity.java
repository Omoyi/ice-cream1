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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.myicecream.ice_cream1.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MysignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth Auth;
    public static final String TAG = MysignupActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener AuthenticationListener;
    private ProgressDialog processDialog;
    private String USERSNAME;



    @BindView(R.id.createUserButton) Button submitAccount;
    @BindView(R.id.nameEditText) EditText NameEText;
    @BindView(R.id.emailEditText) EditText EmailEText;
    @BindView(R.id.passwordEditText) EditText PasswordEText;
    @BindView(R.id.confirmPasswordEditText) EditText ConfPswEText;
    @BindView(R.id.loginTextView) TextView goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysignup);
        ButterKnife.bind(this);
        createAuthProgressDialog();
        Auth = FirebaseAuth.getInstance();
        createAuthStateListener();
        goToLogin.setOnClickListener(this);
        submitAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View account) {
        if (account == goToLogin) {
            Intent login = new Intent(MysignupActivity.this, MyLoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            finish();
        }
        if (account == submitAccount) {
            createNewUser();
        }
    }

    private void createNewUser() {

        USERSNAME = NameEText.getText().toString().trim();
        final String name = NameEText.getText().toString().trim();
        final String email = EmailEText.getText().toString().trim();
        String password = PasswordEText.getText().toString().trim();
        String confirmPassword = ConfPswEText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;
        processDialog.show();
        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    processDialog.dismiss();

                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication successful");
                        createFirebaseUserProfile(task.getResult().getUser());
                    }
                    else {
                        Toast.makeText(MysignupActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }

    private void createAuthStateListener() {
        AuthenticationListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser owner = firebaseAuth.getCurrentUser();
                if (owner != null) {
                    Intent intent = new Intent(MysignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(USERSNAME)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, user.getDisplayName());
                        }
                    }

                });
    }

    private void createAuthProgressDialog() {
        processDialog = new ProgressDialog(this);
        processDialog.setTitle("coming soon...");
        processDialog.setMessage("on Authentication with Firebase...");
        processDialog.setCancelable(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Auth.addAuthStateListener(AuthenticationListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthenticationListener != null) {
            Auth.removeAuthStateListener(AuthenticationListener);
        }
    }



    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            EmailEText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            NameEText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            PasswordEText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            PasswordEText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

}
