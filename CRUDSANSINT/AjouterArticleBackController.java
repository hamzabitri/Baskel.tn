/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articletemoignage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Article;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import javafx.event.ActionEvent;
import service.*;


/**
 * FXML Controller class
 *
 * @author onsks
 */
public class AjouterArticleBackController implements Initializable {

    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXButton ajout;
    @FXML
    private ImageView imgbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
   
    public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        //File dir = new File("C:\\Users\\Omar\\Desktop\\ArticleTemoignage\\src\\images");
        //"C:\Users\Omar\Desktop\ArticleTemoignage\src\images"
      File dir = new File("C:\\wamp64\\www\\pi\\image");
      // File dir = new File("http://localhost:8888/pi/image");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
      //  String name1 = String.format("http://localhost/pi/image/%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
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
    private void ajouter(ActionEvent event) throws IOException {
      
        try {
            
            String tittle = titre.getText();
            String descr = desc.getText();
            Image image1=null;
             image1 = imgbtn.getImage();
            String photo = null;
             photo = saveToFileImageNormal(image1);
            int idClient = 1;
             
            ArticleService as = new ArticleService();
           Article a = new Article(idClient, tittle, descr, photo);
            as.ajouter1(a);
            
          /* FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherProduits.fxml"));
            try {
                Parent root = loader.load();
                AfficherProduitsController lpc = loader.getController();

                
                ajouter.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }*/
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
    }

    @FXML
    private void addImage(javafx.scene.input.MouseEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgbtn.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
