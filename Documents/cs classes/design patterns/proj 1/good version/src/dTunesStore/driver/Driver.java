package dTunesStore.driver;

import dTunesStore.util.Debug;
import dTunesStore.util.Results;
import dTunesStore.dataStore.FileReaderHelper;
import dTunesStore.dataStore.PopulateWorker;
import dTunesStore.dataStore.SearchWorker;
import dTunesStore.dataStore.MusicStore;
import java.util.Vector;
public class Driver 
{

    public static void main(String args[]) 
    {

	  String dataFile = "";
	  int NN = 0;
	  String searchFileName = "";
      	  int MM = 0;
	  String DEBUG_VALUE = "";

	  try
	  {
		  dataFile = args[0];
		  NN = Integer.parseInt(args[1]);
		  searchFileName = args[2];
		  MM = Integer.parseInt(args[3]);
		  DEBUG_VALUE= args[4];
	  }
	  catch(Exception e)
	  {
		  if(e instanceof ArrayIndexOutOfBoundsException)
		  {
		  		System.out.println("Exception thrown :" + e);
		  }
          	  else
          	  {
    			System.out.println("Exception thrown:" + e);
          	  }
	}


	FileReaderHelper frh1 = new FileReaderHelper(dataFile);
	PopulateWorker pw = new PopulateWorker(NN, frh1);
	pw.startThreads();
	MusicStore ms = new MusicStore();
	ms = pw.getMusicStore();
	FileReaderHelper frh2 = new FileReaderHelper(searchFileName);
	Results r = new Results();
	SearchWorker sw = new SearchWorker(MM, ms, frh2, r);
	sw.startThreads();
	r = sw.getResults();
	r.printResults();
	
    } // end main(...)
} // end class Driver
