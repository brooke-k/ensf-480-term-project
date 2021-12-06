import java.awt.ActionListener;
import java.awt.ActionEvent;

package ensf480.group14;


public class Driver {
    JFrame frame;

    LoginListener loginListener;
    class LoginListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == "loginButton"){
                System.out.println("login");
            }
            else if(e.getSource() == "signUpButton"){
                System.out.println("SignUp");
            }
        }
    }

    public static void main(String[] args){
        frame = new JFrame();
        LoginForm form = new LoginForm(new loginListener());
        frame.add(form.display());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return;
    }
}
