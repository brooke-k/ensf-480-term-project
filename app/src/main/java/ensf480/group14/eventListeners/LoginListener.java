
package ensf480.group14.eventListeners;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LoginListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("just in case");
        if(e.getSource() == "loginButton"){
            System.out.println("login");
        }
        else if(e.getSource() == "signUpButton"){
            System.out.println("SignUp");
        }
    }

}