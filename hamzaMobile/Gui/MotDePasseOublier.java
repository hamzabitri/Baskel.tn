/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Entities.User;
import Services.ServiceUser;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;


/**
 *
 * @author Aziz Sayeb
 */
public class MotDePasseOublier extends Form {
    Form current;
Form previous;
Resources theme;

private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    // optional, make it more random
//    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
//    private static final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;

    private static final SecureRandom random = new SecureRandom();
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
      
    private com.codename1.ui.TextField tEmail = new com.codename1.ui.TextField();
    private com.codename1.ui.Button btnPass = new com.codename1.ui.Button();
    String pasw;
    public MotDePasseOublier() {
        InboxForm(theme);
        
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_2);
        gui_Container_2.setScrollableY(true);
        gui_Container_2.setName("Container_2");

        gui_Container_2.setName("Component_Group_2");
        gui_Container_2.addComponent(tEmail);
        tEmail.setHint("please enter your Email");
        tEmail.setName("Text_Field_2");
        tEmail.setUIID("CenterLabel1");
        gui_Container_2.addComponent(btnPass);
        gui_Container_2.setName("Component_Group_2");
        btnPass.setText("Send");
        btnPass.setUIID("Button2");
        btnPass.setName("Button_2");
        Style connecterStyle=btnPass.getAllStyles();
       connecterStyle.setMargin(Component.TOP,50); 
       Style emailStyle=tEmail.getAllStyles();
//       emailStyle.setFgColor(0xffffff);
       emailStyle.setMargin(Component.TOP,50); 
        
        
        btnPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            User u=ServiceUser.getInstance().getUser1(tEmail.getText());
                for (int i = 0; i < 1; i++) {
                     pasw=generateRandomPassword(15);
            System.out.println("password : " + pasw);
            System.out.println("\n");
        }
                     User t = new User(u.getId(),pasw);
                        if( ServiceUser.getInstance().MotDePasseOublier(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            ServiceUser.getInstance().sendMail(t);
                             
                        }
                        
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                
                
            new LoginForm(theme).show();
            }
        
        });
                 
        
    }
    
    public static String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
            char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }
     public void InboxForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Forgot Password ?","Title")
                        
                        
                )
        );}
//    public static String shuffleString(String a) {
//        List<String> letters = Arrays.asList(a.split(""));
//        Collections.shuffle(letters);
//        return letters.stream().collect(Collectors.joining());
//    }
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
                       
               private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
          setUIID("password");
//        setTitle("");
//        setName("passwordForm");
        
    }// </editor-fold>     
                
    }
