package com.example.sofxpertapp.models;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("constractionYear")
	private String constractionYear;

	@SerializedName("brand")
	private String brand;

	@SerializedName("isUsed")
	private boolean isUsed;

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setConstractionYear(String constractionYear){
		this.constractionYear = constractionYear;
	}

	public String getConstractionYear(){
		return constractionYear;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setIsUsed(boolean isUsed){
		this.isUsed = isUsed;
	}

	public boolean isIsUsed(){
		return isUsed;
	}
}