package com.idirin.placesapp.ui.mvp.venue_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idirin.placesapp.service.models.VenueModel;

/**
 * Created by
 * idirin on 11/11/2017...
 */

public class VenueDetailState implements Parcelable {

    @SerializedName("venue")
    @Expose
    private VenueModel venue;

    public VenueDetailState(VenueModel venue) {
        this.venue = venue;
    }

    public VenueModel getVenue() {
        return venue;
    }

    public final static Parcelable.Creator<VenueDetailState> CREATOR = new Creator<VenueDetailState>() {
        public VenueDetailState createFromParcel(Parcel in) {
            return new VenueDetailState(in);
        }
        public VenueDetailState[] newArray(int size) {
            return (new VenueDetailState[size]);
        }
    };

    protected VenueDetailState(Parcel in) {
        this.venue = ((VenueModel) in.readValue((VenueModel.class.getClassLoader())));
    }

    public VenueDetailState() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(venue);
    }

    public int describeContents() {
        return 0;
    }

}
