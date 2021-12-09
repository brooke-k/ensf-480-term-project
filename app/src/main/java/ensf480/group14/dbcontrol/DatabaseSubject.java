/**
 * File: DatabaseSubject.java
 * ENSF 480, Fall 2021
 * Term Project
 * Lecture Section: L02
 * Instructor: M. Moshirpour
 * Group 14
 * @author Khosla, Abhay
 * @author Kindleman, Brooke
 * @author Knapton, Nicholas
 * @author Kramer, Brian
 * Created: Dec 2021
 * @version 1.0
 */

package ensf480.group14.dbcontrol;

public interface DatabaseSubject {
	void addObserver(DatabaseObserver dbo);

	void removeObserver(DatabaseObserver dbo);

	void notifiyAllObservers();
}
