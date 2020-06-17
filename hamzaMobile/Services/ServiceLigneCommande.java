/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieP;
import Entities.LigneCommande;
import Utils.CnxRequest;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Amine
 */
public class ServiceLigneCommande {

    ConnectionRequest req;
    ArrayList<LigneCommande> paniers;
    
    public ServiceLigneCommande() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<LigneCommande> getPaniers(int idU) {
        String url=Statics.BASE_URL+"/produitF/"+idU+"/afficherPanierM";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                paniers=parsePaniers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return paniers;
    }
    
    private ArrayList<LigneCommande> parsePaniers(String json) {
        try {
            paniers =new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> catJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)catJson.get("root");
            for (Map<String, Object> obj : liste) {
                LigneCommande c = new LigneCommande();
                c.setId((int)Float.parseFloat(obj.get("id").toString()));
                c.setIdUser((int)Float.parseFloat(((Map<String, Object>)obj.get("idUser")).get("id").toString()));
                c.setIdProduit((int)Float.parseFloat(((Map<String, Object>)obj.get("idProduit")).get("id").toString()));
                c.setQuantite((int)Float.parseFloat((obj.get("quantite")).toString()));
                paniers.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return paniers;
    }
    
        public ArrayList<LigneCommande> delPaniers(int id) {
        String url=Statics.BASE_URL+"/produitF/"+id+"/supprimerPanierM";
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);
        return paniers;
    }
        


}
