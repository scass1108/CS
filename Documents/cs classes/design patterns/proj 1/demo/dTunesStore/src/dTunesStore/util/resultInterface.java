/**Interface for the Results class,
 * demonstrates necessary methods to be implemented
 */

package dTunesStore.util;

import dTunesStore.dataStore.MusicInfo;

public interface resultInterface{
	//Method for adding results to the vector in Results.java
	void addResult(MusicInfo obj);
	//Method for displaying contents of the vector to stdout
	void printResults();
}
