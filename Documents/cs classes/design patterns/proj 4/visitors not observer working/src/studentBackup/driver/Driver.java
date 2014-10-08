package studentBackup.driver;
import studentBackup.util.BSTBuilder;


public class Driver{
    public static void main(String args[]) 
    {	
	String infile = "";
	String UPDATE_VALUE = "";
	try
	{			
	 	infile = args[0];
	 	UPDATE_VALUE = args[1];
	}
	 	catch(Exception e)
	{
		System.out.println("error, wrong num of args");
		System.exit(2);
	}

	BSTBuilder bstb = new BSTBuilder();
	bstb.buildTrees(infile);
    	bstb.printTrees();
    	System.out.println(" ");
    	bstb.sumTrees();
    	System.out.println(" ");
    	bstb.updateTrees(UPDATE_VALUE);
	bstb.printTrees();
	System.out.println(" ");
    	bstb.sumTrees();
    	
    }
}
