package com.example.android.urbangarden.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amirahoxendine on 3/3/18.
 */
@Entity(tableName = "gardens")
public class Garden {
    @PrimaryKey @NonNull
    String propid;
    String address;
    String bbl;
    String bin;
    String boro;
    String census_tract;
    String community_board;
    String council_district;
    String cross_streets;
    String garden_name;
    String jurisdiction;
    String latitude;
    String longitude;
    String neighborhoodname;
    String nta;
    String postcode;
    String status;
    String size;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBbl() {
        return bbl;
    }

    public void setBbl(String bbl) {
        this.bbl = bbl;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getBoro() {
        return boro;
    }

    public void setBoro(String boro) {
        this.boro = boro;
    }

    public String getCensus_tract() {
        return census_tract;
    }

    public void setCensus_tract(String census_tract) {
        this.census_tract = census_tract;
    }

    public String getCommunity_board() {
        return community_board;
    }

    public void setCommunity_board(String community_board) {
        this.community_board = community_board;
    }

    public String getCouncil_district() {
        return council_district;
    }

    public void setCouncil_district(String council_district) {
        this.council_district = council_district;
    }

    public String getCross_streets() {
        return cross_streets;
    }

    public void setCross_streets(String cross_streets) {
        this.cross_streets = cross_streets;
    }

    public String getGarden_name() {
        return garden_name;
    }

    public void setGarden_name(String garden_name) {
        this.garden_name = garden_name;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNeighborhoodname() {
        return neighborhoodname;
    }

    public void setNeighborhoodname(String neighborhoodname) {
        this.neighborhoodname = neighborhoodname;
    }

    public String getNta() {
        return nta;
    }

    public void setNta(String nta) {
        this.nta = nta;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPropid() {
        return propid;
    }

    public void setPropid(String propid) {
        this.propid = propid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
