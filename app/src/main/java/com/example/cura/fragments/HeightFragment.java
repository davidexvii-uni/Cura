package com.example.cura.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cura.R;
import com.example.cura.WeightAdapter;
import com.example.cura.activities.QuestionarioActivity;

import java.util.ArrayList;
import java.util.List;

public class HeightFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeightAdapter adapter;
    private List<Integer> heightList;
    private Integer selectedHeight = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_height, container, false);

        recyclerView = view.findViewById(R.id.heightRecycler);
        Button nextBtn = view.findViewById(R.id.btnContinue);
        ImageButton backBtn = view.findViewById(R.id.btnBack);

        heightList = new ArrayList<>();
        for (int i = 100; i <= 230; i++) {
            heightList.add(i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WeightAdapter(heightList);
        recyclerView.setAdapter(adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        int posizioneIniziale = 70;
        selectedHeight = heightList.get(posizioneIniziale);
        adapter.setSelectedPosition(posizioneIniziale);
        recyclerView.scrollToPosition(posizioneIniziale);

        recyclerView.post(() -> {
            View centerView = snapHelper.findSnapView(layoutManager);
            if (centerView != null) {
                int pos = layoutManager.getPosition(centerView);
                selectedHeight = heightList.get(pos);
                adapter.setSelectedPosition(pos);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    if (centerView != null) {
                        int pos = layoutManager.getPosition(centerView);
                        selectedHeight = heightList.get(pos);
                        adapter.setSelectedPosition(pos);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                View centerView = snapHelper.findSnapView(layoutManager);
                if (centerView != null) {
                    int pos = layoutManager.getPosition(centerView);
                    adapter.setSelectedPosition(pos);
                }
            }
        });

        nextBtn.setOnClickListener(v -> {
            if (selectedHeight == null) return;
            QuestionarioActivity activity = (QuestionarioActivity) requireActivity();

            activity.getProfile().altezza = Double.valueOf(selectedHeight);

            activity.loadFragment(new FitlvlFragment());
        });

        backBtn.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        return view;
    }
}