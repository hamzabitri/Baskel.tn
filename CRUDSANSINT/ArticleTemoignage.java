
package articletemoignage;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author onsks
 */
public class ArticleTemoignage extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
    //Parent root = FXMLLoader.load(getClass().getResource("AjouterArticleBack.fxml"));
    Parent root = FXMLLoader.load(getClass().getResource("AfficherBlogFront.fxml"));
   //  Parent root = FXMLLoader.load(getClass().getResource("AjouterArticleBack.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("CommetAvaliderBack.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
