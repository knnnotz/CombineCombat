/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinecombat;


import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
/**
 *
 * @author ACER
 */
public class WhoFirst {
    private int order;
    private String ordername;
    boolean choose = false;
    String picture ; //ที่อยู่รูป
    ImageView image; //เก็บภาพ

    public WhoFirst(int order){
        this.order = order;
        picture = "image/";
        switch(order){
            case 1:
                ordername = "1st.png";
                picture += ordername;
                break;
            case 2:
                ordername = "2nd.png";
                picture += ordername;
                break;
        }
        image = new ImageView (new Image(picture, 248, 350, true, true));//making picture
    }    
    
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    
}