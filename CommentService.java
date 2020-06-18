/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Article;
import Entities.Comment;
import Entities.User;
import Gui.DetailsArticle;
import static Services.ServiceUser.instance;
import Utils.CnxRequest;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author onsks
 */
public class CommentService {
     private ConnectionRequest req;
   private ArrayList<Comment> comments;
   public boolean resultOK;
    public static CommentService instance = null;
    public CommentService() {
        req = new ConnectionRequest();
    }

      public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }
   
      public ArrayList<Comment> getEvent() {
        String url=Statics.BASE_URL+"B/article/allcommM";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent a) {
                comments=parseComment(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
  
   
    
      
     public boolean addComment(Comment c , int id, String username ) {
         req.setPost(true);
        User us = ServiceUser.getInstance().ti;
         username = us.getUsername();
        String Url = Statics.BASE_URL + "B/article/"+ id+"/addcom?content=" + c.getContent()+"&user="+username;
        req.setUrl(Url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(req.getResponseData());
                CommentService us = new CommentService(); 
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                return resultOK;

    }
        
      private ArrayList<Comment> parseComment(String json) {
        try {
            comments=new ArrayList<>();
            JSONParser parser=new JSONParser();
            Map<String, Object> commentsJson=parser.parseJSON(new CharArrayReader(json.toCharArray()));
            ArrayList<Map<String, Object>> liste=(ArrayList<Map<String, Object>>)commentsJson.get("root");
            for (Map<String, Object> obj : liste) {
                Comment c = new Comment();
                c.setId((int)Float.parseFloat(obj.get("id").toString()));
                c.setArticle((int)Float.parseFloat(((Map<String, Object>)obj.get("article_id")).get("id").toString()));
                c.setAuthor(obj.get("author").toString());
                c.setContent(obj.get("content").toString());
                c.setCreatedAt(obj.get("createdAt").toString());
                 comments.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return comments;
    }
    
}
