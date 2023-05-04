package com.nagarro.weekSix;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException
	{
		while(true)
		{
			System.out.println();
			String gender, sortBy, color, size;
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Gender: ");
			gender=sc.nextLine();
			if(gender.toUpperCase().equals("M")==false
				&&	gender.toUpperCase().equals("F")==false
				&&	gender.toUpperCase().equals("U")==false)
			{
				System.out.println("Please select gender from given values");
				break;
			}
	    	System.out.println("Enter color: ");
	    	color=sc.nextLine();
	    	System.out.println("Enter Size(S|M|L|XL|XXL): ");
	    	size=sc.nextLine();
	    	if(size.toUpperCase().equals("S")==false
					&&	size.toUpperCase().equals("M")==false
					&&	size.toUpperCase().equals("L")==false
					&&	size.toUpperCase().equals("XL")==false
					&&	size.toUpperCase().equals("XXL")==false)
				{
	    		System.out.println("Please select an appropriate size.");
					break;
				}
	    	System.out.println("Enter Sorting Preference(P|R|P+R): ");
	    	sortBy=sc.nextLine();
	    	App obj = new App(gender, color, size, sortBy);
	    	obj.GetData();
	    	obj.printList();
	    	System.out.println("Would you like to continue: (Y/N)");
	    	char ch = sc.nextLine().charAt(0);
	    	if(ch=='Y'||ch=='y')
	    	{
	    		continue;
	    	}
	    	else
	    	{
	    		break;
	    	}
		}
		
	}
	
}
