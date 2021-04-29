package com.example.sofxpertapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sofxpertapp.models.DataItem;

import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private ArrayList<DataItem> carList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView carImage;
        final TextView carBrand, carUsability, carYear;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            carImage = view.findViewById(R.id.carImage);
            carBrand = view.findViewById(R.id.carBrand);
            carUsability = view.findViewById(R.id.carUsability);
            carYear = view.findViewById(R.id.carYear);
        }
    }

    public CarsAdapter(Context context, ArrayList<DataItem> dataSet) {
        carList = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.car_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.carBrand.setText(carList.get(position).getBrand());
        viewHolder.carYear.setText(carList.get(position).getConstractionYear());
        if (carList.get(position).isIsUsed()) {
            viewHolder.carUsability.setText("Used");
        } else {
            viewHolder.carUsability.setText("New");
        }
        Glide.with(context).load(carList.get(position).getImageUrl()).into(viewHolder.carImage);
    }

    public void addCars(ArrayList<DataItem> cars) {
        carList.addAll(cars);
        notifyDataSetChanged();
    }

    public void clear() {
        carList.clear();
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return carList.size();
    }
}
