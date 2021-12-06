
package ensf480.group14.eventListeners;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class ContactListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == "loginButton"){
            System.out.println("login");
        }
        // else if(e.getSource() == "signUpButton"){
        //     System.out.println("SignUp");
        // }
    }
    



}