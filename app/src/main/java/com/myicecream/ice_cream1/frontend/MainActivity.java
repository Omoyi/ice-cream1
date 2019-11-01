package com.myicecream.ice_cream1.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myicecream.ice_cream1.R;

public class MainActivity extends AppCompatActivity {
    private Button homeButton;
    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener AuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeButton = (Button) findViewById(R.id.homeButton);

        Auth = FirebaseAuth.getInstance();

        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Lovely " + user.getDisplayName());
                } else {

                }
            }
        };
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(MainActivity.this, icecreamGallery.class);
                startActivity(gallery);
            }

        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent logIn = new Intent(MainActivity.this, MyLoginActivity.class);
        logIn.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logIn);
        finish();
    }
}
