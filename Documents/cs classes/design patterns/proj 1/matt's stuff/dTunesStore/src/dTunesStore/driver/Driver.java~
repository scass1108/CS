
package dTunesStore.driver;

import dTunesStore.util.Debug;
import dTunesStore.util.Results;
import dTunesStore.dataStore.PopulateWorker;
import dTunesStore.dataStore.SearchWorker;

//remove these later, this is for testing
import dTunesStore.dataStore.MusicInfo;
import dTunesStore.dataStore.MusicStore;

public class Driver {
    
	public static void main(String args[]) {
	  
	  String dataFile = "";
	  int NN = 0;
	  String searchFileName = "";
      int MM = 0;
	  String DEBUG_VALUE = "";

	  try{
		  dataFile = args[0];
		  NN = Integer.parseInt(args[1]);
		  searchFileName = args[2];
		  MM = Integer.parseInt(args[3]);
		  DEBUG_VALUE= args[4];
	  }
	  catch(Exception e){
		  if(e instanceof ArrayIndexOutOfBoundsException){
		  		System.out.println("Exception thrown :" + e);
		  }
          else{
    			System.out.println("Exception thrown:" + e);
          }
	  }
	  //Testing stuff below
	  /*
      System.out.println(dataFile);
	  System.out.println(NN);
	  System.out.println(searchFileName);
	  System.out.println(MM);
	  System.out.println(DEBUG_VALUE);
	  MusicStore dTunes = new MusicStore();
	  Results result = new Results();
	  for(int i = 0; i < 10; i++){
	  MusicInfo song1 = new MusicInfo();
	  	song1.albumName = "Album" + i;
		//System.out.println("song1 is: " + song1.albumName);
	  	song1.singerName = "Singer" + i;
	  	song1.duration = i;
	  	song1.songName= "Song" + i;
		dTunes.addMusicInfo(song1);
		//result.addResult(song1);
	  }
	  //dTunes.displayData();*/
	  
    } // end main(...)
} // end class Driver
