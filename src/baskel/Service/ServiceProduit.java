/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.Service;

import baskel.Entite.Produit;
import baskel.Utils.DataBase;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abdel
 */
public class ServiceProduit {
     private final Connection con;
    private Statement ste;
    private ResultSet res ;

    public ServiceProduit() {
        con = DataBase.getInstance().getConnection();
}
    public void ajouter(Produit t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO produit (`id_produit`, `nom`, `reference`, `id_categorie`, `quantite`, `prix` , `image` , `description`) VALUES (NULL, '" + t.getNom() + "', '" + t.getReference() + "', '" + t.getCategorie() + "', '" + t.getQuantite() + "', '" + t.getPrix() + "', '" + t.getImage() + "', '" + t.getDescription() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Produit p) throws SQLException
    {
       
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`produit` (`id_produit`, `nom`, `reference`, `id_categorie`, `quantite`, `prix` , `image` , `description`) VALUES ( ?, ?, ?, ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getReference());
    pre.setInt(3, p.getCategorie());
    pre.setInt(4, p.getQuantite());
    pre.setFloat(5, p.getPrix());
    pre.setString(6, p.getImage());
    pre.setString(7, p.getDescription());
    pre.executeUpdate();
    }
       
  
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `baskel`.`produit` WHERE `id_produit`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    public boolean delete(Produit t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM produit WHERE nom='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
    }
    
    public boolean update(Produit t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public List<Produit> readAll() throws SQLException {
    List<Produit> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String reference=rs.getString("reference");
               int id_categorie=rs.getInt("id_categorie");
               int quantite=rs.getInt("quantite");
               float prix=rs.getFloat("prix");
               String image=rs.getString("image");
               String description=rs.getString("description");
               Produit p=new Produit(id, nom, reference, id_categorie, quantite, prix, image, description);
               
     arr.add(p);
     }
    return arr;
    }
    

 public boolean update(Produit t,String nom) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE produit SET nom='"+ nom  +"' WHERE nom='" + t.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }

 
  public void updatetab(Produit p) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `baskel`.`produit` SET `nom`=? ,`reference`=?,`id_categorie`=?,`quantite`=?,`prix`=?,`image`=?,`description`=? WHERE `id_produit`=?");
    PS.setString(1, p.getNom());
    PS.setString(2, p.getReference());
    PS.setInt(3, p.getCategorie());
    PS.setInt(4, p.getQuantite());
    PS.setFloat(5, p.getPrix());
    PS.setString(6, p.getImage());
    PS.setString(7, p.getDescription());
    PS.setInt(8, p.getId());
    PS.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }

    }

 public Produit getById(int id) {
          Produit a = null;
         String requete = " select* from produit  where id_produit='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res = ste.executeQuery(requete);
            if (res.next())
            {
                a=new Produit(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getFloat(6),res.getString(7),res.getString(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
      
       public Produit getByName(String nom) {
          Produit a = null;
         String requete = " select * from produit where (nom like '"+nom+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new Produit(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getFloat(6),res.getString(7),res.getString(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    public Produit getByName(Produit f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
    public Produit getById(Produit f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void mail (String MSG)
    {
        /*String host="abdelhak.hamdi@esprit.tn";
                    final String user="abdelhak.hamdi@esprit.tn";//change accordingly
                    final String password="firealienx9";//change accordingly
                    
                    String to="abdelhak.hamdi@esprit.tn";//change accordingly
                    
                    //Get the session object
                    Properties props = new Properties();
                    props.put("mail.smtp.ssl.trust", "*");
                    
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.starttls.enable", "true");
                    Session session = Session.getDefaultInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                                                        return new PasswordAuthentication(user,password);

                                }
                                });
                            
                    
                    //Compose the message
                  
                        try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("NEW NEW");  
              message.setText(MSG  ); 

      
            System.out.println("message sent successfully...");  

    //send the message  
     Transport.send(message);  
       
     } catch (MessagingException e)
      {e.printStackTrace();} */
                        
                        
                
   
    }

}

