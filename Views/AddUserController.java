/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import Service.ServiceUser;
import Service.mailer;
import static Service.mailer.sendMail;
import Service.sms;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class AddUserController implements Initializable {

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
    @FXML
    private JFXComboBox<String> Sexe;
    @FXML
    private JFXTextField CIN;
    @FXML
    private JFXDatePicker Date;
    @FXML
    private JFXButton img;
    @FXML
    private ImageView imgButton;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton Btnannuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Sexe.getItems().addAll("Homme", "Femme");
        Sexe.getSelectionModel().select("Homme");
    }    
 public boolean validateNomP() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(Nom.getText());
        if (m.find() && m.group().equals(Nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillP() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(Email.getText());
        if (m.find() && m.group().equals(Email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateCINP() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(CIN.getText());
        if (m.find() && m.group().equals(CIN.getText()) && CIN.getText().length() == 8) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate CIN");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid CIN");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validatePasswordP() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (Password.getText().matches(pattern)) {

            if (Password.getText().equals(Password.getText())) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Check your password confirmation");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }
    }
    @FXML
    private void addImage(MouseEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\azizs\\src\\azizs\\image");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
    try {
            String nom = Nom.getText();
            String prenom = Prenom.getText();
            String email = Email.getText();
            String username = Username.getText();
            String password = Password.getText();
            String cin = CIN.getText();
            String sexe = Sexe.getValue();
            java.sql.Date date_naissance = java.sql.Date.valueOf(Date.getValue());
            String adresse = Adresse.getText();
            String num_tel = Ntel.getText();
            Image image1=null;
            image1 = imgButton.getImage();
            String photo_profil = null;
            photo_profil = saveToFileImageNormal(image1);
            
            
            ServiceUser sp = new ServiceUser();
            mailer sm = new mailer();
            User p = new User(email, username,  password,  prenom,  nom,  cin,  sexe,  date_naissance,  adresse,  num_tel,  photo_profil);
            sp.ajouter1(p);
            
            sms test=new sms();
            test.sensSms();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Compte crée avec succés ! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
            
            sm.sendMail( "aziz.sayeb@esprit.tn",  "Confirmation d'inscription",  "Inscription Réussie");
                /*JOptionPane.showMessageDialog(null, "Inscription reussie");*/
                Alert A1 = new Alert(Alert.AlertType.INFORMATION);
                A1.setTitle("Confirmation d'inscription");
                A1.setHeaderText("Inscription Réussie");
                A1.setContentText("Un mail de vérification a été envoyé, veuillez vérifier votre compte avant de vous connecter.");
                A1.showAndWait();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void Cancel(Stage Stage) {
        try {
            Parent root = FXMLLoader
        .load(getClass().getResource("Login.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage.setTitle("Baskel");
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void Cancel(ActionEvent event) {
        try {
            Parent root = FXMLLoader
        .load(getClass().getResource("Login.fxml"));
            
            
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void table(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
}
