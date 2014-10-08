/**Driver code to call Builder pattern to create orientationSchedule object
*This uses a Builder to create a student orientationSchedule object.
*/

package studentOrientation.driver;

import studentOrientation.util.ActivityBuilder;
import studentOrientation.orientationScheduler.ScheduleEngineer;
import studentOrientation.orientationScheduler.GameDorm;
import studentOrientation.orientationScheduler.FindDorm;
import studentOrientation.orientationScheduler.FormRegister;
import studentOrientation.orientationScheduler.ComputerRegister;
import studentOrientation.orientationScheduler.OrientationSchedule;
import studentOrientation.orientationScheduler.MandoBooks;
import studentOrientation.orientationScheduler.BusRideTour;
import studentOrientation.orientationScheduler.OnFootTour;
import studentOrientation.orientationScheduler.BookBridge;
import studentOrientation.orientationScheduler.UniversityBookStore;
import studentOrientation.util.Debug;

public class Driver{
    public static void main(String args[]) 
    {
		double bookStorePrice = 100;
		double busRide = 5;
		double footTour = 1;
		double tuition = 10000;

		ActivityBuilder tour= new BusRideTour();
		tour.setActivityEffort(100);
		tour.setActivityTime(60);
		tour.setActivityMoney(5);
		ActivityBuilder bookStore= new MandoBooks();
		bookStore.setActivityEffort(200);
		bookStore.setActivityTime(40);
		bookStore.setActivityMoney(bookStorePrice * .97);
		ActivityBuilder dorm= new GameDorm();
		dorm.setActivityEffort(400);
		dorm.setActivityTime(50);
		dorm.setActivityMoney(0);
		ActivityBuilder registration= new ComputerRegister(); 
		registration.setActivityEffort(100);
		registration.setActivityTime(10);
		registration.setActivityMoney(tuition);
		
		try{
			Debug.setDebug(Integer.parseInt(args[0]));
		}
		catch(Exception e){
			System.out.println("Exception thrown: " + e + "\n" + "Debug argument error");
			System.exit(1);
		}
		ScheduleEngineer engineer = new ScheduleEngineer(tour, bookStore, dorm, registration);
		
		OrientationSchedule schedule = engineer.getorientationSchedule();
	
		if(Debug.getDebug() == 1)
		{
			System.out.println("Orientation Schedule: " + schedule.toString());
		}
	}
}
