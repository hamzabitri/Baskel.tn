/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.ui.FontImage;
import Entities.User;
import Services.ServiceUser;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import Entities.User;
import Services.ServiceUser;

/**
 *
 * @author Aziz Sayeb
 */
public class ProfilForm extends Form {
   Form current;
    Label lb_Username = new Label();
    Label lb_Email = new Label();

    ProfilForm( String username, Form previous) {
        current=this;
        Home a = new Home(username);
        a.addMenu(this, username);
        setTitle("Profile Informations");
        setLayout(BoxLayout.y());
        Container cnt1=new Container(new FlowLayout(Container.CENTER));
        Container cnt4=new Container(new FlowLayout(Container.CENTER));
        User t=ServiceUser.getInstance().getUser(username);
        lb_Username.setText("Username: " +username);
        lb_Email.setText("Email: " +t.getEmail());
        this.add(lb_Username);
        this.add(lb_Email);
        Button btnEditTask = new Button("Modifier Votre Profile");
        Button btnPassOubtTask = new Button("Modifier Votre Mot de passe");
        btnEditTask.addActionListener(e-> new EditForm(current,username).show());
        btnPassOubtTask.addActionListener(e-> new PasswordForm(username).show());
        
        
       cnt1.addAll(btnEditTask,btnPassOubtTask);
       Style connecterStyle=cnt1.getAllStyles();
       connecterStyle.setFgColor(0x4cc395);
       connecterStyle.setMargin(Component.TOP,1400); 
        
        addAll(cnt1);
        
     
}

    
}

    
