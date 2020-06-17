/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import Entities.User;
import Services.ServiceUser;
import org.mindrot.jbcrypt.BCrypt;



/**
 *
 * @author Aziz Sayeb
 */
public class LoginForm extends Form{
   Form current;
    public  static int ID=0;
ImageViewer iv1;
 public LoginForm(Resources theme){
     current=this;
        setTitle("Login");
        setLayout(BoxLayout.y());
        iv1 = new ImageViewer(theme.getImage("logo.png"));
        this.add(iv1);
         Container cnt1=new Container(new FlowLayout(Container.CENTER));
                  Container cnt4=new Container(new FlowLayout(Container.CENTER));
        Container cnt5=new Container(new FlowLayout(Container.CENTER));

        Container cnt2=new Container(new FlowLayout(Container.CENTER));
        Container cnt3=new Container(new FlowLayout(Container.CENTER));
                
  Label Seconnecter = new Label("Se connecter");
  


                        TextField Username = new TextField("", "saisir votre username");
                             
                        
                        TextField tpassword = new TextField();
                      
                        
                        tpassword.setHint("saisir votre mot de passe");
        tpassword.setConstraint(TextField.PASSWORD);
cnt2.addAll(Username);
cnt3.add(tpassword);
                
                
                        Button btnval = new Button("valider");

          

     
                
                
                Button motOublier= new Button("Mot de passe oublié ?");
                
          cnt5.add(motOublier);
          
                    addAll(cnt1,Seconnecter,cnt2,cnt3,btnval,cnt5);    
/***********************************/
               btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Username.getText().length()==0)||(tpassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {               // pw = pw.replace("$2y$", "$2a$");

User t=ServiceUser.getInstance().getUser(Username.getText());
String pw = t.getPassword();
                        System.out.println("1");
String pw1=pw.substring(4);
String pw11="$2a$"+pw1;
                System.out.println("pw1 = " +pw11);
//                pw = pw.replace("$2y$", "$2a$");
                
                        if(t.equals(null))
                        {
                            
                                            Dialog.show("ERROR", "User not found", new Command("OK"));
                                            }

                        else if(BCrypt.checkpw(tpassword.getText(), pw11)==false){
                                                                    Dialog.show("ERROR", "Mot de passe invalide", new Command("OK"));

                        }
                                
                                else{
                            System.out.println(pw);
                            ID=t.getId();
                           new Home(Username.getText()).show();
                            
                        
                        }
                  
                    } catch (NumberFormatException e) {
                        System.out.println("1");
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});

}

   
}
 
 
 
 
 