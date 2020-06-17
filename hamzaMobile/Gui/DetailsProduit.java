/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieP;
import Entities.LigneCommande;
import Entities.Produit;
import Entities.User;
import Entities.Wish;
import Services.ServiceLigneCommande;
import Services.ServiceProduit;
import Services.ServiceUser;
import Services.ServiceWish;


import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.ParseException;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
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
import com.twilio.Twilio;




/**
 *
 * @author abdel
 */
public class DetailsProduit extends Form {
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
     public static final String ACCOUNT_SID ="AC87415bc270674dccbc3300c28fc22bce";
    public static final String AUTH_TOKEN ="dc2a862bfc660b9af147ffba657a739d";
    int idE;
        public DetailsProduit(Form previous, Produit a, CategorieP c, String username) throws ParseException {
                        try {
            enc=EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        setTitle("Détails Produit");
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
        User u = ServiceUser.getInstance().getUser(username);
        String img=a.getImage();
        String url="http://localhost/baskeltt/web/uploads/images/"+img;
        System.out.println(url);
        imgs = URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
        String Pr=String.valueOf(a.getPrix());
         Button AddToCart=new Button("Ajouter Au Panier");
                 AddToCart.addActionListener(e -> {
         LigneCommande em= new LigneCommande();
              ServiceProduit sb= new ServiceProduit();
            
                      ServiceLigneCommande rs=new ServiceLigneCommande();
                      sb.AjouterP(a.getId_produit(),u.getId());
                  new AllProduits(username).show();
           // System.out.println(idl);
          
              
        });
         
           Button AddToWish=new Button("Ajouter Aux Favoris");
                 AddToWish.addActionListener(e -> {
         Wish em= new Wish();
            
                      ServiceWish rs=new ServiceWish();
                      rs.AjouterW(a.getId_produit(),u.getId());
                  new AllProduits(username).show();
           // System.out.println(idl);
          
              
        });
                
                String Prix="Prix :"+ String.valueOf(a.getPrix())+"TND" ;
        Container C2= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addAll(C2.add(imgv),new Label("Nom: "+a.getNom()),new Label(Prix),new Label("Catégorie : "+c.getNom()), new SpanLabel("Description : "+a.getDescription()),AddToCart,AddToWish);
        getToolbar().addMaterialCommandToLeftBar("Retour", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
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
