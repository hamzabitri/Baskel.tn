/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Entities.User;
import Services.ServiceUser;
import com.codename1.ui.FontImage;

/**
 *
 * @author Aziz Sayeb
 */
public class EditForm extends Form {

Form current;
Form previous;
 
    public EditForm(Form current, String username) {
        current=this;
        setTitle("Information du Profile");
        setLayout(BoxLayout.y());
        User u=ServiceUser.getInstance().getUser(username);
        TextField tUsername = new TextField(u.getUsername());
        TextField tEmail = new TextField(u.getEmail());
        Button update = new Button("Modifier");
        
        
            update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                    try {
                        String email = tEmail.getText();
        String Username = tUsername.getText();
       
                        User t = new User(u.getId(),Username,email);
                        if( ServiceUser.getInstance().editUser(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            User s=ServiceUser.getInstance().getUser(username);
                            ProfilForm l = new ProfilForm(username,previous);
                            l.show();
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } 
            }
        });
        addAll(tUsername,tEmail,update);
        getToolbar().addMaterialCommandToLeftBar("Retour ", FontImage.MATERIAL_ARROW_BACK, e -> new ProfilForm(username, previous).show());
    }
}
