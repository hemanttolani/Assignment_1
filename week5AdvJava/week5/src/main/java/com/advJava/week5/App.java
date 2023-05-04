package com.advJava.week5;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String g, sort, c, len;
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Gender: ");
    	g=sc.nextLine();
    	System.out.println("Enter color: ");
    	c=sc.nextLine();
    	System.out.println("Enter Size(S|M|L|XL|XXL): ");
		
    	len=sc.nextLine();
    	System.out.println("Enter Sorting Preference(P|R|P+R): ");
    	sort=sc.nextLine();
    	scriptClass ob = new scriptClass(g, c, len, sort);
    	ob.GetData();
    	ob.printList();
    	
    }
}
