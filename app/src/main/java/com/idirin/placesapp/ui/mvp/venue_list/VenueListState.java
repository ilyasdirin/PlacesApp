package com.idirin.placesapp.ui.mvp.venue_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idirin.placesapp.service.models.VenueItemModel;

import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListState implements Parcelable {

    @SerializedName("paging")
    @Expose
    public Integer paging;
    @SerializedName("venues")
    @Expose
    public List<VenueItemModel> venues = null;


    public final static Parcelable.Creator<VenueListState> CREATOR = new Creator<VenueListState>() {
        public VenueListState createFromParcel(Parcel in) {
            return new VenueListState(in);
        }

        public VenueListState[] newArray(int size) {
            return (new VenueListState[size]);
        }
    };

    protected VenueListState(Parcel in) {
        this.paging = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.venues, (VenueItemModel.class.getClassLoader()));
    }

    public VenueListState() {
    }

    public VenueListState(List<VenueItemModel> venues, Integer paging) {
        this.venues = venues;
        this.paging = paging;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(paging);
        dest.writeList(venues);
    }

    public int describeContents() {
        return 0;
    }

}
