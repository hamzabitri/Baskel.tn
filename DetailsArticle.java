/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Article;
import Entities.Comment;
import Entities.User;
import Services.ArticleService;
import Services.CommentService;
import Services.ServiceUser;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import static com.sun.javafx.geom.Curve.prev;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author onsks
 */
public class DetailsArticle extends Form {
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    int idE,i;
 private ArrayList<Comment> comments;
 
    private Container listC;
    


        public DetailsArticle(Form previous, Article a) throws ParseException {
               Container C3= new Container(new BoxLayout(BoxLayout.Y_AXIS));
                       Label c = new Label("Commentaire");
 

        setTitle("Article");
        setLayout(BoxLayout.y());
        String img=a.getImage();
         try {
            enc=EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String url="http://localhost/untitled1/web/uploads/images/"+img;
        System.out.println(url);
        imgs =URLImage.createToStorage(enc, url, url ,URLImage.RESIZE_SCALE_TO_FILL);
        imgv = new ImageViewer(imgs);
       // Button like=new Button("Like");
        

      //************************************ add com
        TextComponent content = new TextComponent().labelAndHint("");
        FontImage.setMaterialIcon(content.getField().getHintLabel(), FontImage.MATERIAL_COMMENT);

        TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().horizontalSpan(2), content);

        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");

        Container conten = BorderLayout.center(comps);

        Button save = new Button("Commenter");
        conten.add(SOUTH, save);
        

        save.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent evt) {
                if ((content.getText().length() == 0)) {
                    Dialog.show("Alert", "vous ne pouvez pas ajouter un commentaire vide", new Command("OK"));
                } else {
                    try {

                        Comment u;
                            u = new Comment(content.getText());
                          String  username;
                          User us = ServiceUser.getInstance().ti;
                          username = us.getUsername();
                        if (!CommentService.getInstance().addComment(u,a.getId(),username)) {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        } else {
                            ToastBar.showMessage("adding...", FontImage.MATERIAL_INFO, 3);

                            Dialog.show("Success", "Comment Added", new Command("OK")); 
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "id number", new Command("OK"));
                    }

                }

            }

        });
      //************************************


            
        addAll(C3.add(imgv),new Label("Title: "+a.getTitle()), new SpanLabel("Content : "+a.getContent()), new SpanLabel("Created on: "+a.getDate()),conten);
        getToolbar().addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
        
       
    
}
