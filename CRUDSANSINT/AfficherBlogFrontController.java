/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articletemoignage;

import entities.Article;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.ArticleService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherBlogFrontController implements Initializable {

    @FXML
    private VBox InboxVB; // le grand contenaire de tout les messages
//    @FXML
//    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        ArticleService fs =null;
        try {
            fs = new ArticleService();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherBlogFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Article> us = null;
        try {
            us = fs.readAll(); // id user seesion
        } catch (SQLException ex) {
            Logger.getLogger(AfficherBlogFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator iterator = us.iterator();

        VBox vbm;
// calcul total unread messages !!! ( think about refresh after reply )
       // long nb = fs.countNbUnreadMessages(2);
      //  String nbtext = Long.toString(nb);
        Label nbUnreadMessages = new Label("Blog:");
//        nbUnreadMessages.setFont(Font.font("Amble CN", FontWeight.BOLD, 14));
//        nbUnreadMessages.setTextFill(Color.web("#FF0000"));
//        nbUnreadMessages.setMaxWidth(Double.MAX_VALUE);
//        nbUnreadMessages.setAlignment(Pos.CENTER);
//        nbUnreadMessages.setPadding(new Insets(15, 20, 20, 20));
       

        InboxVB.getChildren().add(nbUnreadMessages);
        for (int i = 0; i < us.size(); i++) {
            vbm = new VBox(10);
            vbm.setPadding(new Insets(20, 20, 20, 20));
            // vbm.setSpacing(10);
            vbm.setSpacing(20); // espace horizontal entre deux images
            for (int j = 0; j < 3; j++) {
                if (iterator.hasNext()) {
                    vbm.getChildren().add(createVBMessage((Article) iterator.next()));
                }
            }
            InboxVB.getChildren().add(vbm);

        }

    }

    public VBox createVBMessage(Article m) {
        VBox vbmsg = new VBox(); // le petit contenaire pour un seul message
        HBox hb_petit = new HBox();
        vbmsg.setStyle("background-color : #ffffff; padding-right: 0px");
        vbmsg.setSpacing(10);
         ImageView img = new ImageView(new Image(m.getImageA())); //f.getPicturePath()
        //http://localhost/pi/image/f.getPicturePath()
        img.setPreserveRatio(false);
        img.setX(100);
        img.setY(100);
        img.setFitHeight(220);
        img.setFitWidth(170);
        Label nomSender = new Label(m.getTitre());
      

        nomSender.setFont(Font.font("Amble CN", FontWeight.BOLD, 20));
          Label messageBody = new Label(m.getDescription());
        messageBody.setFont(Font.font("Amble CN", FontWeight.BOLD, 15));

//        TextArea t = new TextArea();
//        t.setPromptText("Type your reply here.");
//
//        Button reply = new Button("Reply");
//        Button delete = new Button("delete");

//        reply.setStyle("-fx-background-color: #FF4500; -fx-text-fill: #000000; -fx-font-size: 15;");
//        delete.setStyle("-fx-background-color: #FF4500; -fx-text-fill: #000000; -fx-font-size: 15;");

//        reply.setOnAction((event) -> {
//            System.out.println(t.getText());
//            MessageBoxServices mbs = new MessageBoxServices();
//
//            mbs.sendMessage(new MessageBox(m.getId(), m.getSenderID(), m.getReceiverID(), t.getText(), m.getSenderFirstName(), m.getSenderLastName()));
//            MessageBoxServices msgRead = new MessageBoxServices();
//            System.out.println(m.getId());
//            System.out.println(m.getSenderFirstName());
//            System.out.println("id message reply = 1: " + m.getId());
//            msgRead.setMessageAsRead(m.getId());    // user has replies to this message ça n'a pas marché
//            System.out.println("your message has been sent successfully");
//
//            // refresh ou afficher  l'historique de discussion
//            // refreeeeeesh  required here !!!!
//            
//            
////        MessageBoxServices fs = new MessageBoxServices();    
////        long nb = fs.countNbUnreadMessages(2);
////        String nbtext = Long.toString(nb);
////        Label nbUnreadMessages = new Label("Total of unread messages: " + nbtext);
////    
//            
//            
//            
//            
//            Parent root;
//
//        });

//        // delete
//        delete.setOnAction((event) -> {
//            System.out.println(t.getText());
//            MessageBoxServices mbs = new MessageBoxServices();
//
//            mbs.deleteMessage(m.getId());
//
//            System.out.println("suppression message id = " + m.getId());
//
//            System.out.println("your message has been deleted successfully");
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText(null);
//            alert.setTitle("Delete");
//            alert.setContentText("Message deleted successfully!");
//
//            alert.showAndWait();
//         //*******************REFRESH *******************************
//              InboxVB.getChildren().clear();
//              
//        MessageBoxServices fs = new MessageBoxServices();
//        List<MessageBox> us = fs.getMessageByIdReceiver(2); // id user seesion
//        Iterator iterator = us.iterator();
//
//        VBox vbm;
//// calcul total unread messages !!! ( think about refresh after reply )
//        long nb = fs.countNbUnreadMessages(2);
//        String nbtext = Long.toString(nb);
//        Label nbUnreadMessages = new Label("Total of unread messages: " + nbtext);
//        nbUnreadMessages.setFont(Font.font("Amble CN", FontWeight.BOLD, 14));
//        nbUnreadMessages.setTextFill(Color.web("#FF0000"));
//        nbUnreadMessages.setMaxWidth(Double.MAX_VALUE);
//        nbUnreadMessages.setAlignment(Pos.CENTER);
//        nbUnreadMessages.setPadding(new Insets(15, 20, 20, 20));
//       
//
//        InboxVB.getChildren().add(nbUnreadMessages);
//        for (int i = 0; i < us.size(); i++) {
//            vbm = new VBox(10);
//            vbm.setPadding(new Insets(20, 20, 20, 20));
//            // vbm.setSpacing(10);
//            vbm.setSpacing(20); // espace horizontal entre deux images
//            for (int j = 0; j < 3; j++) {
//                if (iterator.hasNext()) {
//                    vbm.getChildren().add(createVBMessage((MessageBox) iterator.next()));
//                }
//            }
//            InboxVB.getChildren().add(vbm);
//
//        }
// 
//
//            // refresh ou afficher  l'historique de discussion
//            // refreeeeeesh  required here !!!!
//            Parent root;
//
//        });
        hb_petit.setSpacing(20);
        vbmsg.getChildren().add(nomSender);
        vbmsg.getChildren().add(messageBody);
        vbmsg.getChildren().add(img);
        

        //vbmsg.getChildren().add(t);
//        hb_petit.getChildren().add(reply);
//        hb_petit.getChildren().add(delete);
        vbmsg.getChildren().add(hb_petit);
        
       

        return vbmsg;

    }

//    @FXML
//    private void redirect(ActionEvent event) throws IOException {
//
//        Parent parent = FXMLLoader.load(getClass().getResource("../Fxml/HomeYosr.fxml"));
//        Scene scene = new Scene(parent);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }

}
