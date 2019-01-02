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
public class Player {
    
    private Card[] cardIndex = new Card[2];//การ์ดที่จับคู่
    private DestCard[] destCard = new DestCard[1];
    private int cardIndexCount = 1;
    private int destCardCount = 1;
     
    private int charType; // ชนิดของการ์ด
    private String charname;
    String picture;
    ImageView image;
    
    private ImageView[] cardImageView = new ImageView[2];//เก็บภาพไอเทม
    private String nameA ;
    private String nameB ;

    public Player(){}
    public Player(int charType){
        this.charType = charType;
        picture = "image/";
        switch (charType) { //นำชื่อมาระบุชนิด
            case 3:
                charname = "Knight"; 
                picture += charname+"1.png";
                break;
            case 4:
                charname = "Wizard";
                picture += charname+"1.png";
                break;    
            case 5:
                charname = "Assassin";
                picture += charname+"1.png";
                 break;
            case 6:
                charname = "Cleric";
                picture += charname+"1.png";
                 break; 
    
        }
        image = new ImageView (new Image(picture, 300, 420, true, true));//making picture
    }
    // For Change Char Effect

    public String getCharname() {
        return charname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture,int i) { // i = 2 damaged i = 3 healed .....
        this.picture = "image/"+picture+i+".png";
        image = new ImageView (new Image(this.picture, 300, 420, true, true));
    }
    
    public DestCard getDestCard(int i) {
        return destCard[i];
    }

    public void setDestCard(DestCard card) {
        destCard[destCardCount] = card;
    }

    public void setCardIndex(Card card) {
        cardIndex[cardIndexCount++] = card;
    }
    
    public Card getCardIndex(int i) {
        return cardIndex[i];
    }
    
    public void clearIndex() {//เคลียร์ออกหมด
        cardIndex = new Card[2];//สร้างใหม่ทับ
        cardIndexCount = 1; //รีเซตตัวนับ
        cardImageView = new ImageView[2];//สร้างใหม่ทับ
    }
    
    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }
    
    public ImageView getCardImageView(int i) {
        return cardImageView[i];
    }

    public void setCardImageView(int i,ImageView img) {
        this.cardImageView[i] = img;
    }

    public int getCardIndexCount() {
        return cardIndexCount;
    }
     
}
