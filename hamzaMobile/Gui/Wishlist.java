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
import Services.ServiceCategorieP;
import Services.ServiceLigneCommande;
import Services.ServiceProduit;
import Services.ServiceUser;
import Services.ServiceWish;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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

/**
 *
 * @author amine
 */
public class Wishlist extends Form{
    
    private Wishlist current;
    private ArrayList<Produit> produits;
    private ArrayList<Wish> wishlist;
    private EncodedImage enc;
    private Container produitC;
        private ArrayList<CategorieP> categories;


      private int idP;
        Image imgs;
    ImageViewer imgv;
        String Prix;
    
    public Wishlist(String username) {
        Container animalC=new Container(BoxLayout.y());
            setTitle("Mes Favoris");
       current=this;
        Home t = new Home(username);
        t.addMenu(this, username);
                try {
            enc=EncodedImage.create("/loadingg.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        ServiceProduit as=new ServiceProduit();
        ServiceWish cas=new ServiceWish();
        produits=as.getProduit();
        User u = ServiceUser.getInstance().getUser(username);
        wishlist=cas.getWishlists(u.getId());
        produitC=new Container();
        produitC.setLayout(BoxLayout.y());

                for (Wish a : wishlist)
            addProduit(a,username);
                
               
        
                add(produitC);
                getToolbar().addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_ARROW_BACK, e-> {
                new AllProduits(username).show();
              
              });
        }
        private void addProduit(Wish a,String username) {
        Container animalC=new Container(BoxLayout.y());
        Produit ca=null;

      
        Button remove=new Button("Retirer de Mes Favoris");
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
         ServiceWish rs= new ServiceWish();

                      rs.delWishlists(a.getId());
                      Dialog.show("Succès", "Produit retiré de vos favoris", "ok","quitter");
                      new Wishlist(username).show();



        });
                                User u = ServiceUser.getInstance().getUser(username);

                         Button AddToCart=new Button("Ajouter Au Panier");
                 AddToCart.addActionListener(e -> {
         LigneCommande em= new LigneCommande();
              ServiceProduit sb= new ServiceProduit();
            
                      ServiceLigneCommande rs=new ServiceLigneCommande();
                      sb.AjouterP(a.getIdProduit(),u.getId());
                  new AllProduits(username).show();
           // System.out.println(idl);
          
              
        });

animalC.addAll(imgv, new Label("Nom: "+ca.getNom()),new Label("Prix: "+Prix), remove,AddToCart);
       produitC.add(animalC);
    }
    
  
}
