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
	
	public MusicInfo(){
		if(Debug.getDebug() == 4){
			System.out.println("MusicInfo constructor called");
		}
	}

	/**Mutator for songName
	*@param song song to be set
	*/
	public void setSong(String song){
		this.songName = song;	
	}
	/**Accessor for songName
	*/
	public String getSong(){
		return this.songName;
	}
	/**Mutator for albumName
	*@param album album to be set
	*/
	public void setAlbum(String album){
		this.albumName = album;
	}
	
	/**Accessor for albumName
	*/
	public String getAlbum(){
		return this.albumName;
	}
	/**Mutator for singerName
	*@param singer singer to be set
	*/
	public void setSinger(String singer){
		this.singerName = singer;
	}
	/**Accessor for singerName
	*/
	public String getSinger(){
		return this.singerName;
	}
	/**Mutator for duration
	*param duration duration to be set
	*/
	public void setDuration(int duration){
		this.duration = duration;
	}
	/**Accessor for duration
	*/
	public int getDuration(){
		return duration;
	}

	/**toString method
	* used to override toString for debugging purposes
	*/
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Song Name: " + songName + "\n");
		result.append("Album Name: " + albumName + "\n");
		result.append("Singer Name: " + singerName + "\n");
		result.append("Duration: " + duration + "\n");
		return result.toString();
	}

} // end class MusicInfo

