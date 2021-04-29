package com.example.sofxpertapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CarsResponseModel{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private int status;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}