package com.idirin.placesapp.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idirin.placesapp.service.models.VenueDetailModel;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueDetailResponse extends BaseResponse {

    @SerializedName("response")
    @Expose
    private VenueDetailModel response;


    public VenueDetailModel getResponse() {
        return response;
    }

    public void setResponse(VenueDetailModel response) {
        this.response = response;
    }


}
