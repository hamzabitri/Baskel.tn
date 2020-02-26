/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import baskel.Entite.Produit;
import baskel.Entite.Produit;
import baskel.Service.ServiceProduit;
import baskel.Service.ServiceProduit;
import baskel.Utils.DataBase;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> nomP;
    @FXML
    private TableColumn<Produit, String> refP;
    @FXML
    private TableColumn<?, ?> catP;
    @FXML
    private TableColumn<Produit, Integer> quanP;
    @FXML
    private TableColumn<Produit, Float> prixP;
    @FXML
    private TableColumn<Produit, String> imageP;
    @FXML
    private TableColumn<Produit, String> desP;
    @FXML
    private Button AddP;
    @FXML
    private Button supprimer;
    @FXML
    private TextField rechercher;
    
    private final ObservableList<Produit> data = FXCollections.observableArrayList();
     ServiceProduit stb = new ServiceProduit();
    
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
    private Statement ste;
    private Connection con;
    @FXML
    private Button addcatP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Aff();
        RechercheAV();
        // TODO
    }    

    @FXML
    private void ChangerNom(TableColumn.CellEditEvent cc) throws SQLException {
     Produit tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(cc.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }
    @FXML
    private void ChangerRef(TableColumn.CellEditEvent bb) throws SQLException {
     Produit tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDescription(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerDes(TableColumn.CellEditEvent bb) throws SQLException {
     Produit tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDescription(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerPrix(TableColumn.CellEditEvent bb) throws SQLException {
     Produit tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDescription(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }

    @FXML
    private void ChangerQuantite(TableColumn.CellEditEvent bb) throws SQLException {
     Produit tab_Demandeselected = table.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDescription(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }


    @FXML
    private void AddProduit(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }



     @FXML
    private void supprimer(ActionEvent event) throws SQLException {
               table.setItems(data);

             ObservableList<Produit> allDemandes,SingleDemandes ;
             allDemandes=table.getItems();
             SingleDemandes=table.getSelectionModel().getSelectedItems();
             Produit A = SingleDemandes.get(0);
             ServiceProduit STD = new ServiceProduit(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             

    }
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                if (produit.getReference().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                if (String.valueOf(produit.getCategorie()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				}
                                if (String.valueOf(produit.getQuantite()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				}
                                if (produit.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
                                if (String.valueOf(produit.getPrix()).indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				else if (String.valueOf(produit.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
     public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from produit");
            while(rs.next()){
                data.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getString(7),rs.getString(8)));

            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
            refP.setCellValueFactory(new PropertyValueFactory<>("reference"));
            catP.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            quanP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
            imageP.setCellValueFactory(new PropertyValueFactory<>("image"));
            desP.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            table.setItems(data);
            table.setEditable(true);
            
            nomP.setCellFactory(TextFieldTableCell.forTableColumn());
            refP.setCellFactory(TextFieldTableCell.forTableColumn());
            //quanP.setCellFactory(TextFieldTableCell.forTableColumn());
                    quanP.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                    prixP.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

            //prixP.setCellFactory(TextFieldTableCell.forTableColumn());
            desP.setCellFactory(TextFieldTableCell.forTableColumn());





    }

    @FXML
    private void addcatP(ActionEvent event) throws IOException {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddCatP.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
}
