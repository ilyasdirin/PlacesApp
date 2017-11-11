package com.idirin.placesapp.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class CategoryModel implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pluralName")
    @Expose
    public String pluralName;
    @SerializedName("shortName")
    @Expose
    public String shortName;
    @SerializedName("icon")
    @Expose
    public IconModel icon;
    @SerializedName("primary")
    @Expose
    public Boolean primary;


    public final static Parcelable.Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        public CategoryModel[] newArray(int size) {
            return (new CategoryModel[size]);
        }

    };

    protected CategoryModel(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.pluralName = ((String) in.readValue((String.class.getClassLoader())));
        this.shortName = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((IconModel) in.readValue((IconModel.class.getClassLoader())));
        this.primary = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public CategoryModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(pluralName);
        dest.writeValue(shortName);
        dest.writeValue(icon);
        dest.writeValue(primary);
    }

    public int describeContents() {
        return 0;
    }

}
