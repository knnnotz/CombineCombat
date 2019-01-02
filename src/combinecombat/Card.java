/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinecombat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ACER
 */
public class Card {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 170;
    
    private int type; // ชนิดของการ์ด
    private String typename;
    private String destname;
    String picture ; //ที่อยู่รูป
    String annouce ;
    ImageView image; //เก็บภาพ
    private int cardX = 5 ; // ตำแหน่งของอาเรย์ที่จะ เก็บ
    private int cardY = 4 ; 
    private boolean opened;
    boolean matched; // ถูกจับคู่ยัง
    private int damage = 0 ;
    private int hpPlus ;
    private int turnEffect ;
    private int timeEffect ;
    
    private static final int SMALL_DAMAGE = 2 ;
    private static final int BIG_DAMAGE = 3 ;
    private static final int CRI_DAMAGE = 4 ;
    private static final int SMALL_HEAL = 2 ;
    private static final int BIG_HEAL = 3 ;
    private static final int GRA_HEAL = 5 ;
    
    
    
    //Constructor//
    public Card(){}
    
    public Card(int cardX , int cardY,boolean isOpen,int type ){
        this.cardX = cardX;
        this.cardY = cardY;
        this.type = type;
        opened = isOpen;
        
        if( !opened ){
            picture = "image/back_";
            switch(cardX){
            case 0:
                typename = "0_";
                picture += typename;
                break;
            case 1:
                typename = "1_";
                picture += typename;
                break;
            case 2:
                typename = "2_";
                picture += typename;
                break;
            case 3:
                typename = "3_";
                picture += typename;
                break;
            case 4:
                typename = "4_";
                picture += typename;
                break;
            }
            switch(cardY){
            case 0:
                typename = "0.png";
                picture += typename;
                break;
            case 1:
                typename = "1.png";
                picture += typename;
                break;
            case 2:
                typename = "2.png";
                picture += typename;
                break;
            case 3:
                typename = "3.png";
                picture += typename;
                break;
            }
        }else{
            picture = "image/";
            switch (type) { //นำชื่อมาระบุชนิด
            case 1:
                typename = "Atk.png";
                picture += typename; //เพิ่มชื่อ.....ลงในที่อยู่ภาพ *ด้านบนตอนเช็ค if else*
                damage = SMALL_DAMAGE;//2
                annouce = "Normal ATK 2";
                break;
            case 2:
                typename = "Heal.png";
                picture += typename;
                hpPlus = BIG_HEAL;//3
                annouce = "Normal Heal 3";
                break;
            case 3:
                typename = "Knight.png"; 
                picture += typename;
                hpPlus = SMALL_HEAL;//2
                damage = BIG_DAMAGE;//3
                annouce = "Knight atk 3 p and +hp 2 p";
                break;
            case 4:
                typename = "Wizard.png";
                picture += typename;
                turnEffect = 1;
                annouce = "Wizard sleep enermy 1 turn ";
                break;    
            case 5:
                typename = "Assassin.png";
                picture += typename;
                damage = CRI_DAMAGE;
                annouce = "CRITICAL !!!";
                 break;
            case 6:
                typename = "Cleric.png";
                picture += typename;
                hpPlus = GRA_HEAL;
                annouce = "GRAND HEAL ";
                break; 
            }
        }
        
        image = new ImageView (new Image(picture, 117, 168, true, true));//making picture
    }

    public int getCardType() {
        return type;
    }

    public int getCardX() {
        return cardX;
    }

    public int getCardY() {
        return cardY;
    }

