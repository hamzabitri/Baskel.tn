/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieP;
import Entities.Produit;


import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.ParseException;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

import com.codename1.io.Log; 
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea; 
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author abdel
 */
public class ReclamationForm extends Form {
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    
           private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label(); 
    int idE;
        public ReclamationForm(String username)  {
                        try {
            enc=EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        setTitle("Reclamation");
        setLayout(BoxLayout.y());
        
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_CAMERA, e -> {
           Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        paintComponent(screenshot.getGraphics(), true);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
               System.out.println(imageFile);
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
        });
        
      
         Button AddToCart=new Button("Ajouter Au Panier");
         
                             AddToCart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
       //   sms();
      }


  });
         
            gui_Label_3.setText("Veuillez remplir votre réclamation");
            gui_Label_3.setName("Label_3");
            add(gui_Label_3);
            TextArea reclamationText = new TextArea();
            reclamationText.setRows(10);
            add(reclamationText);
            Button btnSMS = new Button("Envoyer par SMS");
            
            btnSMS.addActionListener(l -> {                                             
                
                
                if(reclamationText.getText().equals("")) {
                Dialog.show("Vide", "Veuillez remplir votre reclamation", new Command("OK"));
                }
                else { 
                           Twilio.init("AC3ad4ac148bec2cb52ee86f6900692a9b", "974b8249cf4d1a98e42117ead2402594");
            System.out.println("A message was sent");
                    Message message =  Message.creator(new com.twilio.type.PhoneNumber("+21628771007"),
                    new com.twilio.type.PhoneNumber("+12052362730"),
                    reclamationText.getText()).create();
                                    Dialog.show("Success", "Votre message a été envoyé avec succée : "+reclamationText.getText(), new Command("OK"));

                }
                
                
                
});
            
            
            
            add(btnSMS);
            Button btnMail = new Button("Envoyer par EMAIL"); 
           
           add(btnMail);
                     btnMail.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
      com.codename1.messaging.Message m = new com.codename1.messaging.Message(reclamationText.getText());
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"abdelhak.hamdi@esprit.tn"}, "Reclamation",m );
      }


  });
           
           getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e-> new Home(username).show());
    }
  /*  public static void sms() {
  
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            System.out.println("A message was sent");
            Message message = Message.creator(new com.twilio.type.PhoneNumber("+21655882630"),
                    new com.twilio.type.PhoneNumber("+18312288506"),
                    "New goods were added").create();
            System.out.println(message.getSid());
        } */
    
}
