/** Result class that has the datastructure for storing
 * matches in the MusicStore datastrucutre and terms in the
 * search info file
 */

package dTunesStore.util;

import dTunesStore.util.Debug;
import java.util.Vector;
import dTunesStore.dataStore.MusicInfo;
public class Results implements resultInterface {

	Vector<MusicInfo> resultVector = new Vector<MusicInfo>();

	public Results(){
		if(Debug.getDebug() == 4){
			System.out.println("Results constructor called");
		}
	}
	
	/**Method to add a matched result to the Result vector
	*param Obj the object to be added to the Result vector
	*/
	public synchronized void addResult(MusicInfo Obj){
		resultVector.add(Obj);
		if(Debug.getDebug() == 2){
			System.out.println("Entry added to Results vector");		
		}
		//System.out.println(resultVector.get(0).songName);
	}
	
	/**Method to print results for debugging,
	 *use this instead of the toString method
	 */
	public void printResults(){
		for(int i = 0; i < resultVector.size(); i++){
			MusicInfo temp = resultVector.get(i);
			System.out.println(temp.getSong() + " " + temp.getAlbum() + " " +
			temp.getSinger() + " " + temp.getDuration());		
		}
	}
}
