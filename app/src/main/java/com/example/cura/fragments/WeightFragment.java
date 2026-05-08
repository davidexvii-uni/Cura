package com.example.cura.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cura.R;
import com.example.cura.WeightAdapter;
import com.example.cura.activities.QuestionarioActivity;
import com.example.cura.fragments.HeightFragment;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeightAdapter adapter;
    private List<Integer> weightList;
    private Integer selectedWeight = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        recyclerView = view.findViewById(R.id.weightRecycler);
        Button nextBtn = view.findViewById(R.id.btnContinue);

        weightList = new ArrayList<>();
        for (int i = 30; i <= 150; i++) {
            weightList.add(i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WeightAdapter(weightList);
        recyclerView.setAdapter(adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        int posizioneIniziale = 36;
        selectedWeight = weightList.get(posizioneIniziale);
        adapter.setSelectedPosition(posizioneIniziale);
        recyclerView.scrollToPosition(posizioneIniziale);

        recyclerView.post(() -> {
            View centerView = snapHelper.findSnapView(layoutManager);
            if (centerView != null) {
                int pos = layoutManager.getPosition(centerView);
                selectedWeight = weightList.get(pos);
                adapter.setSelectedPosition(pos);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    if (centerView != null) {
                        int pos = layoutManager.getPosition(centerView);
                        selectedWeight = weightList.get(pos);
                        adapter.setSelectedPosition(pos);
                        android.util.Log.d("DEBUG_SCROLL", "Peso aggiornato a: " + selectedWeight);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View centerView = snapHelper.findSnapView(layoutManager);
                if (centerView != null) {
                    int pos = layoutManager.getPosition(centerView);
                    adapter.setSelectedPosition(pos);
                }
            }
        });
        nextBtn.setOnClickListener(v -> {
            if (selectedWeight == null) return;

            QuestionarioActivity activity = (QuestionarioActivity) requireActivity();
            activity.getProfile().peso = Double.valueOf(selectedWeight);

            activity.loadFragment(new HeightFragment());
        });

        return view;
    }
}