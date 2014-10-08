/**Interface for time put into each activity
*/

package studentOrientation.util;

public interface TimeInterface{
	//getter for time value
	public double getTime();
	//setter for time value
	public void setTime(double value);
	//conversion for time currently in minutes
	public void convertTime(double conversionRate);

}
