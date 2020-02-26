/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Admin;
import Entities.Client;
import Entities.User;
import Entities.UserSession;
import Service.SendMail;
import Service.ServiceAdmin;
import Service.ServiceUser;
import Service.Service_Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField Username;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXButton btnSignin;
    @FXML
    private JFXButton btnost;
    @FXML
    private JFXButton btnInscription;
    UserSession US;
    @FXML
    private Label lblSuccess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        US = UserSession.getInstace();
        System.out.println(US);
        SendMail s = new SendMail();
        int code = s.GenerateCode();
        System.out.println(code);
    }    

    @FXML
    public void Signin(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String username = Username.getText();
        String password = Password.getText();
        int verif1 = 0;
               
        Client C = new Client(username, password);
        C.setUsername(username);
        C.setPassword(password);
        Service_Client SA = new Service_Client();
        verif1 = SA.Singin(C);
         System.out.println(verif1);
            if (verif1 == 3){
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(C.Session(C).getNom());
            US.setPrenom(C.Session(C).getPrenom());
            US.setId(C.Session(C).getid());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/ModifierUser.fxml")));
            stage.setScene(scene);
            stage.show();
            }
           
    }

    @FXML
    public void Host(ActionEvent event) throws IOException, SQLException  {
        String username = Username.getText();
        String password = Password.getText();
        int verif1 = 0;
               
        Admin C = new Admin(username, password);
        C.setUsername(username);
        C.setPassword(password);
        ServiceAdmin SA = new ServiceAdmin();

        verif1 = SA.SingIn(C);
         System.out.println(verif1);
            if (verif1 == 3){
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(C.Session(C).getNom());
            US.setPrenom(C.Session(C).getPrenom());
            US.setId(C.Session(C).getid());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/AfficherUser.fxml")));
            stage.setScene(scene);
            stage.show();
            }
           
    }

     @FXML
    public void handleInscription() throws IOException {
        LoginController.showInscription();
    }
    
    public static boolean showInscription() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getResource("/Views/AddUser.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remplissez le formulaire d'inscription");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        return false;
    }
    
}
