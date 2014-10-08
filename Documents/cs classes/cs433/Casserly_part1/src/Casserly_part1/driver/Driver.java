package Casserly_part1.driver;

import java.io.*;
import java.util.*;
import Casserly_part1.util.FileReaderHelper;
import Casserly_part1.util.FileWriterHelper;
import Casserly_part1.util.Processor;
import Casserly_part1.util.TDPair;
import Casserly_part1.util.Triple;
public class Driver{
    public static void main(String args[]) 
    {	
	String infile = "";
	String dictionary = "";
	String posting = "";
	try
	{			
	 	infile = args[0];
		dictionary = args[1];
		posting = args[2];
	}
	catch(Exception e)
	{
		System.out.println("error, wrong num of args");
		System.exit(2);
	}
	FileReaderHelper frh = new FileReaderHelper(infile);
	FileWriterHelper fwh = new FileWriterHelper(dictionary);
	FileWriterHelper fwh2 = new FileWriterHelper(posting);
	String s = "";
	Processor p = new Processor();
	
	List<TDPair> arr = new ArrayList<TDPair>();
	for(int i = 0; ((s = frh.read()) != null); i++)
    	{
    		ArrayList<TDPair> temp = new ArrayList<TDPair>();
    		//System.out.println(i + " " + s);
    		//fwh.write(s);
    		temp = p.process(s,i);
    		for(int k = 0; k < temp.size(); k++)
    		{
    			TDPair pairTemp = new TDPair();
    			pairTemp = temp.get(k);
    			arr.add(pairTemp);
    		}
    	}
    	Collections.sort(arr);
   	
    	ArrayList<Triple> dict = new ArrayList<Triple>();
    	for(int a = 0; a < arr.size(); a++)
    	{	
    		String termA = arr.get(a).getTerm();
    		int docIDA = arr.get(a).getDocID();
  		int contains = 0;
  		
    		for(int c = 0; c < dict.size(); c++)
    		{
    			String dictTerm = dict.get(c).getTerm();
    			if(dictTerm.equals(termA))
    			{
    				
    				contains = c;
    			}
    		}	

    		if(contains == 0)
    		{
    			ArrayList<Integer> l = new ArrayList<Integer>();
    			l.add(docIDA);
    			dict.add(new Triple(termA, 1, l));
    		}
    		else
    		{
    			ArrayList<Integer> tempPosting = dict.get(contains).getPostingList();
    			String tempTerm = dict.get(contains).getTerm();
    			int tempDF = dict.get(contains).getDf();
    			tempDF++;
    			tempPosting.add(docIDA);
    			dict.set(contains,new Triple(tempTerm, tempDF, tempPosting));

    		}
    		
    	}
        int offsetAccumulator = 0;			
    	for(int z = 0; z < dict.size(); z++)
    	{
    		fwh.write(dict.get(z).getTerm() + " " + dict.get(z).getDf() + " " + offsetAccumulator);
    		offsetAccumulator += dict.get(z).getDf();
    		
    	}
    	
    	for(int z1 = 0; z1 < dict.size(); z1++)
    	{
    		ArrayList<Integer> tp = dict.get(z1).getPostingList();
    		for(int z2 = 0; z2 < tp.size(); z2++)
    		{
    			fwh2.write(tp.get(z2) + " " + "1");
    		}
    	}
    	
    	fwh.close();
    	fwh2.close();
}
}
