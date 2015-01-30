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

public class Mechanic {
	
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	HashMap<String,Integer> mechanics = new HashMap<String,Integer>();
		
	public Mechanic() 
	{
		// getTop10();
		buildDataset();
	}

	private void buildDataset() 
	{
		ArrayList<String> newStringList;

		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Category Domain.csv"));
			bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Category Domain Mechanic.csv"));

			String strLine;

			while ((strLine = br.readLine()) != null)   
			{
				// System.out.println (strLine);

				newStringList = getMechanicMatrix(strLine);

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

	private ArrayList<String> getMechanicMatrix(String strLine) 
	{
		String addASpace = strLine.replaceAll(",,", ", ,");
		StringTokenizer st = new StringTokenizer(addASpace, ",");
		// String uglyRange = "";

		ArrayList<String> tokenList = new ArrayList<String>();
		ArrayList<String> mechanicList = new ArrayList<String>();

		boolean dice = false;
		boolean handManagement = false;
		boolean hex = false;
		boolean collection = false;
		boolean variablePlayerPowers = false;
		boolean tile = false;
		boolean rollSpinMove = false;
		boolean modularBoard = false;
		boolean cardDrafting = false;
		boolean auction = false;
		boolean areaControl = false;
		boolean simulation = false;
		boolean areaMovement = false;
		boolean simultaneousAction = false;
		
		/*
		key: Simultaneous Action Selection value: 548
		key: Area Movement value: 556
		key: Simulation value: 567
		key: Area Control / Area Influence value: 593
		key: Auction/Bidding value: 649
		key: Card Drafting value: 655
		key: Modular Board value: 726
		key: Roll / Spin and Move value: 783
		key: Tile Placement value: 806
		key: Variable Player Powers value: 916
		key: Set Collection value: 1122
		key: Hex-and-Counter value: 1503
		key: Hand Management value: 1711
		key: Dice Rolling value: 2155
		*/


		// System.out.println("*** Build Categories Columns ***");

		// int tokenNumber = 0; 
		while(st.hasMoreTokens())
		{
			tokenList.add(st.nextToken());

		}

		String mechanicStr = tokenList.get(37);
		if (mechanicStr.contains("<("))
		{
			int startIndex = 0;
			int endIndex = 0;
			int end = 2;
			while (true) 
			{
				int start = mechanicStr.indexOf("<(", startIndex);
				if (start != -1) 
				{
					end = mechanicStr.indexOf(")>", endIndex);
					String dom = mechanicStr.substring(start + 2, end);
					// System.out.println("(" + cat + ")");
					// System.out.println("+");
					mechanicList.add(dom);
				}
				if (start == -1) 
				{
					break;
				}
				startIndex = start + 2;  // move start up for next iteration
				endIndex = end + 2;
			}

			for (String m : mechanicList)
			{
				// System.out.println("(" + c + ")");
				if (m.equals("Dice Rolling"))
				{
					dice = true;
				}
				if (m.equals("Hand Management"))
				{
					handManagement = true;
				}
				if (m.equals("Hex-and-Counter"))
				{
					hex = true;
				}
				if (m.equals("Set Collection"))
				{
					collection = true;
				}
				if (m.equals("Variable Player Powers"))
				{
					variablePlayerPowers = true;
				}
				if (m.equals("Tile Placement"))
				{
					tile = true;
				}
				if (m.equals("Roll / Spin and Move"))
				{
					rollSpinMove = true;
				}
				if (m.equals("Modular Board"))
				{
					modularBoard = true;
				}		
				if (m.equals("Card Drafting"))
				{
					cardDrafting = true;
				}
				if (m.equals("Auction/Bidding"))
				{
					auction = true;
				}
				if (m.equals("Area Control / Area Influence"))
				{
					areaControl = true;
				}
				if (m.equals("Simulation"))
				{
					simulation = true;
				}
				if (m.equals("Area Movement"))
				{
					areaMovement = true;
				}
				if (m.equals("Roll / Spin and Move"))
				{
					rollSpinMove = true;
				}
				if (m.equals("Simultaneous Action Selection"))
				{
					simultaneousAction = true;
				}	
			}
			if (dice)
			{
				tokenList.add(35, "True");
			}
			else
			{
				tokenList.add(35, "False");
			}
			if (handManagement)
			{
				tokenList.add(36, "True");
			}
			else
			{
				tokenList.add(36, "False");
			}
			if (hex)
			{
				tokenList.add(37, "True");
			}
			else
			{
				tokenList.add(37, "False");
			}	
			if (collection)
			{
				tokenList.add(38, "True");
			}
			else
			{
				tokenList.add(38, "False");
			}		
			if (variablePlayerPowers)
			{
				tokenList.add(39, "True");
			}
			else
			{
				tokenList.add(39, "False");
			}	
			if (tile)
			{
				tokenList.add(40, "True");
			}
			else
			{
				tokenList.add(40, "False");
			}	
			if (rollSpinMove)
			{
				tokenList.add(41, "True");
			}
			else
			{
				tokenList.add(41, "False");
			}	
			if (modularBoard)
			{
				tokenList.add(42, "True");
			}
			else
			{
				tokenList.add(42, "False");
			}	
			if (cardDrafting)
			{
				tokenList.add(43, "True");
			}
			else
			{
				tokenList.add(43, "False");
			}
			if (auction)
			{
				tokenList.add(44, "True");
			}
			else
			{
				tokenList.add(44, "False");
			}
			if (areaControl)
			{
				tokenList.add(45, "True");
			}
			else
			{
				tokenList.add(45, "False");
			}	
			if (simulation)
			{
				tokenList.add(46, "True");
			}
			else
			{
				tokenList.add(46, "False");
			}		
			if (areaMovement)
			{
				tokenList.add(47, "True");
			}
			else
			{
				tokenList.add(47, "False");
			}	
			if (simultaneousAction)
			{
				tokenList.add(48, "True");
			}
			else
			{
				tokenList.add(48, "False");
			}		
		}
		else
		{
			tokenList.add(35, "False");
			tokenList.add(36, "False");
			tokenList.add(37, "False");
			tokenList.add(38, "False");
			tokenList.add(39, "False");
			tokenList.add(40, "False");
			tokenList.add(41, "False");
			tokenList.add(42, "False");
			tokenList.add(43, "False");
			tokenList.add(44, "False");
			tokenList.add(45, "False");
			tokenList.add(46, "False");
			tokenList.add(47, "False");
			tokenList.add(48, "False");
		}
		return tokenList;
	}



	private void getTop10() 
	{		
		HashMap<String,Integer> mechanicList = null;;

		try
		{
			br = new BufferedReader(new FileReader("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Category Domain.csv"));
			// bw = new BufferedWriter(new FileWriter("data/BGGbFinal 2015 01 18 AgeSplit BestWith MinAge Domain.csv"));

			String strLine;

			while ((strLine = br.readLine()) != null)   
			{

				mechanicList = getMechanics(strLine);

			}

			int accum = 0;

			Map<String, Integer> map = sortByValues(mechanicList); 
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

	
	private HashMap<String,Integer> getMechanics(String strLine) 
	{
		String addASpace = strLine.replaceAll(",,", ", ,");
		StringTokenizer st = new StringTokenizer(addASpace, ",");

		ArrayList<String> tokenList = new ArrayList<String>();

		while(st.hasMoreTokens())
		{
			tokenList.add(st.nextToken());
		}
		String mechanicList = "";
		if (tokenList.size() >= 37)
		{
			mechanicList = tokenList.get(37);
		}
		
		if (mechanicList.contains("<("))
    	{
			int startIndex = 0;
			int endIndex = 0;
			int end = 2;
			while (true) 
			{
				int start = mechanicList.indexOf("<(", startIndex);
				if (start != -1) 
				{
					end = mechanicList.indexOf(")>", endIndex);
					String d = mechanicList.substring(start + 2, end);

					if (mechanics.containsKey(d))
					{
						int count = mechanics.get(d);
						count++;
						mechanics.put(d, count);
					}
					else
					{
						mechanics.put(d, 1);
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

		return mechanics;
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