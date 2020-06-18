/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
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
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author onsk
 */
public class ArticleService {
   private ConnectionRequest req;
   private ArrayList<Article> articles;
    
    public ArticleService() {
        req=CnxRequest.getInstance().getConnectionRequest();
    }
    
    public ArrayList<Article> getArticle() {
        String url=Statics.BASE_URL+"B/article/all";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                articles=parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    private ArrayList<Article> parseEvent(String json) {
        try {
            articles=new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> eventsJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)eventsJson.get("root");
            for (Map<String, Object> obj : liste) {
                Article e = new Article();
                e.setId((int)Float.parseFloat(obj.get("id").toString()));
                e.setTitle(obj.get("title").toString());
                e.setContent(obj.get("content").toString());
                e.setImage(obj.get("image").toString());
                e.setDate(obj.get("date").toString());

                 articles.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return articles;
    }
    
             
}
