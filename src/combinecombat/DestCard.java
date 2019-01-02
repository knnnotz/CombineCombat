/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinecombat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ACER
 */
public class DestCard {
    private int dest; // ชนิดของการ์ด
    private int destX;
    private String destname;
    private String annouce;
    private boolean choose;
    
    String picture ; //ที่อยู่รูป
    ImageView image; //เก็บภาพ
    private int damage = 0 ;
    private int hpPlus ;
    private int turnEffect ;
    private int timeEffect ;
    
    public DestCard(int destX ,int dest, boolean choose){
        this.dest = dest;
        this.destX = destX;
        this.choose = choose;
        picture = "image/";
        
        
        if(!choose){
            switch(destX){
                case 0:
                    destname = "R_A.png";  
                    picture += destname;
                    break;
                case 1:    
                    destname = "R_S.png";  
                    picture += destname;
                    break;
                case 2:
                    destname = "B_K.png";  
                    picture += destname;
                    break;
                case 3:
                    destname = "B_L.png";  
                    picture += destname;
                    break;
            }
        }else{
        switch(dest){
            case 1:
                destname = "Bomb.png";
                picture += destname;
                damage = 5;
                annouce = ("BOME IT !!!!");
                break;
            case 2:
                destname = "Bombme.png";
                picture += destname;
                damage = 3;
                annouce = ("BOME ME ????");
                break;
            case 3:
            case 4:
                destname = "HealEN.png";
                picture += destname;
                hpPlus = 2;
                annouce = ("WTF IM HEAL WRONG !!!!");
                break;
            case 5:    
            case 6:
            case 7:
                destname = "TimeDE.png";
                picture += destname;
                timeEffect = 3;
                annouce = ("HURRY UP BITCH !!!!");
                break;
            case 8:
            case 9:
            case 10:
                destname = "TimeIN.png";
                picture += destname;
                timeEffect = 1;
                annouce = ("I HAVE MUCH MORE TIME !!!!");
                break;
            case 11:
            case 12:    
            case 13:
            case 14:
            case 15:
                destname = "No.png";
                picture += destname;
                annouce = ("BAD LUCK !!!!");
                break;
        }
    }image = new ImageView (new Image(picture, 117, 168, true, true));//making picture
    }

    public int getDest() {
        return dest;
    }

    public int getDestX() {
        return destX;
    }

    public String getAnnouce() {
        return annouce;
    }

    public int getDamage() {
        return damage;
    }

    public int getHpPlus() {
        return hpPlus;
    }

    public int getTurnEffect() {
        return turnEffect;
    }

    public int getTimeEffect() {
        return timeEffect;
    }

    public String getDestname() {
        return destname;
    }

    public boolean isChoose() {
        return choose;
    }

    public ImageView getImage() {
        return image;
    }
    public void isOpen(){
        choose = true;   
    }
    public void isClose(){
        choose = false;   
    }
}
