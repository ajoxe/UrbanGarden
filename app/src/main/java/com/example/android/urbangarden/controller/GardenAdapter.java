package com.example.android.urbangarden.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.urbangarden.GardenViewHolder;
import com.example.android.urbangarden.R;
import com.example.android.urbangarden.model.GardenData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardenAdapter extends RecyclerView.Adapter<GardenViewHolder> {

    List<GardenData> gardenDataList = new ArrayList<>();

    public GardenAdapter(List<GardenData> gardenDataList) {
        this.gardenDataList = gardenDataList;
    }

    @Override
    public GardenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.garden_item_view, parent, false);
        return new GardenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GardenViewHolder holder, int position) {
        holder.bind(gardenDataList.get(position));

    }

    @Override
    public int getItemCount() {
        return gardenDataList.size();
    }
}
