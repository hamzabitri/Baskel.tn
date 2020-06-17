/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Utils.CnxRequest;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author abdel
 */
public class ServiceProduit {
   private ConnectionRequest req;
    private ArrayList<Produit> produits;
    
    public ServiceProduit() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<Produit> getProduit() {
        String url=Statics.BASE_URL+"/m/allp";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                produits=parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    
    private ArrayList<Produit> parseProduit(String json) {
        try {
            produits=new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> produitsJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)produitsJson.get("root");
            for (Map<String, Object> obj : liste) {
                Produit e = new Produit();
                e.setId_produit((int)Float.parseFloat(obj.get("id").toString()));
                e.setNom(obj.get("nom").toString());
                e.setImage(obj.get("image").toString());
                e.setPrix((int)Float.parseFloat(obj.get("prix").toString()));
                e.setDescription(obj.get("description").toString()); 
                e.setReference(obj.get("reference").toString());
                e.setQuantite((int)Float.parseFloat(obj.get("quantite").toString()));              
                e.setId_categorie((int)Float.parseFloat(((Map<String, Object>)obj.get("idCategorie")).get("id").toString()));
                produits.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return produits;
    }
    
     public void AjouterP(int idProduit,int idUser) {
        MultipartRequest con = new MultipartRequest();// crÃ©ation d'une nouvelle demande de connexion

        String Url = "http://localhost/baskeltt/web/app_dev.php/produitF"+"/ajoutM?id_produit="+idProduit+"&id_user="+idUser;

        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());//RÃ©cupÃ©ration de la rÃ©ponse du serveur
            System.out.println(str);//Affichage de la rÃ©ponse serveur sur la console

        });

        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion Ã  la file d'attente du NetworkManager
                       Dialog.show("Succès", "Produit Ajouté à votre panier", "Okay", "Quitter");

    }
    

     
}
