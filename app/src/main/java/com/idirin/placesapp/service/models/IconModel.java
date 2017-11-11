package com.idirin.placesapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class IconModel implements Parcelable {

    @SerializedName("prefix")
    @Expose
    public String prefix;
    @SerializedName("suffix")
    @Expose
    public String suffix;


    public final static Parcelable.Creator<IconModel> CREATOR = new Creator<IconModel>() {

        @SuppressWarnings({
                "unchecked"
        })
        public IconModel createFromParcel(Parcel in) {
            return new IconModel(in);
        }

        public IconModel[] newArray(int size) {
            return (new IconModel[size]);
        }

    };


    protected IconModel(Parcel in) {
        this.prefix = ((String) in.readValue((String.class.getClassLoader())));
        this.suffix = ((String) in.readValue((String.class.getClassLoader())));
    }

    public IconModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(prefix);
        dest.writeValue(suffix);
    }

    public int describeContents() {
        return 0;
    }

}
