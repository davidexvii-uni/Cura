package com.example.cura.activities;
import com.example.cura.R;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cura.fragments.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        // Se è la prima volta che l'activity viene creata, carica il LoginFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser() ;
        if (currentUser != null) {
            vaiAllaMain();
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void vaiAllaMain() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
