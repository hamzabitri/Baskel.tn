/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.gui;

import baskel.Entite.CategorieP;
import baskel.Entite.Produit;
import baskel.Utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class StatistiqueController implements Initializable {
        @FXML
    private BarChart<String, Integer> barChart;
    private final ObservableList<String> data = FXCollections.observableArrayList();
        private final ObservableList<Produit> data1 = FXCollections.observableArrayList();


    @FXML
    private CategoryAxis xAxis;

    private Statement ste;
    private Connection con;
    private ObservableList<String> categorieNames = FXCollections.observableArrayList();
    @FXML
    private NumberAxis yAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int n=0;
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from category");
                            //System.out.println(rs);
            while(rs.next()){
                
                
                
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs1 = ste.executeQuery("select id_categorie from produit");
                            //System.out.println(rs);
            while(rs1.next()){
                //data.add(rs.getString(2));
                if(rs1.getInt(1)==rs.getInt(1))
                    n++;
                series.getData().add(new XYChart.Data(rs.getString(2),n));
                
                    

            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
                        n=0;
                
                
                
                //data.add(rs.getString(2));
                //series.getData().add(new XYChart.Data(rs.getString(2),10));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
        
        
        //series.getData().add(new XYChart.Data("james",10));
        barChart.getData().addAll(series);
        
        
        
        // Get an array with the English month names.
        
        /*
         try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select name from category");
                            //System.out.println(rs);
            while(rs.next()){
                data.add(rs.getString(2));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        //categoryNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(data);
                 try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Produit");
                            //System.out.println(rs);
            while(rs.next()){
                data1.add(new Produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getString(7),rs.getString(8)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
        
        // TODO*/
    }     
    
    
    
    
    public void setProduitData(List<Produit> produits) {
        // Count the number of people having their birthday in a specific month.
        int[] catCounter = new int[12];
        for (Produit p : produits) {
            int cat = p.getCategorie();
            catCounter[cat]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < catCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(data.get(i), catCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
}
