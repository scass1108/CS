package reflection.driver;

import reflection.util.FileReaderHelper;
import reflection.util.FileWriterHelper;
import reflection.util.Debug;

public class Driver{
    public static void main(String args[]) 
    {	
	String infile = "";
	String outfile = "";
	String debugValue = "";
	
	try
	{			
	 	infile = args[0];
		outfile = args[1];
		Debug.setDebug(Integer.parseInt(args[2]));
	}
	catch(Exception e)
	{
		System.out.println("error, wrong num of args");
		System.exit(2);
	}
	
	FileReaderHelper frh = new FileReaderHelper(infile);
	FileWriterHelper fwh = new FileWriterHelper(outfile);
	String s = "";

	while((s = frh.read()) != null)
    	{
    		System.out.println(s);
    		fwh.write(s);
    	}
    	fwh.close();

    }
}
