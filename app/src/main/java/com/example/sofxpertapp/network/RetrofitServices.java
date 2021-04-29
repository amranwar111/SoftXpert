package com.example.sofxpertapp.network;

import com.example.sofxpertapp.models.CarsResponseModel;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServices {

    @GET("cars")
    Flowable<CarsResponseModel> getCars(@Query("page") int page);
}
