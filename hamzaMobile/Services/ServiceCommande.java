/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
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

/**
 *
 * @author amine
 */
public class ServiceCommande {
    
    
    
    ConnectionRequest req;
    ArrayList<Commande> commandes;
    
    public ServiceCommande() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<Commande> getCommandes(float total,int idUser) {
        String url = "http://localhost/baskeltt/web/app_dev.php/produitF/confirmerCommandeM?total="+total+"&id_user="+idUser;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }
    
      
}
