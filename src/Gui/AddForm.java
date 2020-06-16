/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.messaging.Message;
import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import Entities.User;
import Services.ServiceUser;
import java.util.Map;
import java.util.Random;
import javafx.scene.control.Alert;



/**
 *
 * @author Aziz Sayeb
 */
public class AddForm extends Form{
        TextField tfUsername = new TextField("","Username");
        TextField tfPassword = new TextField("","Password");
        TextField tfPasswordC = new TextField("","Confirme Password");
        TextField tfemail = new TextField("","email");
        String accountSID = "AC6f9520a6e9a3cc4e2b91ccb6dc69224d";
        String authToken = "9cc19a3b3441e383c211f8bdc2c739dd";
        String fromPhone = "51989910";
        String destinationPhone= "92863484";

        Button btnValider = new Button("Confirmer");
    AddForm(Form previous) {
        setTitle("Créer un nouveau compte");
        setLayout(BoxLayout.y());
     
       
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfUsername.getText().length()==0)||(tfPassword.getText().length()==0)||(tfemail.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Random r = new Random();
                        String val = "" + r.nextInt(10000);
                        while(val.length() < 4) {
                        val = "0" + val;
                        }
                        User t = new User(tfUsername.getText(), tfPassword.getText(), tfemail.getText());
                        if( ServiceUser.getInstance().addUser(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        Message m = new Message("vous êtes inscrit Baskel.tn ");
                        Display.getInstance().sendMessage(new String[] {"aziz.sayeb3233@gmail.com"}, "Baskel", m);
                        Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                        queryParam("To", destinationPhone).
                        queryParam("From", fromPhone).
                        queryParam("Body", val).
                        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                        getAsJsonMap();}
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfUsername,tfPassword,tfemail,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
