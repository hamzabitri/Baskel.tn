/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import baskel.Entite.CategorieP;
import baskel.Entite.Produit;
import baskel.Utils.DataBase;
import baskel.IService.IService;
import baskel.Service.ServiceCategorie;
import baskel.Service.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class AfficherCatPController implements Initializable {


    @FXML
    private TableColumn<CategorieP, String> resNomC;
    
    
    private final ObservableList<CategorieP> data = FXCollections.observableArrayList();
     ServiceCategorie stb = new ServiceCategorie();
    
    ObservableList<String> list = FXCollections.observableArrayList("ok","cc");
    
        private Statement ste;
    private Connection con;
    @FXML
    private Button supprimer;

    @FXML
    private Button AddC;
    
   
    @FXML
    private TableView<CategorieP> tableX;
    @FXML
    private Button GestEv;
    @FXML
    private TextField rechercher;
    @FXML
    private Button addProd;


    public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from category");
                            //System.out.println(rs);
            while(rs.next()){
                data.add(new CategorieP(rs.getInt(1),rs.getString(2)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
 
            resNomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
            
            tableX.setItems(data);
            tableX.setEditable(true);
            
            resNomC.setCellFactory(TextFieldTableCell.forTableColumn());

            




    }
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
    private void supprimer(ActionEvent event) throws SQLException {
        
             tableX.setItems(data);
             ObservableList<CategorieP> allDemandes,SingleDemandes ;
             allDemandes=tableX.getItems();
             SingleDemandes=tableX.getSelectionModel().getSelectedItems();
             CategorieP A = SingleDemandes.get(0);
             ServiceCategorie STD = new ServiceCategorie(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
        
    }
    
       public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CategorieP> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(CategorieP -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (CategorieP.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                
                                
				else if (String.valueOf(CategorieP.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CategorieP> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableX.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableX.setItems(sortedData);
    }

    @FXML
    private void AddCategorie(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddCatP.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }



    @FXML
    private void ChangerNom(TableColumn.CellEditEvent cc) throws SQLException {
     CategorieP tab_Demandeselected = tableX.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(cc.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
    }

    @FXML
    private void addProd(ActionEvent event) throws IOException {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void GestEv(ActionEvent event) {
    }
    




    
}
