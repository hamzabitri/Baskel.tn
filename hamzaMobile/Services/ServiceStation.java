/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Station;
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
 * @author Mariem Chtourou
 */
public class ServiceStation {
    
   private ConnectionRequest req;
    private ArrayList<Station> stations;
    
    public ServiceStation() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<Station> getStation() {
        String url=Statics.BASE_URL+"/findAll";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                stations=parseStation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stations;
    }
    
    private ArrayList<Station> parseStation(String json) {
        try {
            stations=new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> produitsJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)produitsJson.get("root");
            for (Map<String, Object> obj : liste) {
                Station e = new Station();
                e.setId((int)Float.parseFloat(obj.get("id").toString()));
                e.setNomStation(obj.get("nomStation").toString());
                e.setLatitude(Double.parseDouble(obj.get("latitude").toString()));
                e.setLongitude(Double.parseDouble(obj.get("longitude").toString()));
                e.setNbrVelo((int)Float.parseFloat(obj.get("nbrVelo").toString())); 
                stations.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return stations;
    }
       
}