    public ImageView getImage() {
        return image;
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
    public String getAnnouce() {
        return annouce;
    }
    public boolean isMatch(){
        return matched;   
    }
    public void isMatched(){
        matched = true;   
    }
    public boolean isOpen(){
        return opened;   
    }

//    public Card(int destX ,int dest, boolean isOpen){
//        type = dest;
//        cardX = destX;
//        opened = isOpen;
//        picture = "image/";
//        
//        if(!isOpen){
//            switch(destX){
//                case 0:
//                    destname = "R_A.png";  
//                    picture += destname;
//                    break;
//                case 1:    
//                    destname = "R_S.png";  
//                    picture += destname;
//                    break;
//                case 2:
//                    destname = "B_K.png";  
//                    picture += destname;
//                    break;
//                case 3:
//                    destname = "B_L.png";  
//                    picture += destname;
//                    break;
//            }
//        }else{
//        switch(dest){
//            case 1:
//                destname = "Bomb.png";
//                picture += destname;
//                damage = 5;
//                annouce = ("BOME IT !!!!");
//                break;
//            case 2:
//                destname = "Sleep.png";
//                picture += destname;
//                turnEffect = 1;
//                annouce = ("LET'S SLEEP !!!!");
//                break;
//            case 3:
//            case 4:
//                destname = "HealEN.png";
//                picture += destname;
//                hpPlus = 2;
//                annouce = ("WTF IM HEAL WRONG !!!!");
//                break;
//            case 5:    
//            case 6:
//            case 7:
//                destname = "TimeDE.png";
//                picture += destname;
//                timeEffect = 3;
//                annouce = ("HURRY UP BITCH !!!!");
//                break;
//            case 8:
//            case 9:
//            case 10:
//                destname = "TimeIN.png";
//                picture += destname;
//                timeEffect = 1;
//                annouce = ("I HAVE MUCH MORE TIME !!!!");
//                break;
//            case 11:
//            case 12:    
//            case 13:
//            case 14:
//            case 15:
//                destname = "No.png";
//                picture += destname;
//                annouce = ("BAD LUCK !!!!");
//                break;
//        }
//    }image = new ImageView (new Image(picture, 117, 168, true, true));//making picture
//    }
    
    public Card(int cardX , int cardY ,int type ){
        this.cardX = cardX;
        this.cardY = cardY;
        this.type = type;
        switch (type) { //นำชื่อมาระบุชนิด
            case 1:
                typename = "Atk.png";
                picture += typename; //เพิ่มชื่อ.....ลงในที่อยู่ภาพ *ด้านบนตอนเช็ค if else*
                break;
            case 2:
                typename = "Heal.png";
                picture += typename;
                 break;
            case 3:
                typename = "Knight.png"; 
                picture += typename;
                break;
            case 4:
                typename = "Wizard.png";
                picture += typename;
                break;    
            case 5:
                typename = "Assassin.png";
                picture += typename;
                 break;
            case 6:
                typename = "Adventure.png";
                picture += typename;
                 break; 
            case 7:
                typename = "Clown.png";
                picture += typename;
                 break;  
            case 8:    
                typename = "Cleric.png";
                picture += typename;
                 break; 
        }
        image = new ImageView (new Image(picture, 117, 168, true, true));//making picture
    }
}

    
//    public Card(int cardX , int cardY ,int type ,boolean isOpen,boolean isMatch){
//        this.cardX = cardX;
//        this.cardY = cardY;
//        this.type = type;
//        opened = isOpen;
//        matched = isMatch;
//        
//        if( !opened ){
//            picture = "image/Back.png";
//        }else{
//            picture = "image/";
//            switch (type) { //นำชื่อมาระบุชนิด
//            case 1:
//                typename = "Atk.png";
//                picture += typename; //เพิ่มชื่อ.....ลงในที่อยู่ภาพ *ด้านบนตอนเช็ค if else*
//                break;
//            case 2:
//                typename = "Heal.png";
//                picture += typename;
//                 break;
//            case 3:
//                typename = "Knight.png"; 
//                picture += typename;
//                break;
//            case 4:
//                typename = "Wizard.png";
//                picture += typename;
//                break;    
//            case 5:
//                typename = "Assassin.png";
//                picture += typename;
//                 break;
//            case 6:
//                typename = "Adventure.png";
//                picture += typename;
//                 break; 
//            case 7:
//                typename = "Clown.png";
//                picture += typename;
//                 break;  
//            case 8:    
//                typename = "Cleric.png";
//                picture += typename;
//                 break; 
//            }
//        }
//        
//        image = new ImageView (new Image(picture, 70, 100, true, true));//making picture
//    }