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
public class Temoignage {
     private int idt;
    private int idc;
    String titre ;
    String champ;
    String imageT;

    public  Temoignage(int idt, int idc, String titre, String champ,String imageT) {
        this.idt = idt;
        this.idc = idc;
        this.titre = titre;
        this.champ=champ;
        this.imageT=imageT;
    }

    public  Temoignage(int idc, String titre, String champ, String imageT) {
        this.idc = idc;
        this.titre = titre;
        this.champ=champ;
        this.imageT=imageT;
    }
    
     public Temoignage(String titre, String champ, String imageT) {
        this.titre = titre;
        this.champ=champ;
        this.imageT=imageT;
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int id) {
        this.idt = id;
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
    
     public String getChamp() {
        return champ;
    }

    public void setDescription(String champ) {
        this.champ=champ;
    }
    
     public String getImageT() {
        return imageT;
    }

    public void setImgA(String imageT) {
        this.imageT= imageT;
    }

    @Override
    public String toString() {
        return " Temoignage{" + "idt=" + idt + ", idc=" + idc + ", titre=" + titre +", champ=" + champ +", imageT=" + imageT + '}';
    }
    
}
