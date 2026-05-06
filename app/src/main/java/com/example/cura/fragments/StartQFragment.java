package com.example.cura.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cura.R;
import com.example.cura.activities.QuestionarioActivity;

public class StartQFragment extends Fragment {
    private Button nextBtn;

    public StartQFragment(){
        super(R.layout.fragment_startq);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        nextBtn.setOnClickListener(view1 -> {
            QuestionarioActivity activity = (QuestionarioActivity) requireActivity();
            activity.loadFragment(new GenderFragment());
        });
    }

}
