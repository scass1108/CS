/** This is the class for the activity taking a tour on foot
*/
package studentOrientation.orientationScheduler;

import studentOrientation.util.EffortInterface;
import studentOrientation.util.MoneyInterface;
import studentOrientation.util.TimeInterface;
import studentOrientation.util.ActivityBuilder;
import studentOrientation.util.Debug;
public class OnFootTour implements ActivityBuilder, EffortInterface, MoneyInterface, TimeInterface
{
	private double effortVal;
	private double moneyVal;
	private double timeVal;
	//empty constructor that prints when debug val = 2
	public OnFootTour()
	{
		if(Debug.getDebug() == 2)
		{
			System.out.println("OnFootTour constructor called");
		}
	}

	//Set the activity's effort value
	public void setActivityEffort(double value)
	{
		this.effortVal = value; 	
	}
	//Set the activity's money value
	public void setActivityMoney(double value)
	{
		this.moneyVal = value;
	}
	//Set the activity's time value
	public void setActivityTime(double value)
	{
		this.timeVal = value;	
	}

	//getter for effort value
	public double getEffort()
	{
		return effortVal;
	}
	//setter for effort value
	public void setEffort(double value)
	{
		this.effortVal = value;
	}

	//getter for money value
	public double getMoney()
	{
		return moneyVal;
	}
	//setter for money value
	public void setMoney(double value)
	{
		this.moneyVal = value;	
	}
	//getter for time value
	public double getTime()
	{
		return timeVal;
	}
	//setter for time value
	public void setTime(double value)
	{
		this.timeVal = value;
	}
	
	public void convertCurrency(double conversionRate)
	{
		this.moneyVal = moneyVal * conversionRate;
	}
	public void convertTime(double conversionRate)
	{
		this.timeVal = timeVal * conversionRate;
	}
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Effort value: " + effortVal +"\n");
		result.append("Money value: " + moneyVal + "\n");
		result.append("Time value: " + timeVal);
		return result.toString();
	}

}
