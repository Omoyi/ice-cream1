package com.myicecream.ice_cream1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBusinessesSearchResponse> getIcecreams(
            @Query("location") String location,
            @Query("term") String term
    );
}
