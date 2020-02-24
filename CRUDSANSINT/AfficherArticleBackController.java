/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articletemoignage;

import com.jfoenix.controls.JFXButton;
import entities.Article;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import service.ArticleService;
import utils.DataBase;

/**
 * FXML Controller class
 *
 * @author onsks
 */
public class AfficherArticleBackController implements Initializable {

    @FXML
    private TableView<Article> listeArticle;
    @FXML
    private TableColumn<Article, String> titre;
    @FXML
    private TableColumn<Article, String> description;
    @FXML
    private TableColumn<Article, String> img;

    private final ObservableList<Article> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
    private Statement ste;
    private Connection con;
    @FXML
    private JFXButton supprimer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Aff();
    }    
     public void Aff(){
                        try {
                            ArticleService as = new ArticleService();
            con = DataBase.getInstance().getCnx();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from article");
            while(rs.next()){
                data.add(new Article(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               

            titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            img.setCellValueFactory(new PropertyValueFactory<>("ImageA"));
            
            listeArticle.setItems(data);
            listeArticle.setEditable(true);
            
  
           titre.setCellFactory(TextFieldTableCell.forTableColumn());
            description.setCellFactory(TextFieldTableCell.forTableColumn());
                       

    }
     
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
               listeArticle.setItems(data);

             ObservableList<Article> allDemandes,SingleDemandes ;
             allDemandes=listeArticle.getItems();
             SingleDemandes=listeArticle.getSelectionModel().getSelectedItems();
             Article A = SingleDemandes.get(0);
             ArticleService STD = new ArticleService(); // STD = Service TAB DEMANDE
             STD.delete(A);
             SingleDemandes.forEach(allDemandes::remove);
    }
    
    
    @FXML
    private void ChangerNom(TableColumn.CellEditEvent bb) throws SQLException {
     Article tab_Demandeselected = listeArticle.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setTitre(bb.getNewValue().toString());
                  ArticleService stb = new ArticleService(); // STD = Service TAB DEMANDE

     stb.updatetab(tab_Demandeselected);
    }
    
    @FXML
    private void ChangerDescription(TableColumn.CellEditEvent bb) throws SQLException {
     Article tab_Demandeselected = listeArticle.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setDescription(bb.getNewValue().toString());
     ArticleService stb = new ArticleService(); // STD = Service TAB DEMANDE
     stb.updatetab(tab_Demandeselected);
    }
}
