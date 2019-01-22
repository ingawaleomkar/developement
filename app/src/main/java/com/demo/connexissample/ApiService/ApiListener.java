package com.demo.connexissample.ApiService;

import com.demo.connexissample.model.MasterPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by samrat on 22/1/19.
 */

public interface ApiListener {

    @GET("api/users")
    Call<MasterPojo> getTopRatedMovies(@Query("page") String apiKey);

}
