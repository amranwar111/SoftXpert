package com.example.sofxpertapp.mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sofxpertapp.models.CarsResponseModel;
import com.example.sofxpertapp.network.RetrofitClient;
import com.example.sofxpertapp.network.RetrofitServices;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CarViewModel extends ViewModel {

    private MutableLiveData<CarsResponseModel> carsLiveData = new MutableLiveData();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RetrofitServices retrofitServices;

    @SuppressLint("CheckResult")
    public void getCars(Context context, int page) {

        retrofitServices = RetrofitClient.getRetrofitInstance().create(RetrofitServices.class);

        try {
            retrofitServices.getCars(page).
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(value -> carsLiveData.postValue(value))
                    .doOnError(error -> Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show());

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public MutableLiveData<CarsResponseModel> getData() {
        return carsLiveData;
    }
}
