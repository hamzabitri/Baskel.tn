/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Client;
import Service.Service_Client;
import Entities.UserSession;
import Service.SendMail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class ModifierUserController implements Initializable {

    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField Password;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Ntel;
    UserSession us;
    @FXML
    private Label lblWelcome;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private Button btnLogOut;
    @FXML
    private JFXButton btnDesactiver;
    @FXML
    private Rectangle image;
    @FXML
    private Circle image2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Client R = new Client();
        us = UserSession.getInstace();
        System.out.println(us);
        int id_user = us.getId();
        
        lblWelcome.setText(us.getNom() + " " + us.getPrenom());

        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public Client Chercher(int id_user) throws SQLException {
        
        Client R = new Client();
        Service_Client sc = new Service_Client();
        R = sc.chercher(id_user);
        return R;
    }

    public void Afficher() throws SQLException {
        Client R = new Client();
        R = Chercher(us.getId());
        Username.setText(R.getUsername());

        Nom.setText(R.getNom());
       
        Prenom.setText(R.getPrenom());
    
        Email.setText(R.getEmail());
        
        Ntel.setText(R.getNum_tel());
        
        Adresse.setText(R.getAdresse());
        
        Password.setText(R.getPassword());
        
        String photo=R.getPhoto_profil();
        Image imageURL= new Image("file:///C:/wamp64/www/azizs/src/azizs/image/" + photo);
        System.out.println(imageURL);
        image.setFill(new ImagePattern(imageURL));
        image2.setFill(new ImagePattern(imageURL));
      

    }

     @FXML
    public void Modifier(ActionEvent event) throws SQLException {

        String email = Email.getText();
        String username = Username.getText();
        String password = Password.getText();
        String prenom = Prenom.getText();
        String nom = Nom.getText();
        String adresse = Adresse.getText();
        String num_tel = Ntel.getText();
        SendMail sm = new SendMail();
        Client P = new Client(us.getId(), email, username,password, prenom, nom, adresse, num_tel);
        Service_Client ss = new Service_Client();
        int i = 0;
        i = ss.update(P);
        System.out.println(i);
        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modifier Profil");
            alert.setHeaderText(null);
            alert.setContentText("Votre profil a été modifié");
            alert.showAndWait();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Votre profil a été modifié ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        }
    }
    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        Node node;
        node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void Desactiver(ActionEvent event) throws SQLException {
        
        Service_Client sc = new Service_Client();
        sc.Delete(us.getId());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Désactiver Compte");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été désactivé");
        alert.showAndWait();

    }
    
}
