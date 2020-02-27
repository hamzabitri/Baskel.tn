/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.Entite.Personne;
import com.esprit.Service.ServicePersonne;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author House
 */
public class AddPersonController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button bValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            String nomP = tfNom.getText();
            String prenomP = tfPrenom.getText();
            ServicePersonne sp = new ServicePersonne();
            Personne p = new Personne(nomP, prenomP, 14);
            sp.ajouter(p);
            
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AffichePersonne.fxml"));
            try {
                Parent root = loader.load();
                AffichePersonneController apc = loader.getController();
                apc.setResNom(tfNom);
                apc.setResPrenom(tfPrenom);
                tfNom.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
