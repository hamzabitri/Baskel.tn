/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import baskel.Entite.Produit;
import baskel.Service.ServiceProduit;
import baskel.Utils.DataBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
 

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class FrontProduitController implements Initializable {
@FXML
TableColumn<File, Image> imageColumn = TableColumnBuilder.<File, Image>create().text("Image").build();
@FXML
 TableColumn<File, String> pathColumn = TableColumnBuilder.<File, String>create().text("Path").build();
    @FXML
    private TableColumn<Produit, String> nomColumn;
    @FXML
    private TableColumn<File, Image> prixColumn;
    @FXML
    private TableColumn<Produit, String> descriptionColumn;
        @FXML
    private TableView<Produit> table;
        ServiceProduit stb = new ServiceProduit();

@FXML
        TableView<File> tableView = TableViewBuilder.<File>create().columns(imageColumn, pathColumn).columnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY).build();
private Statement ste;
    private Connection con;
    private final ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;
    @FXML
    private TableColumn<Produit, Integer> categoryColumn;
    @FXML
    private TableColumn<Produit, String> referenceColumn;
    @FXML
    private TableColumn<Produit, String> imageColumn1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         imageColumn.setCellValueFactory(new Callback<CellDataFeatures<File, Image>, ObservableValue<Image>>() {
            public ObservableValue<Image> call(CellDataFeatures<File, Image> p) {
                File file = p.getValue();
                return new SimpleObjectProperty<>(new Image(file.toURI().toString(), 100, 100, true, true, true));
            }
        });
        imageColumn.setCellFactory(new Callback<TableColumn<File, Image>, TableCell<File, Image>>(){
 
            @Override
           public TableCell<File, Image> call(TableColumn<File, Image> p) {
                return new TableCell<File, Image>(){
 
                    @Override
                   protected void updateItem(Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                       setGraphic(imageView);
                   }                    
               };
            }
        });
        
        
        //
       
        pathColumn.setCellValueFactory(new Callback<CellDataFeatures<File, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<File, String> p) {
                File file = p.getValue();
                return new SimpleStringProperty(file.getAbsolutePath());
            }
        });
        //
        //
        String path = "C:\\Users\\abdel\\Desktop\\Baskel";
        File folder = new File(path);
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
            }
        });
        if (files != null) {
            for (File file : files) {
                tableView.getItems().add(file);
            }
            
            
        }
        Aff();
       
    }
        // TODO
    
    
    
    
    
    
    
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
               
            
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
            prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
            
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            
            
            
            System.out.println(data);                    
                    table.setItems(data);
            table.setEditable(true);
            
                        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                       // prixColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            





    }
    }    
    






/**
 *
 * @author fabriceb
 */
