/**Interface for cost of each activity
*/

package studentOrientation.util;

public interface MoneyInterface{
	//getter for money value
	public double getMoney();
	//setter for money value
	public void setMoney(double value);
	//Convert money in the future, currently in dollars
	public void convertCurrency(double conversionRate);
}
