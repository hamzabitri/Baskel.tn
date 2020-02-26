/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import baskel.Entite.CategorieP;
import baskel.Service.ServiceCategorie;
import baskel.Service.ServiceProduit;
import static baskel.gui.AddProduitController.saveToFileImageNormal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class AddCatPController implements Initializable {

    @FXML
    private TextField NomC;
    @FXML
    private Button ValiderC;
    @FXML
    private Button TableC;
    @FXML
    private Button GesttE;
    @FXML
    private Button addprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String nomC = NomC.getText();
        ServiceCategorie sp = new ServiceCategorie();
        CategorieP p = new CategorieP(nomC);
        sp.ajouter(p);
        FXMLLoader loader = new FXMLLoader
                                (getClass()
                                        .getResource("AfficherCatP.fxml"));
        try {
            Parent root = loader.load();
            AfficherCatPController apc = loader.getController();
            
            
            NomC.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void table(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherCatP.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }



    @FXML
    private void addprod(ActionEvent event) throws IOException {
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
}
