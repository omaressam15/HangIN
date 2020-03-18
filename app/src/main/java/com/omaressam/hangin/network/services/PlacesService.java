package com.omaressam.hangin.network.services;

import com.omaressam.hangin.Place;
import com.omaressam.hangin.network.api.APIConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacesService {

  @GET(APIConstants.SERVICE_PLACES)
    Call<List<Place>>getPlaces();
}
