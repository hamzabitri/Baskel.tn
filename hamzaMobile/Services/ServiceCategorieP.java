/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieP;
import Utils.CnxRequest;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author abdel
 */
public class ServiceCategorieP {
     private ConnectionRequest req;
    private ArrayList<CategorieP> categories;
    
    public ServiceCategorieP() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<CategorieP> getCategorieP() {
        String url=Statics.BASE_URL+"/m/allC";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                categories=parseCategorieP(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
    
    private ArrayList<CategorieP> parseCategorieP(String json) {
        try {
            categories=new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> categoriesJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)categoriesJson.get("root");
            for (Map<String, Object> obj : liste) {
                CategorieP e = new CategorieP();
                e.setIdCategorie((int)Float.parseFloat(obj.get("id").toString()));
                e.setNom(obj.get("name").toString());
                categories.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return categories;
    }
}
