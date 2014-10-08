package Casserly_part3.util;
import java.io.*;
import java.util.*;
import Casserly_part3.util.FileReaderHelper;
import Casserly_part3.util.DictPair;
public class HashStruct{

	String filename;
	Hashtable <String, DictPair> dictionary;
//hashtable constructor
	public HashStruct(String filename)
	{
		this.filename = filename;
		dictionary = new Hashtable<String, DictPair>();
	}

// reads in file, parses line by line and puts term as key
// and value as a term frequency/posting list location pair
	public void populateDictionary()
	{
		FileReaderHelper frh = new FileReaderHelper(filename);
		
		String s = "";
		for(int i = 0; ((s = frh.read()) != null); i++)
		{
			String term;
			int tf;
			int posting;
			String[] arr = s.split("\\s+");
			
			term = arr[0];
			tf = (int) Integer.parseInt(arr[1]);
			posting = (int) Integer.parseInt(arr[2]);
			DictPair dp = new DictPair(tf, posting);
			
			dictionary.put(term, dp);
		}
	}
	
	public String getFilename()
	{
		return filename;
	}
	public Hashtable<String, DictPair> getDictionary()
	{
		return dictionary;
	}
	
	public void printDictionary()
	{
		System.out.println(dictionary.toString());
	}
	
	public int getPosting(String term)
	{
		DictPair dp = dictionary.get(term);
		return dp.getPosting();
	}
}
