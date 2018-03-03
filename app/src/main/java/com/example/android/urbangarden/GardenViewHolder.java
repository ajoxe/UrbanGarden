package com.example.android.urbangarden;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.urbangarden.model.Garden;
import com.example.android.urbangarden.model.GardenData;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardenViewHolder extends RecyclerView.ViewHolder {

    private TextView gardenName, neighborhood, borough;
    private Button favesButton;

    public GardenViewHolder(View itemView) {
        super(itemView);
        gardenName = itemView.findViewById(R.id.garden_name_textview);
        neighborhood = itemView.findViewById(R.id.neighborhood_textview);
        borough = itemView.findViewById(R.id.borough_textview);
        favesButton = itemView.findViewById(R.id.add_to_faves_button);
    }

    public void bind(final GardenData gardenData) {
        gardenName.setText(gardenData.getGarden_name());
        neighborhood.setText(gardenData.getNeighborhoodname());
        borough.setText(gardenData.getBoro());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gardenDetailIntent = new Intent(itemView.getContext(), GardenDetailActivity.class);
                gardenDetailIntent.putExtra("name", gardenData.getGarden_name());
                gardenDetailIntent.putExtra("neighborhood", gardenData.getNeighborhoodname());
                gardenDetailIntent.putExtra("borough", gardenData.getNeighborhoodname());
                gardenDetailIntent.putExtra("address", gardenData.getAddress());
                gardenDetailIntent.putExtra("postcode", gardenData.getPostcode());
                gardenDetailIntent.putExtra("cross streets", gardenData.getCross_streets());

                itemView.getContext().startActivity(gardenDetailIntent);

            }
        });
    }
}
