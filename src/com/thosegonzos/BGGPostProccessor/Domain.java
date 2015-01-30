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

public class Domain {
	
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	HashMap<String,Integer> domains = new HashMap<String,Integer>();
		
	public Domain() 
	{
		// getTop10();
		buildDataset();
	}

	private void buildDataset() 
	{
		ArrayList<String> newStringList;

		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Category Domain.csv"));

			String strLine;

			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);

				newStringList = getDomainMatrix(strLine);

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
					bw.close();
				}
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}

	}

	private ArrayList<String> getDomainMatrix(String strLine) 
	{
		String addASpace = strLine.replaceAll(",,", ", ,");
		StringTokenizer st = new StringTokenizer(addASpace, ",");
		// String uglyRange = "";

		ArrayList<String> tokenList = new ArrayList<String>();
		ArrayList<String> domainList = new ArrayList<String>();

		boolean wargame = false;
		boolean strategy = false;
		boolean family = false;
		boolean abstractGames = false;
		boolean thematic = false;
		boolean childrens = false;
		boolean party = false;
		boolean customizable = false;
		
		/*
		key: Customizable Games value: 224
		key: Party Games value: 311
		key: Children's Games value: 539
		key: Thematic Games value: 689
		key: Abstract Games value: 691
		key: Family Games value: 1223
		key: Strategy Games value: 1250
		key: Wargames value: 2149
		Count of categories: 8 Total Categories Used: 7076
		 */


		// System.out.println("*** Build Categories Columns ***");

		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
		{
			tokenList.add(st.nextToken());

		}

		String domainStr = tokenList.get(28);
		if (domainStr.contains("<("))
		{
			int startIndex = 0;
			int endIndex = 0;
			int end = 2;
			while (true) 
			{
				int start = domainStr.indexOf("<(", startIndex);
				if (start != -1) 
				{
					end = domainStr.indexOf(")>", endIndex);
					String dom = domainStr.substring(start + 2, end);
					// System.out.println("(" + cat + ")");
					// System.out.println("+");
					domainList.add(dom);
				}
				if (start == -1) 
				{
					break;
				}
				startIndex = start + 2;  // move start up for next iteration
				endIndex = end + 2;
			}

			for (String d : domainList)
			{
				// System.out.println("(" + c + ")");
				if (d.equals("Wargames"))
				{
					wargame = true;
				}
				if (d.equals("Strategy Games"))
				{
					strategy = true;
				}
				if (d.equals("Family Games"))
				{
					family = true;
				}
				if (d.equals("Abstract Games"))
				{
					abstractGames = true;
				}
				if (d.equals("Thematic Games"))
				{
					thematic = true;
				}
				if (d.equals("Children's Games"))
				{
					childrens = true;
				}
				if (d.equals("Party Games"))
				{
					party = true;
				}
				if (d.equals("Customizable Games"))
				{
					customizable = true;
				}
			}

			if (wargame)
			{
				tokenList.add(27, "True");
			}
			else
			{
				tokenList.add(27, "False");
			}
			if (strategy)
			{
				tokenList.add(28, "True");
			}
			else
			{
				tokenList.add(28, "False");
			}
			if (family)
			{
				tokenList.add(29, "True");
			}
			else
			{
				tokenList.add(29, "False");
			}	
			if (abstractGames)
			{
				tokenList.add(30, "True");
			}
			else
			{
				tokenList.add(30, "False");
			}		
			if (thematic)
			{
				tokenList.add(31, "True");
			}
			else
			{
				tokenList.add(31, "False");
			}	
			if (childrens)
			{
				tokenList.add(32, "True");
			}
			else
			{
				tokenList.add(32, "False");
			}	
			if (party)
			{
				tokenList.add(33, "True");
			}
			else
			{
				tokenList.add(33, "False");
			}	
			if (customizable)
			{
				tokenList.add(34, "True");
			}
			else
			{
				tokenList.add(34, "False");
			}	
		}
		else
		{
			tokenList.add(27, "False");
			tokenList.add(28, "False");
			tokenList.add(29, "False");
			tokenList.add(30, "False");
			tokenList.add(31, "False");
			tokenList.add(32, "False");
			tokenList.add(33, "False");
			tokenList.add(34, "False");
		}
		return tokenList;
	}



	private void getTop10() 
	{		
		HashMap<String,Integer> domainList = null;;

		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));
			// bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));

			String strLine;

			while ((strLine = br.readLine()) != null)   
			{

				domainList = getDomains(strLine);

			}

			int accum = 0;

			Map<String, Integer> map = sortByValues(domainList); 
			for (String m : map.keySet())
			{
				System.out.println("key: " + m + " value: " + map.get(m));
				accum = accum + map.get(m);
	
			}

			System.out.println("Count of categories: " + map.size() + " Total Categories Used: " + accum);
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

	
	private HashMap<String,Integer> getDomains(String strLine) 
	{
		String addASpace = strLine.replaceAll(",,", ", ,");
		StringTokenizer st = new StringTokenizer(addASpace, ",");

		ArrayList<String> tokenList = new ArrayList<String>();

		while(st.hasMoreTokens())
		{
			tokenList.add(st.nextToken());
		}
		String domainList = "";
		if (tokenList.size() >= 29)
		{
			domainList = tokenList.get(28);
		}
		
		if (domainList.contains("<("))
    	{
			int startIndex = 0;
			int endIndex = 0;
			int end = 2;
			while (true) 
			{
				int start = domainList.indexOf("<(", startIndex);
				if (start != -1) 
				{
					end = domainList.indexOf(")>", endIndex);
					String d = domainList.substring(start + 2, end);

					if (domains.containsKey(d))
					{
						int count = domains.get(d);
						count++;
						domains.put(d, count);
					}
					else
					{
						domains.put(d, 1);
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

		return domains;
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