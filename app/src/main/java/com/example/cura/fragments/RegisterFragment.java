package com.example.cura.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cura.R;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText emailEditText = view.findViewById(R.id.email);
        EditText passwordEditText = view.findViewById(R.id.password);
        EditText c_passwordEditText = view.findViewById(R.id.passwordconfirm);
        CheckBox tosCheckBox = view.findViewById(R.id.checkbox);

        Button btnRegister = view.findViewById(R.id.crea);
        btnRegister.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = c_passwordEditText.getText().toString();


            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Compila tutti i campi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                c_passwordEditText.setError("Le password non coincidono");
                return;
            }

            if (!tosCheckBox.isChecked()) {
                Toast.makeText(getContext(), "Accetta i termini di servizio", Toast.LENGTH_SHORT).show();
                return;
            }
            registraNuovoUtente(email, password);
        });

        return view;
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
