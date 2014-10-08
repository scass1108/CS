package Casserly_part2.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.InterruptedException;
import java.io.IOException;

/**
*Helper method to read file 
*/
public class FileReaderHelper
{
	BufferedReader br;

/**
*Constructor that takes in a filename as a string and opens the file using 
*FileReader and BufferedReader
*@param s   string containing filename to be opened
*/
	public FileReaderHelper(String inputfilename)
	{
		try
		{
			br = new BufferedReader(new FileReader(inputfilename));
		}
		catch(IOException e)
		{
			System.out.println("Bad filename");
			System.exit(1);
		}
	}
/**
*method that will be accessed by threads so it is synchronized
*used to read a single line from a file and return that line
*@return   the line that is read from the file
*/
	public String read()
	{	
		String s = null;
		try
    		{	
		    	if((s = br.readLine()) != null)
		    	{
		    		return s;
		    	}

		    	br.close();
		}
		catch(IOException e)
		{
			
		}
		
		return s;
	}
}
