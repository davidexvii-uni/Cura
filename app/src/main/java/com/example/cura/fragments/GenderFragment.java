package com.example.cura.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.cura.R;
import com.example.cura.fragments.WeightFragment;
import com.example.cura.activities.QuestionarioActivity;

public class GenderFragment extends Fragment {

    private String selectedGender = null;

    private View maleLayout, femaleLayout;
    private Button nextBtn;

    public GenderFragment() {
        super(R.layout.fragment_gender);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        maleLayout = view.findViewById(R.id.layoutMale);
        femaleLayout = view.findViewById(R.id.layoutFemale);
        nextBtn = view.findViewById(R.id.btnContinue);

        maleLayout.setOnClickListener(v -> selectGender("male"));
        femaleLayout.setOnClickListener(v -> selectGender("female"));

        nextBtn.setOnClickListener(v -> {
            if (selectedGender == null) return;

            QuestionarioActivity activity = (QuestionarioActivity) requireActivity();
            activity.getProfile().gender = selectedGender;

            activity.loadFragment(new WeightFragment());
        });
    }

    private void selectGender(String gender) {
        selectedGender = gender;

        maleLayout.setSelected(false);
        femaleLayout.setSelected(false);

        if (gender.equals("male")) {
            maleLayout.setSelected(true);
        } else {
            femaleLayout.setSelected(true);
        }

        nextBtn.setEnabled(true);
    }
}
