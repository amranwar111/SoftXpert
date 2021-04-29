package com.example.sofxpertapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sofxpertapp.models.CarsResponseModel;
import com.example.sofxpertapp.models.DataItem;
import com.example.sofxpertapp.mvvm.CarViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView carRecyclerView;
    private CarsAdapter carsAdapter;
    private ArrayList<DataItem> cars;
    private CarViewModel carViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swiperefresh;

    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cars = new ArrayList();
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        carsAdapter = new CarsAdapter(this, cars);

        carRecyclerView = findViewById(R.id.carRecyclerView);
        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(this);
        carRecyclerView.setAdapter(carsAdapter);

        carViewModel.getCars(this, currentPage);

        observeCars();

        carRecyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) layoutManager) {
                                                @Override
                                                protected void loadMoreItems() {
                                                    currentPage += 1;
                                                    carViewModel.getCars(getApplicationContext(), currentPage);
                                                }

                                                @Override
                                                public boolean isLastPage() {
                                                    currentPage = 1;
                                                    carViewModel.getCars(getApplicationContext(), currentPage);
                                                    return true;
                                                }

                                                @Override
                                                public boolean isLoading() {
                                                    return false;
                                                }
                                            });

                Log.e("TAG", "onCreate: " + cars);

    }

    void observeCars() {
        carViewModel.getData().observe(this, new Observer<CarsResponseModel>() {
            @Override
            public void onChanged(CarsResponseModel carsResponseModel) {
                if (carsResponseModel.getStatus() == 1) {
                    if (swiperefresh.isRefreshing()) {
                        swiperefresh.setRefreshing(false);
                    }
                    cars = (ArrayList<DataItem>) carsResponseModel.getData();
                    carsAdapter.addCars(cars);
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        carsAdapter.clear();
        cars.clear();
        currentPage = 1;
        carViewModel.getCars(this, currentPage);
    }
}