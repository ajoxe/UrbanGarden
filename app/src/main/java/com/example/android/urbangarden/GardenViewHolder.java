package com.example.android.urbangarden;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.urbangarden.database.GardensDataManager;
import com.example.android.urbangarden.database.GardensDatabase;
import com.example.android.urbangarden.model.Garden;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardenViewHolder extends RecyclerView.ViewHolder {

    private TextView gardenName, neighborhood, borough;
    private Button favesButton;
    Context context;


    public GardenViewHolder(View itemView) {
        super(itemView);
        gardenName = itemView.findViewById(R.id.garden_name_textview);
        neighborhood = itemView.findViewById(R.id.neighborhood_textview);
        borough = itemView.findViewById(R.id.borough_textview);
        favesButton = itemView.findViewById(R.id.add_to_faves_button);


    }

    public void bind(final Garden gardenData, final Context context) {
        gardenName.setText(gardenData.getGarden_name());
        neighborhood.setText(gardenData.getNeighborhoodname());

        borough.setText(convertBoro(gardenData.getBoro()));

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
        favesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gardenId = gardenData.getPropid();
                favesButton.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_filter_vintage_black2_24dp));


                GardensDataManager.updateGardenToSaved(gardenId, GardensDatabase.getGardensDatabase(context));
                Log.d("faves onclick", "SAVED GARDEN  : ");

            }
        });
    }

    public String convertBoro(String boro){
        switch (boro){
            case "B":
                return "Brooklyn";
            case "M":
                return "Manhattan";
            case "X":
                return "Bronx";
            case "Q":
                return "Queens";
            case "R":
                return "Staten Island";
            default:
                return boro;
        }
    }
}
