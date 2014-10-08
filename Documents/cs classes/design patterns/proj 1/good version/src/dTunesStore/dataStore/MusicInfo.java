/**Defines MusicInfo objects that will
 * be stored in the datastructure in MusicStore.java 
 */

package dTunesStore.dataStore;

import dTunesStore.util.Debug;
import dTunesStore.util.Results;

public class MusicInfo {
	public String songName;
	public String albumName;
	public String singerName;
	public int duration;

	//Mutator for songName
	public void setSong(String song){
		this.songName = song;	
	}
	//Accessor for songName
	public String getSong(){
		return this.songName;
	}
	//Mutator for albumName
	public void setAlbum(String album){
		this.albumName = album;
	}
	//Accessor for albumName
	public String getAlbum(){
		return this.albumName;
	}
	//Mutator for singerName
	public void setSinger(String singer){
		this.singerName = singer;
	}
	//Accessor for singerName
	public String getSinger(){
		return this.singerName;
	}
	//Mutator for duration
	public void setDuration(int duration){
		this.duration = duration;
	}
	//Accessor for duration
	public int getDuration(){
		return duration;
	}

} // end class MusicInfo

