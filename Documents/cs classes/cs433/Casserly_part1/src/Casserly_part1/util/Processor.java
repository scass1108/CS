package Casserly_part1.util;
import java.io.*;
import java.util.*;
import Casserly_part1.util.Stemmer;
import Casserly_part1.util.TDPair;
public class Processor
{
//pass a string in and method will split it into individual words and remove any 
//non alphanumerics 
	public ArrayList<TDPair> process(String s, int n)
	{
//use String split method to split each line into words based on whitespace
		String[] arr = s.split("\\s+");
		ArrayList<TDPair> list = new ArrayList<TDPair>();
//send each word into loop, replace any non alphanumeric characters,
//and change all to lowercase	
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = arr[i].replaceAll("[^a-zA-Z0-9]","").toLowerCase();
		
//use porters stemmer		
			Stemmer stemmer = new Stemmer();
			char[] c = arr[i].toCharArray();
			stemmer.add(c, arr[i].length());
			stemmer.stem(); 
			String u;
			u = stemmer.toString();
			//System.out.println(u);
			TDPair p = new TDPair(u,n);
			list.add(p);
		}
//return list of stemmed words/docID pairs		
		return list;
	}

}
