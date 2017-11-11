package com.idirin.placesapp.service;

import com.idirin.placesapp.service.response.VenueDetailResponse;
import com.idirin.placesapp.service.response.VenueListResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by
 * idirin on 09/11/2017.
 */

public interface RestInterface {

    @GET("explore")
    Observable<VenueListResponse> getVenues(@QueryMap Map<String, Object> params);

    @GET("{venueId}")
    Observable<VenueDetailResponse> getVenueDetail(@Path("venueId") String venueId, @QueryMap Map<String, Object> params);

}
