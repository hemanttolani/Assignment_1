package com.nagarro.weekSix;



public class Products{
	String id, Name,gender, color, size, availability;
	double price, ratings;
	
	Products(String id, String Name, String gender, String color, 
			String size, String price, String ratings, String availability)
	{
		this.id=id;
		this.Name=Name;
		this.gender= gender;
		this.color=color;
		this.size=size;
		this.price= Double.parseDouble(price);
		this.ratings=Double.parseDouble(ratings);
		this.availability=availability;
	}
}