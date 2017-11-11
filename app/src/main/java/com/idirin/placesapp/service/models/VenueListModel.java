package com.idirin.placesapp.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListModel {

    @SerializedName("groups")
    @Expose
    private List<GroupModel> groups = null;

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }

}
