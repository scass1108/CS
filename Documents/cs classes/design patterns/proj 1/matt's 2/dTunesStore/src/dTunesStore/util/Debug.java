/** Debug class used to store the DEBUG_VALUE
*/
package dTunesStore.util;

public class Debug {
    private static int DEBUG_VALUE;

    /**accessor for DEBUG_VALUE
	*/
	public static int getDebug(){
		return DEBUG_VALUE;	
	}

	/** mutator for DEBUG_VALUE
	*param value the value to set the debug to
	*/
	public static void setDebug(int value){
		DEBUG_VALUE = value;
	}
	

}
