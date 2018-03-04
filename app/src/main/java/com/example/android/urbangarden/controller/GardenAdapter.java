package com.example.android.urbangarden.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.urbangarden.GardenViewHolder;
import com.example.android.urbangarden.R;
import com.example.android.urbangarden.model.Garden;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardenAdapter extends RecyclerView.Adapter<GardenViewHolder> {

    List<Garden> gardenDataList = new ArrayList<>();
    Context context;

    public GardenAdapter(List<Garden> gardenDataList, Context context) {
        this.gardenDataList = gardenDataList;
        this.context = context;
    }

    @Override
    public GardenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.garden_item_view, parent, false);
        return new GardenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GardenViewHolder holder, int position) {
        holder.bind(gardenDataList.get(position), context);

    }

    @Override
    public int getItemCount() {
        return gardenDataList.size();
    }
}
