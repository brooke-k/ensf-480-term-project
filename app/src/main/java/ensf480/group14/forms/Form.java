/**
 * File: Form.java
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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.forms;
/**
 * The import statements used in order for the code to work. 
 */

import java.awt.event.ActionListener;
import javax.swing.JPanel;

import ensf480.group14.eventListeners.Listener;
/**
 * This interface just displays the jpanel from the specific form. 
 */
public interface Form {
    public JPanel display(Listener listener);
}
