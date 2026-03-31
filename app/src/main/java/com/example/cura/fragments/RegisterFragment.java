package com.example.cura.fragments;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
    }

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inizializza l'istanza di Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void registraNuovoUtente(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Registrazione riuscita, aggiorna l'interfaccia o vai alla Home
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Benvenuto " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Se fallisce, mostra un messaggio all'utente
                        Toast.makeText(getContext(), "Errore: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
