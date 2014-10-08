
package dTunesStore.util;

import java.util.Vector;
import dTunesStore.dataStore.MusicInfo;
public class Results implements resultInterface {
	Vector<MusicInfo> resultVector = new Vector<MusicInfo>();

	public void addResult(MusicInfo Obj){
		resultVector.add(Obj);
		//System.out.println(resultVector.get(0).songName);
	}

	public void printResults(){
		for(int i = 0; i < resultVector.size(); i++){
			MusicInfo temp = resultVector.get(i);
			System.out.println(temp.getSong() + " " + temp.getAlbum() + " " +
			temp.getSinger() + " " + temp.getDuration());		
		}
	}
}
