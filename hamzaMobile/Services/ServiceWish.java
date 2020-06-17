/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author amine
 */

import Entities.Wish;
import Utils.CnxRequest;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.codename1.ui.Dialog;


public class ServiceWish {
    
        ConnectionRequest req;
    ArrayList<Wish> wishlists;
    
    public ServiceWish() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<Wish> getWishlists(int idU) {
        String url=Statics.BASE_URL+"/produitF/wishlistM/"+idU;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                wishlists=parseWishlists(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return wishlists;
    }
    
    private ArrayList<Wish> parseWishlists(String json) {
        try {
            wishlists =new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> catJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)catJson.get("root");
            for (Map<String, Object> obj : liste) {
                Wish c = new Wish();
                c.setId((int)Float.parseFloat(obj.get("id").toString()));
                c.setIdProduit((int)Float.parseFloat(((Map<String, Object>)obj.get("idProduit")).get("id").toString()));
                c.setIdUser((int)Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString()));
                wishlists.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return wishlists;
    }
    
        public ArrayList<Wish> delWishlists(int id) {
        String url=Statics.BASE_URL+"/produitF/"+id+"/deleteWishM";
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);
        return wishlists;
    }
        
    
    public void AjouterW(int idProduit,int idUser) {
   MultipartRequest con = new MultipartRequest();// crÃ©ation d'une nouvelle demande de connexion

        String Url = "http://localhost/baskeltt/web/app_dev.php/produitF/wishM?id_user="+idUser+"&id_produit="+idProduit;

        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());//RÃ©cupÃ©ration de la rÃ©ponse du serveur
            System.out.println(str);//Affichage de la rÃ©ponse serveur sur la console

        });

        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion Ã  la file d'attente du NetworkManager
                       Dialog.show("Succès", "Produit Ajouté à vos favoris", "Okay", "Quitter");

    }
    
    
}
