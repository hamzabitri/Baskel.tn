/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articletemoignage;

import com.jfoenix.controls.JFXButton;
import entities.Commentaire;
import entities.CommentaireAvalider;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import service.CommentaireAvaliderService;
import utils.DataBase;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class CommetAvaliderBackController implements Initializable {

    @FXML
    private TableView<CommentaireAvalider> listeComment;
    @FXML
    private TableColumn<CommentaireAvalider, String> champ;
    
    private final ObservableList<CommentaireAvalider> data = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb");
    
    private Statement ste;
    private Connection con;
    
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Aff();
    }    
    
    public void Aff(){
                        try {
            con = DataBase.getInstance().getCnx();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from avalider");
           //int idclient, int idarticle, String champ
            while(rs.next()){
                data.add(new CommentaireAvalider(rs.getString(5)));
                System.out.println(rs.getString(5));
            }

        } catch (SQLException e) {
                //Logger.getLogger(tab)
        }
               
         
            champ.setCellValueFactory(new PropertyValueFactory<>("champ"));
            
            listeComment.setItems(data);
            
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        listeComment.setItems(data);

             ObservableList<CommentaireAvalider> allDemandes,SingleDemandes ;
             allDemandes=listeComment.getItems();
             SingleDemandes=listeComment.getSelectionModel().getSelectedItems();
             CommentaireAvalider A = SingleDemandes.get(0);
             CommentaireAvaliderService STD = new CommentaireAvaliderService(); // STD = Service TAB DEMANDE
             STD.delete(A);
             SingleDemandes.forEach(allDemandes::remove);
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
        
      int idCo = 0,idCl = 0,idA = 0;
      
        listeComment.setItems(data);
             ObservableList<CommentaireAvalider> allDemandes,SingleDemandes ;
             allDemandes=listeComment.getItems();
             SingleDemandes=listeComment.getSelectionModel().getSelectedItems();
                          Commentaire A = new Commentaire(listeComment.getItems().get(0).getChamp());

             ResultSet rs = ste.executeQuery("select * from avalider where `champ`='" + A.getChamp() +"';");
             while(rs.next()){ 
                 idCo=rs.getInt(2);
             idCl=rs.getInt(3);
             idA=rs.getInt(4);}

            PreparedStatement pre=con.prepareStatement( "INSERT INTO `commentaire` (`idcomment`,`idclient`,`idarticle`,`champ`) VALUES (?,?,?,?);");
            System.out.println(idCo);
            System.out.println(idCl);
            System.out.println(idA);
            System.out.println(A.getChamp());
            pre.setInt(1,idCo); 
            pre.setInt(2, idCl); 
            pre.setInt(3,idA); 
            pre.setString(4,A.getChamp()); 
            pre.executeUpdate();     

            CommentaireAvalider B = SingleDemandes.get(0);
             CommentaireAvaliderService STD = new CommentaireAvaliderService(); // STD = Service TAB DEMANDE
             STD.delete(B);
             SingleDemandes.forEach(allDemandes::remove);  
    }
    
}
