package com.idirin.placesapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueModel implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("location")
    @Expose
    public LocationModel location;
    @SerializedName("categories")
    @Expose
    public List<CategoryModel> categories = null;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("ratingColor")
    @Expose
    public String ratingColor;
    @SerializedName("bestPhoto")
    @Expose
    public IconModel bestPhoto;

    public transient Boolean isSelected;


    public final static Parcelable.Creator<VenueModel> CREATOR = new Creator<VenueModel>() {

        @SuppressWarnings({
                "unchecked"
        })
        public VenueModel createFromParcel(Parcel in) {
            return new VenueModel(in);
        }

        public VenueModel[] newArray(int size) {
            return (new VenueModel[size]);
        }

    };

    protected VenueModel(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((LocationModel) in.readValue((LocationModel.class.getClassLoader())));
        in.readList(this.categories, (CategoryModel.class.getClassLoader()));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.ratingColor = ((String) in.readValue((String.class.getClassLoader())));
        this.bestPhoto = ((IconModel) in.readValue((IconModel.class.getClassLoader())));
        this.isSelected = ((Boolean) in.readValue(Boolean.class.getClassLoader()));
    }

    public VenueModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(location);
        dest.writeList(categories);
        dest.writeValue(url);
        dest.writeValue(rating);
        dest.writeValue(ratingColor);
        dest.writeValue(bestPhoto);
        dest.writeValue(isSelected);
    }

    public int describeContents() {
        return 0;
    }

}
