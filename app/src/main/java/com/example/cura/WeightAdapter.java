package com.example.cura;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.ViewHolder> {

    private List<Integer> weights;
    private int selectedPosition = 2;

    public WeightAdapter(List<Integer> weights) {
        this.weights = weights;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weight, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int value = weights.get(position);

        holder.textView.setText(String.valueOf(value));

        if(position == selectedPosition) {
            holder.textView.setTextSize(70);
            holder.textView.setTextColor(Color.parseColor("#1C4475"));
        } else {
            holder.textView.setTextSize(50);
            holder.textView.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return weights.size();
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtWeight);
        }
    }
}