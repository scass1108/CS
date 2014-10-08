/**This is the class that constructs/has building sequence of the schedule
*/

package studentOrientation.orientationScheduler;

import studentOrientation.util.ActivityBuilder;
import studentOrientation.util.Debug;
import java.util.List;
import java.util.ArrayList;


public class ScheduleEngineer {
	
	//private activityBuilder activities;
	private OrientationSchedule schedule = new OrientationSchedule();

	//Constructor for ScheduleEngineer
	public ScheduleEngineer(ActivityBuilder tour, ActivityBuilder bookStore, 
						ActivityBuilder dorm, ActivityBuilder registration){
	//adding the seperate objects to an arraylist as well as printing when it is done for a debug value		
			if(Debug.getDebug() == 2)
			{
				System.out.println("ScheduleEngineer constructor called");
			}		

			List<ActivityBuilder> list = new ArrayList<ActivityBuilder>();					
					
			list.add(tour);
			if(Debug.getDebug() == 3)
			{
				System.out.println("tour added");
			}
			list.add(bookStore);
			
			if(Debug.getDebug() == 3)
			{
				System.out.println("bookstore added");
			}

			list.add(dorm);
			if(Debug.getDebug() == 3)
			{
				System.out.println("dorm added");
			}

			list.add(registration);
			if(Debug.getDebug() == 3)
			{
				System.out.println("registration added");
			}
			//adding the time, effort, and money together for each activity
			for(ActivityBuilder act : list){
				schedule.addtotalEffort(act.getEffort());
				schedule.addtotalMoney(act.getMoney());
				schedule.addtotalTime(act.getTime());
				if(Debug.getDebug() == 4){
					System.out.println("Activity added");
				}
			}	
	}
	
	//Getter for the OrientationSchedule object
	public OrientationSchedule getorientationSchedule(){
			return this.schedule;
	}



}
