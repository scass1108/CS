/**Interface for the product from the builder
*/

package studentOrientation.util;

public interface ActivityBuilder{

	//Set the activity's effort value
	public void setActivityEffort(double value);
	//Set the activity's money value
	public void setActivityMoney(double value);
	//Set the activity's time value
	public void setActivityTime(double value);
	//getter for effort value
	public double getEffort();
	//getter for money value
	public double getMoney();
	//getter for time value
	public double getTime();
}
