package com.example.sofxpertapp.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private RetrofitServices retrofitServices;

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://demo1286023.mockable.io/api/v1/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {



            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }

        return retrofit;
    }

    RetrofitServices getRetrofit() {
         return retrofitServices = retrofit.create(RetrofitServices.class);
    }
}
