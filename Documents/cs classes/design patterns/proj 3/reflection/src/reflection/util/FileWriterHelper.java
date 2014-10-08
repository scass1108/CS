package reflection.util;

import java.io.PrintWriter;
import java.lang.InterruptedException;
import java.io.IOException;
import reflection.util.Debug;
/**
*Helper method to read file 
*used to help make sure threads are reading from same file
*/
public class FileWriterHelper
{
	PrintWriter out;

/**
*Constructor that takes in a filename as a string and opens the file using 
*PrintWriter
*@param s   string containing filename to be opened
*/
	public FileWriterHelper(String outputfilename)
	{
		try
		{
			out = new PrintWriter(outputfilename);
		}
		catch(IOException e)
		{
			System.out.println("Bad filename");
			System.exit(1);
		}
		if(Debug.getDebug() == 4){
			System.out.println("Filereader constructor called");
		}
	}
/**
*method used to write a single line to a file
*/
	public void write(String s)
	{
		out.println(s);
		out.flush();			
	}
	public void close()
	{
		out.close();
	}
}	
