package com.example.cura.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cura.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
            return;
        }

        String uid = user.getUid();

        db.collection("users").document(uid).get()
                .addOnSuccessListener(document -> {
                    if (document.exists()) {
                        Boolean completed = document.getBoolean("profileCompleted");
                        if (completed == null || !completed) {
                            // PRIMO ACCESSO
                            startActivity(new Intent(this, QuestionarioActivity.class));
                            finish();
                        } else {
                            // UTENTE NORMALE
                            setContentView(R.layout.activity_main);
                        }

                    } else {
                        // UTENTE NUOVO (documento non esiste)
                        Map<String, Object> data = new HashMap<>();
                        data.put("profileCompleted", false);

                        db.collection("users").document(uid).set(data);

                        startActivity(new Intent(this, QuestionarioActivity.class));
                        finish();
                    }
                });
    }
}
