
package dTunesStore.dataStore;

import dTunesStore.util.Debug;
import dTunesStore.util.Results;
import java.util.Hashtable; //for Hash table data structure
import java.util.List;
import java.util.ArrayList;


public class MusicStore {
	Hashtable<String, List<MusicInfo>> musicStorage = new Hashtable<String, List<MusicInfo>>();
	
	//When adding MusicInfo objects, need to add each attribute as an individual key
	//We do not use duration as a key because we are not searching by duration.
	public synchronized void addMusicInfo(String key,MusicInfo Obj){
		List<MusicInfo> list;
		if(musicStorage.containsKey(key)){
			list = musicStorage.get(key);
			list.add(Obj);
		} else{
			list = new ArrayList<MusicInfo>();
			list.add(Obj);
			musicStorage.put(key, list);
		}
	}

	public void displayData(){
		for(String key : musicStorage.keySet()){
			for(MusicInfo info : musicStorage.get(key)){
				System.out.println("Song Name: " + info.songName);
				System.out.println("Album Name: " + info.albumName);
				System.out.println("Singer Name: " + info.singerName);
	 			System.out.println("Duration: " + info.duration);
			}
		}
	}
	public MusicInfo getMusicInfo(String s)
	{
		if(musicStorage.containsKey(s))
		{
			return musicStorage.get(s).get(0);
		}
		return null;
	}

} // end class MusicStore

