/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author onsks
 */
public class Article {
     private int ida;
    private int idc;
    String titre ;
    String description;
    String imageA;

    public Article(int ida, int idc, String titre, String description,String imageA) {
        this.ida = ida;
        this.idc = idc;
        this.titre = titre;
        this.description=description;
        this.imageA=imageA;
    }

    public Article(int idc, String titre, String description, String imageA) {
        this.idc = idc;
        this.titre = titre;
        this.description=description;
        this.imageA=imageA;
    }
    
     public Article(String titre, String description, String imageA) {
        this.titre = titre;
        this.description=description;
        this.imageA=imageA;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int id) {
        this.ida = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int id) {
        this.idc = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getImageA() {
        return imageA;
    }

    public void setImgA(String imageA) {
        this.imageA= imageA;
    }

    @Override
    public String toString() {
        return "Article{" + "ida=" + ida + ", idc=" + idc + ", titre=" + titre +", description=" + description +", imageA=" + imageA + '}';
    }
    
}
