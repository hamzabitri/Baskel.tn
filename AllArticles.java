/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entities.Article;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import Gui.Home;
import Services.ArticleService;
import com.codename1.l10n.ParseException;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 *
 * @author onsks
 */
public  class AllArticles extends Form{
    private AllArticles current;
    private ArrayList<Article> articles;
    private EncodedImage enc;
    private Container articleC;
        Image imgs;
    ImageViewer imgv;
    
    public AllArticles(String username) {
      
        current=this;
        Home t = new Home(username);
        t.addMenu(this, username);
                try {
            enc=EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        ArticleService as=new ArticleService();
        articles=as.getArticle();
        setTitle("Articles");
        setLayout(BoxLayout.y());
        ComboBox listeCat=new ComboBox();
        listeCat.addItem("Tout");
        articleC=new Container();
        articleC.setLayout(BoxLayout.y());
        for (Article a : articles)
            addArticle(a);
        add(articleC);

       
    }
    
    private void addArticle(Article a) {
        Container produitCA=new Container(BoxLayout.y());
        //URLImage imageUrl=URLImage.createToStorage(enc, Statics.IMAGE_URL+a.getPhoto(), Statics.IMAGE_URL+a.getPhoto(),URLImage.RESIZE_SCALE_TO_FILL);
        //ImageViewer image=new ImageViewer(imageUrl);
        
        Label des = new Label(a.getContent());
        Button details=new Button(a.getTitle());
                String img=a.getImage();
        String url="http://localhost/untitled1/web/uploads/images/"+img;
        System.out.println(url);
        imgs = URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try {
                    new DetailsArticle(current, a).show();
                  
                    
                } catch (ParseException ex) {
                   System.out.println(ex);
                }
            }
        });
        
        produitCA.addAll(imgv,details);        
        produitCA.setLeadComponent(details);
        articleC.add(produitCA);
        
    }
    
   
    
    /*public void addRese(Reservation r, String username) throws ParseException {
                Event ca=null;
                for (Event c : events) {
                    if (c.getId()==r.getIdE()) {
                        ca=c;
                        break;
                    }
                }
                //new MyRes(this, r, ca, username).show();
            }*/
   

}
