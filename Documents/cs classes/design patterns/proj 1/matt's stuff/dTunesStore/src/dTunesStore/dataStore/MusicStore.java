package dTunesStore.dataStore;

import dTunesStore.util.Debug;
import dTunesStore.util.Results;
import java.util.Hashtable; //for Hash table data structure

public class MusicStore {
	Hashtable<String, MusicInfo> musicStorage = new Hashtable<String, MusicInfo>();
	
	//When adding MusicInfo objects, need to add each attribute as an individual key
	//We do not use duration because we are not searching by duration.
	public void addMusicInfo(MusicInfo Obj){
		musicStorage.put(Obj.songName, Obj);
		musicStorage.put(Obj.albumName, Obj);
		musicStorage.put(Obj.singerName, Obj);
	}

	public void displayData(){
		for(String key : musicStorage.keySet()){
			System.out.println("Song Name: " + (musicStorage.get(key)).songName);
			System.out.println("Album Name: " + (musicStorage.get(key)).albumName);
			System.out.println("Singer Name: " + (musicStorage.get(key)).singerName);
 			System.out.println("Duration: " + (musicStorage.get(key)).duration);
		}
	}
} // end class MusicStore

