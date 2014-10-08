package Casserly_part2.driver;

import java.io.*;
import java.util.*;
import Casserly_part2.util.FileReaderHelper;
import Casserly_part2.util.HashStruct;
import Casserly_part2.util.DictPair;
import Casserly_part2.util.Processor;
import Casserly_part2.util.DocTF;

public class Driver{
    public static void main(String args[]) 
    {	
   
	String queries = "";
	String dictionary = "";
	String posting = "";
	String documents = "";
	try
	{			
	 	queries = args[0];
		dictionary = args[1];
		posting = args[2];
		documents = args[3];
	}
	catch(Exception e)
	{
		System.out.println("error, wrong num of args");
		System.exit(2);
	}
	
	HashStruct hs = new HashStruct(dictionary);
	hs.populateDictionary();
//	hs.printDictionary();
	
	Processor p = new Processor();
	p.populatePostings(posting);
//	p.printPostings();

	p.readQueries(queries);
//    	p.getQueryPostings();
    	
    	p.populateDocLengths(documents);
    	
    	p.getTopRankings(hs);
    	
    }
}
