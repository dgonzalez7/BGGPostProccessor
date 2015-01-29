package com.thosegonzos.BGGPostProccessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Category 
{
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	HashMap<String,Integer> categories = new HashMap<String,Integer>();
	
	
	public Category() 
	{
		getTop10();
		buildDataset();
	}

	private void buildDataset() 
	{
		ArrayList<String> newStringList;
		
		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));
			
			String strLine;
			
			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);
				
				// String strLine;
				
				while ((strLine = br.readLine()) != null)   
				{
					// System.out.println (strLine);
					
					newStringList = getCategoryMatrix(strLine);
					
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
			
			/*
			System.out.println("Size: " + categoryList.size());
			for (String c : categoryList.keySet())
    		{
    			System.out.println("key: " + c + " value: " + categoryList.get(c));

    		}
    		*/
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
					// bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		
	}
	
	private ArrayList<String> getCategoryMatrix(String strLine) 
	{
		StringTokenizer st = new StringTokenizer(strLine, ",");
		// String uglyRange = "";
		String minAge;
		String maxAge;
		ArrayList<String> tokenList = new ArrayList<String>();
		ArrayList<String> catList = new ArrayList<String>();
		
		boolean card = false;
		boolean wargame = false;
		boolean fantasy = false;
		boolean economic = false;
		boolean childrens = false;
		boolean fighting = false;
		boolean abstractStrategy = false;
		boolean sciFi = false;
		boolean wwii = false;
		boolean dice = false;
		boolean party = false;
		boolean bluffing = false;
		boolean animals = false;
		boolean humor = false;
		boolean dexterity = false;

		// System.out.println("*** Build Categories Columns ***");
		
		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
        {
                tokenList.add(st.nextToken());

        }
    	
		String categoryStr = tokenList.get(12);
		if (categoryStr.contains("<("))
    	{
    		int startIndex = 0;
    		int endIndex = 0;
    		int end = 2;
    		while (true) 
    		{
    			int start = categoryStr.indexOf("<(", startIndex);
    			if (start != -1) 
    			{
    				end = categoryStr.indexOf(")>", endIndex);
    	    		String cat = categoryStr.substring(start + 2, end);
    	    		// System.out.println("(" + cat + ")");
    	    		// System.out.println("+");
    	    		catList.add(cat);
    			}
    			if (start == -1) 
    				{
    					break;
    				}
    			startIndex = start + 2;  // move start up for next iteration
    			endIndex = end + 2;
    		}
			
			for (String c : catList)
			{
				// System.out.println("(" + c + ")");
				if (c.equals("Card Game"))
				{
					card = true;
				}
				if (c.equals("Wargame"))
				{
					wargame = true;
				}
				if (c.equals("Fantasy"))
				{
					fantasy = true;
				}
				if (c.equals("Econimic"))
				{
					economic = true;
				}
				if (c.equals("Children's Game"))
				{
					childrens = true;
				}
				if (c.equals("Fighting"))
				{
					fighting = true;
				}
				if (c.equals("Abstract Strategy"))
				{
					abstractStrategy = true;
				}
				if (c.equals("Abstract Strategy"))
				{
					abstractStrategy = true;
				}
				if (c.equals("Science Fiction"))
				{
					sciFi = true;
				}
				if (c.equals("World War II"))
				{
					wwii = true;
				}
				if (c.equals("Dice"))
				{
					dice = true;
				}
				if (c.equals("Party Game"))
				{
					party = true;
				}
				if (c.equals("Bluffing"))
				{
					bluffing = true;
				}
				if (c.equals("Animals"))
				{
					animals = true;
				}
				if (c.equals("Humor"))
				{
					humor = true;
				}
				if (c.equals("Action / Dexterity"))
				{
					dexterity = true;
				}
			}
			
			if (card)
			{
				tokenList.add(12, "True");
			}
			else
			{
				tokenList.add(12, "False");
			}
			
			
			/*
			if (catList.equals("Humor"))
			{
				System.out.println(tokenList.get(0) + "has Humor");
			}
			*/
			
			
			
			
			/*
			int dividerIndex = tokenList.get(7).indexOf("?");
    		// int tokenSize = tokenList.get(7).length();
    		
    		minAge = tokenList.get(7).substring(0, dividerIndex - 1);
    		maxAge = tokenList.get(7).substring(dividerIndex + 1, tokenList.get(7).length());
    		
    		// System.out.println("(" + minAge + ") (" + maxAge + ")");
    		// System.out.println("Token (" + uglyRange + ")\n'?' is at: " + dividerIndex + ", Size: " + tokenSize);
    		// System.out.println("Token # " + tokenNumber + ", Token : " + uglyRange);
    		 
    		 */
    	}
    	else
    	{
    		tokenList.add(12, "NO");
    		// minAge = tokenList.get(7);
    		// maxAge = tokenList.get(7);
    	}
    	
    	// System.out.println("> " + tokenList);
    	// tokenList.set(7, minAge);
    	// tokenList.add(8, maxAge);
    	// System.out.println("> " + tokenList);
    	/*
    	if (!tokenList.get(9).contains("Best"))
    	{
    		System.out.println(">" + tokenList.get(9));
    	}
    	*/
		
		
		
		
		return tokenList;
	}

	
	
	
	
	
	
	
	
	
	
	
	private void getTop10() 
	{		
		HashMap<String,Integer> categoryList = null;;
		
		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge.csv"));
			// bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));
			
			String strLine;
			
			while ((strLine = br.readLine()) != null)   
			{
				
				categoryList = getCategories(strLine);

			}
	
			int accum = 0;
			
			Map<String, Integer> map = sortByValues(categoryList); 
			for (String m : map.keySet())
    		{
    			// System.out.println("key: " + m + " value: " + map.get(m));
    			accum = accum + map.get(m);
    		}
			// System.out.println("Count of categories: " + map.size() + " Total Categories Used: " + accum);
			
			
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
					// bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
	}

	private HashMap<String,Integer> getCategories(String strLine) 
	{
		StringTokenizer st = new StringTokenizer(strLine, ",");
		
		
		
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
    	
		String categoryList = tokenList.get(12);
		if (categoryList.contains("<("))
    	{
    		/*
			start = categoryList.indexOf("<(");
    		end = categoryList.indexOf(")>");
    		String cat = categoryList.substring(start + 2, end);
    		// System.out.println("(" + cat + ")");
			
    		if (categories.containsKey(cat))
    		{
    			int count = categories.get(cat);
    			count++;
    			categories.put(cat, count);
    		}
    		else
    		{
    			categories.put(cat, 1);
    		}
    		*/
    		
    		int startIndex = 0;
    		int endIndex = 0;
    		int end = 2;
    		while (true) 
    		{
    			int start = categoryList.indexOf("<(", startIndex);
    			if (start != -1) 
    			{
    				end = categoryList.indexOf(")>", endIndex);
    	    		String cat = categoryList.substring(start + 2, end);
    	    		// System.out.println("(" + cat + ")");
    	    		// System.out.println("+");
    	    		
    	    		if (categories.containsKey(cat))
    	    		{
    	    			int count = categories.get(cat);
    	    			count++;
    	    			categories.put(cat, count);
    	    		}
    	    		else
    	    		{
    	    			categories.put(cat, 1);
    	    		}
    			}
    			if (start == -1) 
    				{
    					break;
    				}
    			startIndex = start + 2;  // move start up for next iteration
    			endIndex = end + 2;
    		}
    		// System.out.println("\n\n");
    			
    		
    		
			/*
			int dividerIndex = tokenList.get(7).indexOf("?");
    		// int tokenSize = tokenList.get(7).length();
    		
    		minAge = tokenList.get(7).substring(0, dividerIndex - 1);
    		maxAge = tokenList.get(7).substring(dividerIndex + 1, tokenList.get(7).length());
    		
    		// System.out.println("(" + minAge + ") (" + maxAge + ")");
    		// System.out.println("Token (" + uglyRange + ")\n'?' is at: " + dividerIndex + ", Size: " + tokenSize);
    		// System.out.println("Token # " + tokenNumber + ", Token : " + uglyRange);
    		 */
    	}
    	else
    	{
    		
    		// System.out.println("No categories! " + tokenList.get(0) + " " + tokenList.get(12));
    		
    		
    		// minAge = tokenList.get(7);
    		// maxAge = tokenList.get(7);
    	}
    	
    	// System.out.println("> " + tokenList);
    	// tokenList.set(7, minAge);
    	// tokenList.add(8, maxAge);
		
		return categories;
	}
	
	private static HashMap sortByValues(HashMap map) 
	{ 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
	
}
