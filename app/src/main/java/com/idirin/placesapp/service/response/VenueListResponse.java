package com.idirin.placesapp.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idirin.placesapp.service.models.VenueListModel;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListResponse extends BaseResponse {

    @SerializedName("response")
    @Expose
    private VenueListModel response;


    public VenueListModel getResponse() {
        return response;
    }

    public void setResponse(VenueListModel response) {
        this.response = response;
    }

}
