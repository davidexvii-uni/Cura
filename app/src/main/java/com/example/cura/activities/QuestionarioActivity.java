package com.example.cura.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cura.R;
import com.example.cura.UserProfile;
import com.example.cura.fragments.GenderFragment;
import com.example.cura.fragments.StartQFragment;

public class QuestionarioActivity extends AppCompatActivity {

    private UserProfile profile = new UserProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        loadFragment(new StartQFragment());
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public UserProfile getProfile() {
        return profile;
    }
}
