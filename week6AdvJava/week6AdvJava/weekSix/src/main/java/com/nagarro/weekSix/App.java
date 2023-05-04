package com.nagarro.weekSix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class App
{
String gender, sortBy, color, size;

	
	ArrayList<Products> prodList = new ArrayList<Products>();
	
	App(String gender, String color,String size, String sortBy)
	{
		this.gender=gender.toUpperCase();
		this.sortBy=sortBy.toUpperCase();
		this.color=color.toUpperCase();
		this.size=size.toUpperCase();
	}
	
	public void sortByRate()
	{
		Collections.sort(prodList, new Comparator<Products>() {
        	public int compare(Products p1, Products p2)
        	{
        		return Double.compare(p1.ratings,p2.ratings);
        	}
        });
	}
	public void sortByPrice()
	{
		Collections.sort(prodList, new Comparator<Products>() {
        	public int compare(Products p1, Products p2)
        	{
        		return Double.compare(p1.price,p2.price);
        	}
        });
	}
	public void printList()
	{
		if(prodList.size()==0)
		{
			System.out.println("No match found.");
		}
		else
		{
			for(int len = 0;len<prodList.size();len++)
	        {
	        	System.out.println("Name: "+prodList.get(len).Name);
	        	System.out.println("Gender: "+prodList.get(len).gender);
	        	System.out.println("Size: "+prodList.get(len).size);
	        	System.out.println("Price: "+prodList.get(len).price);
	        	System.out.println("Ratings: "+prodList.get(len).ratings);
	        	System.out.println("Availability: "+prodList.get(len).availability);
	        	System.out.println("-----------------------------------------------");
	        }
		}
		

	}
    public void GetData( ) throws IOException
    {
    	Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , "root", "root");
			try {
				Statement st = con.createStatement();
				String selectSQL="select * from nike;";
				ResultSet res = st.executeQuery(selectSQL);
				System.out.println(res);
				
				
				{
					
					while (res.next()) {
						String data_id = res.getString("id").toUpperCase();
						String data_name = res.getString("name").toUpperCase();
						String data_gender = res.getString("GENDER_RECOMMENDATION").toUpperCase();
						String data_size = res.getString("size").toUpperCase();
						String data_color = res.getString("colour").toUpperCase();
						String data_price = res.getString("price").toUpperCase();
						String data_rating = res.getString("rating").toUpperCase();
						String data_availability = res.getString("availability").toUpperCase();
						
						if(color.equals(data_color) && size.equals(data_size) && gender.equals(data_gender))
						{
							Products p1 = new Products(data_id, data_name, data_gender, data_color, data_size, data_price, data_rating, data_availability);	
							prodList.add(p1);
						}
					}
					
			}
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed!");
	}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();	
		}
 
		if(sortBy.toUpperCase().equals("R"))
		{
			sortByRate();
		}
		else if(sortBy.toUpperCase().equals("P"))
		{
			sortByPrice();
		}
		else if(sortBy.toUpperCase().equals("P+R"))
		{
			sortByRate();
			sortByPrice();
		}
		else
		{
			System.out.println("Invalid Preference, Showing without any sortings: ");
		}
	}
	
}
