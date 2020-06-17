/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import Entities.User;
import Services.ServiceUser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.FontImage;
import com.sendgrid.SendGrid;
import org.mindrot.jbcrypt.BCrypt;



/**
 *
 * @author Aziz Sayeb
 */
public class LoginForm extends Form{
   Form current;
    public  static int ID=0;
ImageViewer iv1;

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField Username = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField tpassword = new com.codename1.ui.TextField();

    private com.codename1.ui.Button btnval = new com.codename1.ui.Button();
    private com.codename1.ui.Button btnAddTask = new com.codename1.ui.Button();
    private com.codename1.ui.Button motOublier = new com.codename1.ui.Button();
    private com.codename1.ui.Button Facebook = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();

 public LoginForm(Resources theme){
        current=this;
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Connectez-vous");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(Username);
        gui_Component_Group_1.addComponent(tpassword);
        Username.setHint("                    Username");
        Username.setName("Text_Field_2");
        Username.setUIID("HugeDarkLabel1");
        tpassword.setHint("                    Password");
        tpassword.setName("Text_Field_1");
        tpassword.setUIID("HugeDarkLabel1");
        getTitleArea().setUIID("Container");

        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_EXIT_TO_APP, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
        gui_Container_1.addComponent(btnval);
        gui_Container_1.addComponent(motOublier);
        gui_Container_1.addComponent(btnAddTask);
        gui_Container_1.addComponent(Facebook);

        gui_Component_Group_1.setName("Component_Group_1");
        btnval.setText("Sign In");
        btnval.setName("Button_2");

        motOublier.setText("Forgot Your Password");
        motOublier.setUIID("CenterLabelSmall");
        motOublier.setName("Button_3");
        Facebook.setText("Connect with Facebook account");
        Facebook.setUIID("CenterLabelSmall");
        Facebook.setName("Button_3");
        btnAddTask.setText("Create a new account");
        btnAddTask.setUIID("CenterLabelSmall");
        btnAddTask.setName("Button_3");
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        Style connecterStyle=gui_Component_Group_1.getAllStyles();
        connecterStyle.setMargin(Component.TOP,750); 
        Style motOublierStyle=motOublier.getAllStyles();
        motOublierStyle.setMargin(Component.TOP,700); 
        Container cnt1=new Container(new FlowLayout(Container.CENTER));
        Container cnt4=new Container(new FlowLayout(Container.CENTER));
        Container cnt5=new Container(new FlowLayout(Container.CENTER));

        Container cnt2=new Container(new FlowLayout(Container.CENTER));
        Container cnt3=new Container(new FlowLayout(Container.CENTER));
                
        Label Seconnecter = new Label("Se connecter");
      
         String clientId = "861902307638537";
                String redirectURI = "{server}/auth/facebook";
                String clientSecret = "6cbc07589913ba6e6bb3b7191a8a8fc2";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                //Sets a LoginCallback listener
                fb.setCallback(new LoginCallback() {
    @Override
    public void loginSuccessful() {
        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionRequest.setPost(false);
        connectionRequest.setUrl(
                "{server}/auth/facebook/callback?access_token="
        + fb.getAccessToken().getToken());
        connectionRequest.addResponseListener(event -> {
            Log.p(new String(connectionRequest.getResponseData()));
        });
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    @Override
    public void loginFailed(String errorMessage) {
        Dialog.show("No!", "it did not work!", "sad", null);
    }
});
//        TextField Username = new TextField("", "saisir votre username");
//        TextField tpassword = new TextField();

        tpassword.setConstraint(TextField.PASSWORD);

       
        
/***********************************/
               btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Username.getText().length()==0)||(tpassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {               // pw = pw.replace("$2y$", "$2a$");

User t=ServiceUser.getInstance().getUser(Username.getText());
String pw = t.getPassword();
                        System.out.println("1");
String pw1=pw.substring(4);
String pw11="$2a$"+pw1;
                System.out.println("pw1 = " +pw11);
//                pw = pw.replace("$2y$", "$2a$");
                
                        if(t.equals(null))
                        {
                            
                                            Dialog.show("ERROR", "User not found", new Command("OK"));
                                            }

                        else if(BCrypt.checkpw(tpassword.getText(), pw11)==false){
                                                                    Dialog.show("ERROR", "Mot de passe invalide", new Command("OK"));

                        }
                                
                                else{
                          
                          
                            System.out.println(pw);
                            ID=t.getId();
                           new Home(Username.getText()).show();
                            
                        
                        }
                  
                    } catch (NumberFormatException e) {
                        System.out.println("1");
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
               Facebook.addActionListener(event -> {
    //trigger the login if not already logged in
    if(!fb.isUserLoggedIn()){
        fb.doLogin();
    }else{
        //get the token and now you can query the facebook API
        String token = fb.getAccessToken().getToken();
    }
});
               
               
               
               motOublier.addActionListener(e-> new MotDePasseOublier().show()); 
        btnAddTask.addActionListener(e-> new AddForm(current).show());
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});

}

}
 
 
 
 
 