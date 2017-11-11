package com.idirin.placesapp.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class GroupModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("items")
    @Expose
    private List<VenueItemModel> items = null;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VenueItemModel> getItems() {
        return items;
    }

    public void setItems(List<VenueItemModel> items) {
        this.items = items;
    }

}
