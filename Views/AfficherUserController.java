/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import Entities.UserSession;
import Service.SendEmail;
import Service.ServiceUser;
import Service.mailer;
import com.jfoenix.controls.JFXButton;
import connexion.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class AfficherUserController implements Initializable {

    @FXML
    private TextField rechercher;
    @FXML
    private JFXButton Delete;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> emailt;
    @FXML
    private TableColumn<User, String> nomt;
    @FXML
    private TableColumn<User, String> prenomt;
    @FXML
    private TableColumn<User, String> cint;
    @FXML
    private TableColumn<User, String> adresset;
    @FXML
    private TableColumn<User, String> numt;
    @FXML
    private TableColumn<User, String> usernamet;
    
    private final ObservableList<User> data = FXCollections.observableArrayList();
     ServiceUser stb = new ServiceUser();
    
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
    private Statement ste;
    private Connection con;

    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblWelcome;
        UserSession us;


     public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("SELECT * FROM `test` WHERE `role`= 1 ");
            
            while(rs.next()){
               
                data.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("nom"),rs.getString("prenom"),rs.getString("cin"),rs.getString("adresse"),rs.getString("num_tel"),rs.getString("username")));

            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            emailt.setCellValueFactory(new PropertyValueFactory<>("email"));
            nomt.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomt.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cint.setCellValueFactory(new PropertyValueFactory<>("cin"));
            adresset.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            numt.setCellValueFactory(new PropertyValueFactory<>("Num_tel"));
            usernamet.setCellValueFactory(new PropertyValueFactory<>("username"));
         

            
            table.setItems(data);
            table.setEditable(true);
            
            nomt.setCellFactory(TextFieldTableCell.forTableColumn());
            usernamet.setCellFactory(TextFieldTableCell.forTableColumn());
            prenomt.setCellFactory(TextFieldTableCell.forTableColumn());
            adresset.setCellFactory(TextFieldTableCell.forTableColumn());
            numt.setCellFactory(TextFieldTableCell.forTableColumn());
            




    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();
        us = UserSession.getInstace();
        lblWelcome.setText(us.getNom() + " " + us.getPrenom());
    }    
    
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
               table.setItems(data);

             ObservableList<User> allDemandes,SingleDemandes ;
             allDemandes=table.getItems();
             SingleDemandes=table.getSelectionModel().getSelectedItems();
             User A = SingleDemandes.get(0);
             System.out.println(A.getid());
             ServiceUser STD = new ServiceUser(); // STD = Service TAB DEMANDE
             mailer sm = new mailer();
             STD.delete(A.getid());
             TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Compte supprimer! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
             sm.sendMail( "aziz.sayeb@esprit.tn",  "Compte supprimer",  "votre compte est supprimer avec succés ");
             SingleDemandes.forEach(allDemandes::remove);
             
             

    }
    
     public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                
                                if (event.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
                                if (event.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				else if (String.valueOf(event.getid()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
     
     @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {
     User tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }
    
    @FXML
    private void ChangerPrenom(TableColumn.CellEditEvent bb) throws SQLException {
     User tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }
    
    @FXML
    private void ChangerUsername(TableColumn.CellEditEvent bb) throws SQLException {
     User tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setUsername(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }
    
    @FXML
    private void ChangerAdresse(TableColumn.CellEditEvent bb) throws SQLException {
     User tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setAdresse(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        Node node;
        node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        mailer sm = new mailer();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/Login.fxml")));
        sm.sendMail( "aziz.sayeb@esprit.tn",  "Inscrit",  "Compte crée");
        stage.setScene(scene);
        stage.show();
    }

    
}
