/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieP;
import Entities.Produit;
import Entities.User;
import Services.ServiceCategorieP;
import Services.ServiceProduit;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
 * @author abdel
 */
public  class AllProduits extends Form{
        private AllProduits current;
    private ArrayList<Produit> produits;
    private ArrayList<CategorieP> categories;
    private EncodedImage enc;
    private Container produitC;
        private ArrayList<User> users;

        private Container produitCC;

      private int idP;
        Image imgs;
    ImageViewer imgv;
    
    public AllProduits(String username) {
        current=this;
      
        current=this;
        Home t = new Home(username);
        t.addMenu(this, username);
                try {
                    enc=EncodedImage.create("/loadingg.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }  
        ServiceProduit as=new ServiceProduit();
        ServiceCategorieP cas=new ServiceCategorieP();
        produits=as.getProduit();
        categories=cas.getCategorieP();
        setTitle("Nos Produits");
        setLayout(BoxLayout.y());
        ComboBox listeCat=new ComboBox();
        listeCat.addItem("Tout");
        for (CategorieP c : categories)
            listeCat.addItem(c.getNom());
        listeCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeCat.getSelectedIndex()==0)
                    trier(-1, username);
                else{ 
                    for (CategorieP c : categories)
                        if(c.getNom().toLowerCase().contains((CharSequence) listeCat.getSelectedItem())){
                    trier(c.getIdCategorie(), username);
                    break;}
                    }
            }
        });
        getToolbar().addMaterialCommandToRightBar("Panier", FontImage.MATERIAL_SHOPPING_CART, e-> {
            try {
                new Paniers(this,username).show();
            } catch (ParseException ex) {
               
            }
        });
        add(listeCat);
        produitC=new Container();
        produitC.setLayout(BoxLayout.y());
        for (Produit a : produits)
            addProduit(a, username);
        add(produitC); 


        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
                     ServiceProduit aaa=new ServiceProduit();
ArrayList<Produit> lis=aaa.getProduit();
        
        this.getToolbar().addSearchCommand(e -> {
               removeAll();
    String text = (String)e.getSource(); 
produitCC=new Container();
        produitCC.setLayout(BoxLayout.y());
        for (Produit a : produits){
            
                  if(a.getNom().toLowerCase().contains(text.toLowerCase()) || Float. toString(a.getPrix()).contains(text.toLowerCase()) ) {{
                              Container produitCA=new Container(BoxLayout.y());
        //URLImage imageUrl=URLImage.createToStorage(enc, Statics.IMAGE_URL+a.getPhoto(), Statics.IMAGE_URL+a.getPhoto(),URLImage.RESIZE_SCALE_TO_FILL);
        //ImageViewer image=new ImageViewer(imageUrl);
       
        Label prix = new Label();
        prix.setText(String.valueOf(a.getPrix()));
        Button details=new Button(a.getNom());
      
        String img=a.getImage();
                
        String url="http://localhost/baskeltt/web/uploads/images/"+img;
        System.out.println(url); 
        imgs = URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategorieP ca=null;
                for (CategorieP c : categories) {
                    if (c.getIdCategorie()==a.getId_categorie()) {
                        ca=c;
                        break;
                    }
                }
               try {
                    new DetailsProduit(current, a, ca,username).show();
                  
                    
                } catch (ParseException ex) {
                   System.out.println(ex);
                }
            }
        });
        
        produitCA.addAll(imgv,details,prix);        
        produitCA.setLeadComponent(details);
        produitCC.add(produitCA);
                 
     
                      
            
       
           }}
        else
                  { 
                  }
        }
        if(produitCC!=null)
        removeComponent(produitC);
                add(produitCC); 
}, 4);
        
        
        
        
        
        
        
        
        
        
        
        
        
      
       
    }
    
    
    
    private void addProduit(Produit a,String username) {
        Container produitCA=new Container(BoxLayout.y());
        //URLImage imageUrl=URLImage.createToStorage(enc, Statics.IMAGE_URL+a.getPhoto(), Statics.IMAGE_URL+a.getPhoto(),URLImage.RESIZE_SCALE_TO_FILL);
        //ImageViewer image=new ImageViewer(imageUrl);
       
        Label prix = new Label();
        prix.setText(String.valueOf(a.getPrix()));
        Button details=new Button(a.getNom());
      
        String img=a.getImage();
                
        String url="http://localhost/baskeltt/web/uploads/images/"+img;
        System.out.println(url); 
        imgs = URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategorieP ca=null;
                for (CategorieP c : categories) {
                    if (c.getIdCategorie()==a.getId_categorie()) {
                        ca=c;
                        break;
                    }
                }
               try {
                    new DetailsProduit(current, a, ca,username).show();
                  
                    
                } catch (ParseException ex) {
                   System.out.println(ex);
                }
            }
        });
        
        produitCA.addAll(imgv,details,prix);        
        produitCA.setLeadComponent(details);
        produitC.add(produitCA);
        
    }
    
    private void trier(int id, String username) {
        produitC.removeAll();
        if (id==-1) {
            for (Produit a : produits)
                addProduit(a,username);
        } else {
            for (Produit a : produits) {
                if (a.getId_categorie()==id)
                    addProduit(a, username);
            }
        }
        refreshTheme();
    }
    
   
}
