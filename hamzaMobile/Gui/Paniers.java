/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieP;
import Entities.Commande;
import Entities.Produit;
import Entities.LigneCommande;
import Entities.User;
import Services.ServiceCategorieP;
import Services.ServiceCommande;
import Services.ServiceProduit;
import Services.ServiceLigneCommande;
import Services.ServiceUser;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author Amine
 */
public class Paniers extends Form {
    private AllProduits previous;
        private Paniers current;
    private ArrayList<Produit> produits;
    private ArrayList<CategorieP> categories;
    private ArrayList<LigneCommande> paniers;
    private ArrayList<Commande> commandes;
    private EncodedImage enc;
    private Container produitC;
        Image imgs;
    ImageViewer imgv;
    String Prix;
    float total;
    int qte;
        public Paniers(Form previous,String username) throws ParseException {
            Container animalC=new Container(BoxLayout.y());
            setTitle("Mon Panier");
       current=this;
        Home t = new Home(username);
        t.addMenu(this, username);
                try {
            enc=EncodedImage.create("/loadingg.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
                        User u = ServiceUser.getInstance().getUser(username);

        ServiceProduit as=new ServiceProduit();
        ServiceLigneCommande cas=new ServiceLigneCommande();
        produits=as.getProduit();
        paniers=cas.getPaniers(u.getId());
        produitC=new Container();
        produitC.setLayout(BoxLayout.y());
                for (LigneCommande a : paniers)
            addProduit(a,username);
                
                 Button ConfirmCart=new Button("Confirmer Votre Commande");
         ConfirmCart.addActionListener(e -> {
         Commande em= new Commande();
              ServiceCommande sb= new ServiceCommande();
                    Produit ca=null;
                    ServiceLigneCommande rs=new ServiceLigneCommande();
                      
                     for (LigneCommande l : paniers) 
                     {
                            qte=l.getQuantite();
                            for (Produit c : produits) 
                            {
                                if (c.getId_produit()==l.getIdProduit()) 
                                {
                                    total+=qte*c.getPrix();
                                }
                            }
                     }
                     
                     if (total!=0){ 
                         sb.getCommandes(total,u.getId());
                         Dialog.show("Succès", "Votre Commande est en cours de traitement !", "Ok","Quitter");

                     }
                     else{
                         Dialog.show("Échec", "Votre panier est vide !", "Ok","Quitter");
                     }
                      produitC.refreshTheme();
                          new AllProduits(username).show();

              
        });
                
        
                add(produitC);
                add(ConfirmCart);
                getToolbar().addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_ARROW_BACK, e-> {
                new AllProduits(username).show();
              
              });
        }
        private void addProduit(LigneCommande a,String username) {
        Container animalC=new Container(BoxLayout.y());
        Produit ca=null;
        Button remove=new Button("Supprimer");
                for (Produit c : produits) {
                    if (c.getId_produit()==a.getIdProduit()) {
                        ca=c;
                        break;
                    }
                }
                String img=ca.getImage();
        String url="http://localhost/baskeltt/web/uploads/images/"+img;
        System.out.println(url);
        imgs = URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
        Prix=String.valueOf(ca.getPrix())+"TND";
        Container C2= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        remove.addActionListener(e->{
         ServiceLigneCommande rs= new ServiceLigneCommande();

                      rs.delPaniers(a.getId());
                      Dialog.show("Succès", "Produit supprimé", "ok","quitter");
            try {
                new Paniers(previous,username).show();
            } catch (ParseException ex) {
            }



        });

animalC.addAll(imgv, new Label("Nom: "+ca.getNom()),new Label("Quantité: "+a.getQuantite()),new Label("Prix: "+Prix), remove);
animalC.setLeadComponent(remove);
       produitC.add(animalC);
    }
        
        
}
