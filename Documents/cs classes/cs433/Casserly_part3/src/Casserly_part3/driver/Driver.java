package Casserly_part3.driver;

import java.io.*;
import java.util.*;
import Casserly_part3.util.FileReaderHelper;
import Casserly_part3.util.HashStruct;
import Casserly_part3.util.DictPair;
import Casserly_part3.util.Processor;
import Casserly_part3.util.DocTF;

public class Driver{
    public static void main(String args[]) 
    {	
   
	String queries = "";
	String documents = "";
	try
	{			
	 	queries = args[0];
		documents = args[1];
	}
	catch(Exception e)
	{
		System.out.println("error, wrong num of args");
		System.exit(2);
	}
	
	Processor p = new Processor();
	
	p.constructDictionaryAndPostings(documents);
	
	
	
	String dictionary = "dictionary.txt";
	String posting = "postings.txt";
	
	
	
	HashStruct hs = new HashStruct(dictionary);
	hs.populateDictionary();

	

	
	p.populatePostings(posting);

	p.readQueries(queries);
    	
    	p.populateDocLengths(documents);
    	
    	p.getTopRankings(hs);
    	
    }
}
