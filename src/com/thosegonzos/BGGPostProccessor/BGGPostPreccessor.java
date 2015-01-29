package com.thosegonzos.BGGPostProccessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BGGPostPreccessor {

	public static void main(String[] args) 
	{
		// changeAgeRange();
		// fixBestWith();
		// cleanMinAge();
		Category d = new Category();
		
	}
	
	static void changeAgeRange()
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		ArrayList<String> newStringList;
		
		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit.csv"));
			
			String strLine;
			
			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);
				
				newStringList = getAgeRange(strLine);
				
				// String newLine = newStringList.toString(); 
				StringBuilder sb = new StringBuilder();
				for(String newLine : newStringList)
				{
		            sb.append(newLine).append(", "); 
		        }
				
				sb.setLength(sb.length() - 2);
				
				// System.out.println(newLine);
				
				bw.write(sb.toString());
				bw.newLine();
				
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (br != null)
				{
					br.close();
					bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	static ArrayList<String> getAgeRange(String strLine)
	{
		StringTokenizer st = new StringTokenizer(strLine, ",");
		// String uglyRange = "";
		String minAge;
		String maxAge;
		ArrayList<String> tokenList = new ArrayList<String>();
		
		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
        {
                // tokenNumber++;

                tokenList.add(st.nextToken());
                /*
                if (tokenNumber == 8)
                {
                	uglyRange = tokenList.get(7);
                	// System.out.println("Token # " + tokenNumber + ", Token : " + uglyRange);
                }
                */
        }
    	
		if (tokenList.get(7).contains("?"))
    	{
    		int dividerIndex = tokenList.get(7).indexOf("?");
    		// int tokenSize = tokenList.get(7).length();
    		
    		minAge = tokenList.get(7).substring(0, dividerIndex - 1);
    		maxAge = tokenList.get(7).substring(dividerIndex + 1, tokenList.get(7).length());
    		
    		// System.out.println("(" + minAge + ") (" + maxAge + ")");
    		// System.out.println("Token (" + uglyRange + ")\n'?' is at: " + dividerIndex + ", Size: " + tokenSize);
    		// System.out.println("Token # " + tokenNumber + ", Token : " + uglyRange);
    	}
    	else
    	{
    		minAge = tokenList.get(7);
    		maxAge = tokenList.get(7);
    	}
    	
    	// System.out.println("> " + tokenList);
    	tokenList.set(7, minAge);
    	tokenList.add(8, maxAge);
    	// System.out.println("> " + tokenList);
    	/*
    	if (!tokenList.get(9).contains("Best"))
    	{
    		System.out.println(">" + tokenList.get(9));
    	}
    	*/
    	
    	
    	return tokenList;
	}
	
	
	static void fixBestWith()
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		ArrayList<String> newStringList;
		
		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith.csv"));
			
		
			String strLine;
			
			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);
				
				newStringList = getBestWith(strLine);
			
				StringBuilder sb = new StringBuilder();
				for(String newLine : newStringList)
				{
		            sb.append(newLine).append(", "); 
		        }
				
				sb.setLength(sb.length() - 2);
				
				// System.out.println(newLine);
				
				bw.write(sb.toString());
				bw.newLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (br != null)
				{
					br.close();
					bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	static ArrayList<String> getBestWith(String strLine)
	{	
		StringTokenizer st = new StringTokenizer(strLine, ",");
		String bestSize;

		ArrayList<String> tokenList = new ArrayList<String>();
		
		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
        {
                // tokenNumber++;

                tokenList.add(st.nextToken());
                /*
                if (tokenNumber == 8)
                {
                	uglyRange = tokenList.get(7);
                	// System.out.println("Token # " + tokenNumber + ", Token : " + uglyRange);
                }
                */
        }
    	
		if (tokenList.get(9).contains("Best with more"))
    	{
    		int startIndex = tokenList.get(9).indexOf("Best with more");
    		
    		bestSize = tokenList.get(9).substring(startIndex + 19, startIndex + 21);
    		
    		// System.out.println("(" + tokenList.get(9) + ")");
    		// System.out.println("(" + bestSize + ")");
    	}
		else if (tokenList.get(9).contains("Best with"))
		{
    		int startIndex = tokenList.get(9).indexOf("Best with");
    		
    		bestSize = tokenList.get(9).substring(startIndex + 9, startIndex + 11);
    		
    		// System.out.println("(" + tokenList.get(9) + ")");
    		// System.out.println("(" + bestSize + ")");
		}
		else
		{
			bestSize = "?";
		}

		if (!isNumeric(bestSize.replaceAll(" ", "")) && !bestSize.contains("?"))
		{
			System.out.println("ERROR: Best with... is not numeric: " + bestSize);
		}
		
    	tokenList.set(9, bestSize);
		
		return tokenList;
	}
	
	static void cleanMinAge()
	{
		BufferedReader br = null;
		BufferedWriter bw = null;
		ArrayList<String> newStringList;
		
		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge.csv"));
			
		
			String strLine;
			
			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);
				
				newStringList = getMinAge(strLine);
			
				StringBuilder sb = new StringBuilder();
				for(String newLine : newStringList)
				{
		            sb.append(newLine).append(", "); 
		        }
				
				sb.setLength(sb.length() - 2);
				
				// System.out.println(newLine);
				
				bw.write(sb.toString());
				bw.newLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (br != null)
				{
					br.close();
					bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	static ArrayList<String> getMinAge(String strLine)
	{	
		StringTokenizer st = new StringTokenizer(strLine, ",");
		String minAge = "";

		ArrayList<String> tokenList = new ArrayList<String>();
		
		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
        {
                tokenList.add(st.nextToken());
        }
    	
		if (tokenList.get(10).contains("and up"))
    	{
    		// int startIndex = tokenList.get(10).indexOf("Best with more");
    		
    		minAge = tokenList.get(10).substring(2, 5);
    		System.out.println("(" + tokenList.get(10) + ") " + "(" + minAge +")");
    		
    		tokenList.set(10, minAge);
    		
    		// System.out.println("(" + tokenList.get(9) + ")");
    		// System.out.println("(" + bestSize + ")");
    	}
		else if (tokenList.get(10).contains("?"))
		{
			// Do nothing...
		}
		else
		{
			System.out.println("Unknown data: (" + tokenList.get(10) + ")");
		}

		if (!isNumeric(minAge.replaceAll(" ", "")) && !tokenList.get(10).contains("?"))
		{
			System.out.println("ERROR: Best with... is not numeric: " + minAge);
		}
		
		return tokenList;
	}
	
	
	
	
	
	public static boolean isNumeric(String input) 
	{
		  try 
		  {
		    Integer.parseInt(input);
		    return true;
		  }
		  catch (NumberFormatException e) 
		  {
		    return false;
		  }
		}
	
}
