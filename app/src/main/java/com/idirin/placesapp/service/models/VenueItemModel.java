package com.idirin.placesapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueItemModel implements Parcelable {

    @SerializedName("venue")
    @Expose
    public VenueModel venue;


    public final static Parcelable.Creator<VenueItemModel> CREATOR = new Creator<VenueItemModel>() {

        @SuppressWarnings({
                "unchecked"
        })
        public VenueItemModel createFromParcel(Parcel in) {
            return new VenueItemModel(in);
        }

        public VenueItemModel[] newArray(int size) {
            return (new VenueItemModel[size]);
        }

    };

    protected VenueItemModel(Parcel in) {
        this.venue = ((VenueModel) in.readValue((VenueModel.class.getClassLoader())));
    }

    public VenueItemModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(venue);
    }

    public int describeContents() {
        return 0;
    }

}
