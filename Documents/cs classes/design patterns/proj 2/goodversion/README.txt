ant clean removes the .class and .java files
ant compile translates Java code to Java bytecode
ant run used Java interpreter to run the compiled Java bytecode that was compiled
ant doc used to create html files that have information about all methods used by all classes

CS442 Design Patterns
Fall 2013
Assignment <Assignment 1> README FILE

Due Date:  Friday, October 18th, 2013
Submission Date: Friday, October 18th, 2013
Grace Period Used This Project: 0
Grace Period Remaining: 0
Author(s): Matthew Cheung, Shawn Casserly
e-mail(s): mcheung1@binghamton.edu, scasser1@binghamton.edu

PURPOSE:

[
  The purpose of this project was to implement Design Patterns when writing
  a program to emulate an Orientation Schedule. There were several decisions to be made:
  
  1.What type of pattern to use - We believed that a builder pattern was most applicable here because
  the order in which the Tour, Dorm, Registration, and Buying Books was done did not matter in this project.

  2.Interfaces - In this project there were multiple things such as Money, Time, and Effort that were used 
  by the Tour, Dorm, Registration, and Book classes. It didnt make sense for these to be implimented seperately
  in each different classes, so these were programmed to interfaces for reusability as well as the ability to modify
  them at a later date more easily.
]

PERCENT COMPLETE:

[
	I believe this project is 100% complete.
]

PARTS THAT ARE NOT COMPLETE:

[
 	N/A
]

BUGS:

[
	None
]

FILES:

[
 BookBridge.java - one option of book buying for the schedule
 BusRideTour.java - one option of a tour for the schedule
 ComputerRegister.java - one option of registering for the schedule
 FindDorm.java - one option of finding a dorm for the schedule
 FormRegister.java - one option of registering for the schedule
 GameDorm.java - one option of finding a dorm for the schedule
 MandoBooks.java - one option of buying a book for the schedule
 OnFootTour.java - one option of a tour for the schedule
 OrientationSchedule.java - class with methods for getting the 
 total effort, time, and money
 for the whole schedule created
 ScheduleEngineer.java - final object created containing the schedule with
 all time, money, and effort calculated
 UniversityBookStore.java - one option of book buying for the schedule
 ActivityBuilder.java - Interface implemented by all activities with methods 
 for getting/setting the effort, time, and money of each activity
 Debug.java - Holds the debug number that the project is using
 EffortInterface.java - Interface holding methods related to effort
 MoneyInterface.java - Interface holding methods related to money
 TimeInterface.java - Interface holding methods related to time
]
SAMPLE OUTPUT:

[
	Depends on Debug value
]

TO COMPILE:

[
  ant compile
]

TO RUN:

[
  ant run
]

TO GENERATE JAVADOCS:

[
  ant doc
]

EXTRA CREDIT:

[
  N/A
]

ACKNOWLEDGEMENT:

[
 N/A
]

