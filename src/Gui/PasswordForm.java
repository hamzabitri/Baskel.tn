/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Entities.User;
import Services.ServiceUser;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Aziz Sayeb
 */
public class PasswordForm extends Form {
    Form previous;
 Form current;
 Resources theme;
    public PasswordForm(String username) {
        setTitle("Modifier Votre Mot de passe");
        setLayout(BoxLayout.y());
        User u=ServiceUser.getInstance().getUser(username);
        TextField tAnPass = new TextField("","Entrez votre ancien mot de passe");
        TextField tNouPass = new TextField("","Entrez votre nouveau mot de passe");
        tAnPass.setHint("Entrez votre ancien mot de passe");
        tAnPass.setConstraint(TextField.PASSWORD);
        tNouPass.setHint("Entrez votre nouveau mot de passe");
        tNouPass.setConstraint(TextField.PASSWORD);
        Button btnPass = new Button("Modifier");
        addAll(tAnPass,tNouPass,btnPass);
         btnPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String pw = u.getPassword();
                String pw1=pw.substring(4);
                String pw11="$2a$"+pw1;
                if(BCrypt.checkpw(tAnPass.getText(),pw11)==false){
                                                                    Dialog.show("ERROR", "Ancien Mot de passe erroné", new Command("OK"));

                        }
                else{
                String nouvpass=tNouPass.getText();
                User t = new User(u.getId(),nouvpass);
                if( ServiceUser.getInstance().MotDePasseOublier(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            new ProfilForm(username,previous).show();
                            ServiceUser.getInstance().sendMail(t);
           
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
                   
        
        
    }
         });
}
}