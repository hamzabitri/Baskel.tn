/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.Service;

import baskel.Entite.CategorieP;
import baskel.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import baskel.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class ServiceCategorie implements IService<CategorieP> {

    private Connection con;
    private Statement ste;
    private ResultSet res ;
    public ServiceCategorie() {
        con = DataBase.getInstance().getConnection();

    }


    @Override
    public void ajouter(CategorieP t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO category (`id_cp`, `name`) VALUES (NULL, '" + t.getNom() +  "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(CategorieP p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`category` ( `id_cp`, `name` ) VALUES ( ?, ?);");
    pre.setString(1, p.getNom());
    
  
    pre.executeUpdate();
    }
        public void delete(int idC) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `baskel`.`category` WHERE `id_cp`=?");
        PS.setInt(1,idC);
        PS.executeUpdate();
    }

    @Override
    public boolean delete(CategorieP t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM category WHERE name='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
    }
    @Override
    public boolean update(CategorieP t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategorieP> readAll() throws SQLException {
    List<CategorieP> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from category");
     while (rs.next()) {                
               int idC=rs.getInt(1);
               String nomC=rs.getString(2);
               
               System.out.println(nomC);
               CategorieP c=new CategorieP(idC, nomC);
               
     arr.add(c);
     }
    return arr;
    }
    
      public void updatetab(CategorieP a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `baskel`.`category` SET `name`=?  WHERE `id_cp`=?");
        PS.setString(1,a.getNom()); 
        
        PS.setInt(2,a.getId());
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }

    }
     public boolean update(CategorieP x,String nomC) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE category  SET name='"+ nomC  +"' WHERE name='" + x.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }
     
       public CategorieP getById(int idC) {
          CategorieP a = null;
         String requete = " select * from category  where id_cp='"+idC+"'" ;
        try {
           
            ste = con.createStatement();
            ResultSet res = ste.executeQuery(requete);
            if (res.next())
            {
                a=new CategorieP(res.getInt(1),res.getString(2));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
       
              public CategorieP getByName(String n) {
          CategorieP a = null;
         String requete = " select * from category where (name like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new CategorieP(res.getInt(1),res.getString(2));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
       
           @Override
    public CategorieP getByName(CategorieP f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
   @Override
    public CategorieP getById(CategorieP f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param p
     */


}