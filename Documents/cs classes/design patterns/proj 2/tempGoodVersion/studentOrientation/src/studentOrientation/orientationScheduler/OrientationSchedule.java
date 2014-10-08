/**This is the object that will contain the total values
 *the total values will be displayed to the user
*/
package studentOrientation.orientationScheduler;

import studentOrientation.util.Debug;

public class OrientationSchedule{

	private double totalEffortVal;
	private double totalMoneyVal;
	private double totalTimeVal;
	//empty constructor that prints when debug val = 2
	public OrientationSchedule()
	{

		this.totalEffortVal = 0;
		this.totalMoneyVal = 0;
		this.totalTimeVal = 0;
		if(Debug.getDebug() == 2)
		{
			System.out.println("OrientationSchedule constructor called");
		}
	}
	//Effort getter
	public double gettotalEffort(){
		return totalEffortVal;
	}
	//Effort adder
	public void addtotalEffort(double value){
		this.totalEffortVal += value; 
	}
	//Money getter
	public double gettotalMoney(){
		return totalMoneyVal;
	}
	//Money adder
	public void addtotalMoney(double value){
		this.totalMoneyVal += value; 
	}
	//Time getter
	public double gettotalTime(){
		return totalTimeVal;
	}
	//Time adder
	public void addtotalTime(double value){
		this.totalTimeVal += value; 
	}

	/**toString method
	* used to override toString for debugging purposes
	*/
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Total Effort value: " + totalEffortVal +"\n");
		result.append("Total Money value: " + totalMoneyVal + "\n");
		result.append("Total Time value: " + totalTimeVal);
		return result.toString();
	}
}
