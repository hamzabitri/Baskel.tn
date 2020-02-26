/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import static baskel.gui.AddProduitController.saveToFileImageNormal;
import baskel.Entite.Produit;
import baskel.Entite.Produit;
import baskel.Service.ServiceProduit;
import baskel.Utils.DataBase;
import com.restfb.types.StoryAttachment.Media;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.apache.commons.lang3.RandomStringUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AddProduitController implements Initializable {

    @FXML
    private TextField desP;
    @FXML
    private TextField NomP;
    @FXML
    private TextField refP;
    @FXML
    private Button GestionCat;
    @FXML
    private ImageView btnimage;
    @FXML
    private Button Table;
    @FXML
    private Button bValider;
    @FXML
    private TextField quanP;
    @FXML
    private TextField prixP;
    @FXML
    private ComboBox<String> CatP;
private Connection con;
        private Statement ste;
        ObservableList<String> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try { con = DataBase.getInstance().getConnection();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("SELECT name FROM category");
        
        while (rs.next())
        {list.add(rs.getString("name"));}
        
        CatP.setItems(list);
        } catch(SQLException e) {}
    }    

    @FXML
    private void gestionCat(ActionEvent event) throws IOException {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddCatP.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }


    @FXML
    private void afficherP(ActionEvent event) throws IOException {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            String nom = NomP.getText();
            String ref = refP.getText();
            String des = desP.getText();
            float prix = Float.valueOf(prixP.getText());
            int quantite = Integer.valueOf(quanP.getText());
            String category= (String) CatP.getValue();
            ResultSet rs=ste.executeQuery("select id_cp from category where name='"+category+"'");

        Image image1=null;
        image1 = btnimage.getImage();
        String image = null;
        System.out.println("gdfg"+image1);
        
        image = saveToFileImageNormal(image1);
            
            
            if(nom==null && ref==null && des==null && prix==0 && quantite==0 && category==null)
            {
             alert1("DENIED APPLICATION","YOU ALREADY DENIED THIS APPLICATION");
            }
            else {
            ServiceProduit sp = new ServiceProduit();
            if(rs.next()){
            Produit p = new Produit(nom, ref, rs.getInt(1), quantite, prix, image, des);
            sp.ajouter(p);
            sp.mail("yaas");
            }}
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherProduit.fxml"));
            try {
                Parent root = loader.load();
                AfficherProduitController apc = loader.getController();

                
                NomP.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
        TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Un Produit est mis à jour ! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        
        
//        Email email = new Email("montassar43@gmail.com","montassar007");
//        email.setFrom("montassar43@gmail.com", "Solidarity With Refugees");
//        email.setSubject("Get your password");
//        email.setContent("<h1>This is your verification code :</h1>"+a, "text/html");
//        email.addRecipient(mai);
//        email.send();
        
        
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
            btnimage.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:/Users/Abdel/Desktop/Baskel");
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
    private void alert1(String Message,String Accept) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText(Accept);
        a1.setContentText(Message);
        a1.showAndWait();
    }
        private void table(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
}
