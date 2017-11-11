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

public class LocationModel implements Parcelable {

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;
    @SerializedName("distance")
    @Expose
    public Integer distance;
    @SerializedName("postalCode")
    @Expose
    public String postalCode;
    @SerializedName("cc")
    @Expose
    public String cc;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("formattedAddress")
    @Expose
    public List<String> formattedAddress = null;
    public final static Parcelable.Creator<LocationModel> CREATOR = new Creator<LocationModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LocationModel createFromParcel(Parcel in) {
            return new LocationModel(in);
        }

        public LocationModel[] newArray(int size) {
            return (new LocationModel[size]);
        }

    };

    protected LocationModel(Parcel in) {
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lng = ((Double) in.readValue((Double.class.getClassLoader())));
        this.distance = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.postalCode = ((String) in.readValue((String.class.getClassLoader())));
        this.cc = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.formattedAddress, (java.lang.String.class.getClassLoader()));
    }

    public LocationModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(distance);
        dest.writeValue(postalCode);
        dest.writeValue(cc);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(country);
        dest.writeList(formattedAddress);
    }

    public int describeContents() {
        return 0;
    }

}
