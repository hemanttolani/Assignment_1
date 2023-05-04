package com.advJava.week5;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import au.com.bytecode.opencsv.CSVReader;

public class scriptClass 
{
	
	String gender, sortBy, color, size;
	File path = new File("src/data");
	
	ArrayList<Products> prodList = new ArrayList<Products>();
	
	scriptClass(String gender, String color,String size, String sortBy)
	{
		this.gender=gender;
		this.sortBy=sortBy;
		this.color=color;
		this.size=size;
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
    	File fileList[] = path.listFiles();
    	
		for(int i=0;i<fileList.length;i++)
		{
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(fileList[i]), ',' , '"' , 1);
	        
	        //Read CSV line by line and using string array
	        String[] nextLine;
	        while ((nextLine = reader.readNext()) != null) {
	           String[] contents = nextLine[0].split("\\|");
	           if(color.toUpperCase().equals(contents[2].toUpperCase()) 
	        		   && size.toUpperCase().equals(contents[4].toUpperCase()) 
	        		   && gender.toUpperCase().equals(contents[3].toUpperCase()))
	           {
	        	   Products p1= new Products(contents[1],contents[3],contents[2],
	        			   contents[4],contents[5],contents[6], contents[7]);
	        	   prodList.add(p1);
	           }
	         }
	        System.out.println();
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
