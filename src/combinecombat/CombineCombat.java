/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinecombat;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.Object;
import java.util.EventObject;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class CombineCombat extends Application{
    //////////////////////////////////////// Data Field /////////////////////////////////////////////////////
    private Pane pane;
    private Scene scene;
    // Player Character
    private Player[] player = new Player[2];
    private int[] playerLocaX = new int[]{950,25}; // 0 = B , 1 = A
    private int playerLocaY = 75;
    // Match card
    private final int ROW = 5;
    private final int COL = 4 ;
    int randCard[] = new int[(ROW*COL)];//สร้างมาเก็บตัวเลข type การ์ด
    Card matchCard[][] = new Card[ROW][COL] ;// สร้าง ออปเจค มาเก็บค่า
    int cardLocaX;
    int cardLocaY;
    // Dest card
    int destCardNum[] = new int[4];//สร้างมาเก็บตัวเลข type Dest 4 ใบ : 0 1 RED : 2 3 BLUE
    DestCard destCard[][] = new DestCard[4][2]; // เก็บแยก 0 1 2 3 เลย เพราะ destX ก็มี 0 1 2 3
    int[] destLocaX = new int[]{45,175,975,1105};// 0 1 2 3
    int destLocaY = 560;
    // Time's Counter
    private Label timeCounter = new Label("");
    int timeCount = 5;
    
    // Turn's Counter
    int turnCount = 0;
    
    // HP & Time Rectangle         
    int Hp[] = new int[]{22,22};
    private Rectangle[] hpBar = new Rectangle[3];//ขีดเลือด A 25-575 //ขีดเลือด B705-1255
    // snail graphic                                                      
    private Rectangle snailTime = new Rectangle(575,5,130, 60);//ขีดเวลา 575-705
    private ImageView snailPicA = new ImageView(new Image("image/left.png", 94, 60, true, true));
    private ImageView snailPicB = new ImageView(new Image("image/right.png", 94, 60, true, true));                                                         
    // BG
    private ImageView Red_Player_BG = new ImageView(new Image("image/Red_Player_BG.png", 300, 420, true, true));
    private ImageView Blue_Player_BG = new ImageView(new Image("image/Blue_Player_BG.png", 300, 420, true, true));
    // Turn effect
    InnerShadow i1 = turnABShow();
    // Who first Graphics
    private WhoFirst cardR ;
    private WhoFirst cardB ;
    // Effect
    private ImageView atk2 = new ImageView(new Image("effect/atk2.png", 300, 420, true, true));
    private ImageView atk3 = new ImageView(new Image("effect/atk3.png", 300, 420, true, true));
    private ImageView atk5 = new ImageView(new Image("effect/bomb1.png", 300, 420, true, true));
    private ImageView cri = new ImageView(new Image("effect/critical.png", 300, 420, true, true));
    private ImageView timeD = new ImageView(new Image("effect/decreaseTime.png", 300, 420, true, true));
    private ImageView timeI = new ImageView(new Image("effect/increaseTime.png", 300, 420, true, true));
    private ImageView heal2 = new ImageView(new Image("effect/heal2.png", 300, 420, true, true));
    private ImageView heal3 = new ImageView(new Image("effect/heal3.png", 300, 420, true, true));
    private ImageView heal5 = new ImageView(new Image("effect/heal5.png", 300, 420, true, true));
    private ImageView sleep = new ImageView(new Image("effect/sleep.png", 300, 420, true, true));
    //UI
    private ImageView gameplay = new ImageView(new Image("UI/GameplayBG.png", 1265, 800, true, true));
    //Menu
    private ImageView gameMenu = new ImageView(new Image("UI/Gameplay_Menu.png", 1265, 800, true, true));
    private ImageView info = new ImageView(new Image("UI/IF.png", 225, 300, true, true));
    private ImageView tutorial = new ImageView(new Image("UI/TTR.png", 225, 300, true, true));
    private ImageView play = new ImageView(new Image("UI/PLAY.png", 300, 400, true, true));
    private ImageView credit = new ImageView(new Image("UI/CD.png", 225, 300, true, true));
    private ImageView quit = new ImageView(new Image("UI/QUIT.png", 225, 300, true, true));
    //
    private ImageView gameHowTo = new ImageView(new Image("UI/Gameplay_HTP.png", 1265, 800, true, true));
    private ImageView gameCredit = new ImageView(new Image("UI/Credit.png", 1265, 800, true, true));
    private ImageView gameQuit = new ImageView(new Image("UI/quitUI.png", 1265, 800, true, true));
    private ImageView gameInfo = new ImageView(new Image("UI/Gameplay_IF.png", 1265, 800, true, true));
    private ImageView gameWhoFirst = new ImageView(new Image("UI/GameplayWFpng.png", 1265, 800, true, true));
    //Character choose
    private ImageView redChoose = new ImageView(new Image("UI/Gameplay_RC.png", 1265, 800, true, true));
    private ImageView blueChoose = new ImageView(new Image("UI/Gameplay_BC.png", 1265, 800, true, true));
    private ImageView knot = new ImageView(new Image("UI/knot.png", 250, 350, true, true));
    private ImageView elf = new ImageView(new Image("UI/elf.png", 250, 350, true, true));
    private ImageView fink = new ImageView(new Image("UI/fink.png", 250, 350, true, true));
    private ImageView poom = new ImageView(new Image("UI/poom.png", 250, 350, true, true));
    //
    private ImageView back = new ImageView(new Image("UI/backbutt.png", 150, 90, true, true));
    private ImageView next = new ImageView(new Image("UI/nextbutt.png", 150, 90, true, true));
    private ImageView blueWin = new ImageView(new Image("UI/Blue_win.png", 1265, 800, true, true));
    private ImageView redWin = new ImageView(new Image("UI/Red_win.png", 1265, 800, true, true));
    private ImageView revenge = new ImageView(new Image("UI/rvbutt.png", 380, 150, true, true));
    private ImageView goMenu = new ImageView(new Image("UI/gmbutt.png", 380, 150, true, true));
    //Media
    MediaPlayer bgmPlayer;
    MediaPlayer clickPlay;
    private Media gameSound = new Media(new File("src/sound/GameSound.mp3").toURI().toString());//*
    private Media battleSound = new Media(new File("src/sound/BattleSound.mp3").toURI().toString());//*
    private Media click = new Media(new File("src/sound/Click.mp3").toURI().toString());//*
    private Media bomb = new Media(new File("src/sound/bomb.mp3").toURI().toString());
    private Media card = new Media(new File("src/sound/FlipCard.mp3").toURI().toString());
    private Media heal = new Media(new File("src/sound/Healling.mp3").toURI().toString());
    private Media atk = new Media(new File("src/sound/Punch.mp3").toURI().toString());
    private Media wizSleep = new Media(new File("src/sound/Sleep.mp3").toURI().toString());
    private Media timeChange = new Media(new File("src/sound/TimeChange.mp3").toURI().toString());
    private Media win = new Media(new File("src/sound/Win.mp3").toURI().toString());
    
    //////////////////////////////////////// Data Field /////////////////////////////////////////////////////
    //////////////////////////////////////  GamePlay  /////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) {
        pane = new Pane();
        
        scene = new Scene(pane, 1265, 805);
          
        player[0] = new Player((int)(Math.random()*4+3));//สร้างผู้เล่น A กันเหนียว
        player[1] = new Player((int)(Math.random()*4+3));//สร้างผู้เล่น A กันเหนียว
        
        clickPlay = new MediaPlayer(click);
        
        primaryStage.setTitle("CombineCombat");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        MatchClickCheck();// for check x y
        gameMenuUI();
//        endUI();
    }
    public void gameMenuUI(){
        bgmPlayer = new MediaPlayer(gameSound);
        bgmPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                bgmPlayer.seek(Duration.ZERO);
            }
        });
        bgmPlayer.setVolume(0.3);
        bgmPlayer.play();

        gameMenu.relocate(6, 10);
        addImage(gameMenu);
        info.relocate(22, 495);
        addImage(info);
        tutorial.relocate(257, 495);
        addImage(tutorial);
        play.relocate(490, 395);
        addImage(play);
        credit.relocate(799, 495);
        addImage(credit);
        quit.relocate(1030, 495);
        addImage(quit);
            info.setOnMouseMoved(e -> {       
                info.setEffect(i1);
                tutorial.setEffect(null);
                play.setEffect(null);
                credit.setEffect(null);
                quit.setEffect(null);
                menuClick(info);
            });
            tutorial.setOnMouseMoved(e -> {       
                tutorial.setEffect(i1);
                info.setEffect(null);
                play.setEffect(null);
                credit.setEffect(null);
                quit.setEffect(null);
                menuClick(tutorial);
            });
            play.setOnMouseMoved(e -> {       
                play.setEffect(i1);
                tutorial.setEffect(null);
                info.setEffect(null);
                credit.setEffect(null);
                quit.setEffect(null);
                play.setOnMouseClicked(ee -> {
                    clearMenu();
                    redCharUI();
                });
            });
            credit.setOnMouseMoved(e -> {       
                credit.setEffect(i1);
                tutorial.setEffect(null);
                play.setEffect(null);
                info.setEffect(null);
                quit.setEffect(null);
                menuClick(credit);
            });
            quit.setOnMouseMoved(e -> {       
                quit.setEffect(i1);
                tutorial.setEffect(null);
                play.setEffect(null);
                credit.setEffect(null);
                info.setEffect(null);
                menuClick(quit);
            }); 
    }
    public void infoUI(){
        gameInfo.relocate(6, 10);
        addImage(gameInfo);
            back.relocate(555, 710);
            addImage(back);
            gameInfo.setOnMouseMoved(e -> {       
                back.setEffect(null);
            });
            back.setOnMouseMoved(e -> {       
                back.setEffect(i1);
                gameInfo.setEffect(null);
                back.setOnMouseClicked(ee -> {
                    bgmPlayer.stop();
                    clickPlay = new MediaPlayer(click);
                    clickPlay.play();
                    gameMenuUI();
                    removeImage(back);
                    removeImage(gameInfo);
                });
            });   
    }
    public void tutorialUI(){
        gameHowTo.relocate(6, 10);
        addImage(gameHowTo);
            back.relocate(555, 715);
            addImage(back);
            gameHowTo.setOnMouseMoved(e -> {       
                back.setEffect(null);
            });
            back.setOnMouseMoved(e -> {       
                back.setEffect(i1);
                gameHowTo.setEffect(null);
                back.setOnMouseClicked(ee -> {
                    bgmPlayer.stop();
                    clickPlay = new MediaPlayer(click);
                    clickPlay.play();
                    gameMenuUI();
                    removeImage(back);
                    removeImage(gameHowTo);
                });
            }); 
    }
    public void creditUI(){
        gameCredit.relocate(6, 10);
        addImage(gameCredit);
            back.relocate(555, 710);
            addImage(back);
            gameCredit.setOnMouseMoved(e -> {       
                back.setEffect(null);
            });
            back.setOnMouseMoved(e -> {       
                back.setEffect(i1);
                gameCredit.setEffect(null);
                back.setOnMouseClicked(ee -> {
                    bgmPlayer.stop();
                    clickPlay = new MediaPlayer(click);
                    clickPlay.play();
                    gameMenuUI();
                    removeImage(back);
                    removeImage(gameCredit);
                });
            }); 
    }
    public void quitUI(){
        gameQuit.relocate(6, 10);
        addImage(gameQuit);
    }
    public void menuClick(ImageView img){
        img.setOnMouseClicked(e -> {
            bgmPlayer.stop();
            clickPlay = new MediaPlayer(click);
            clickPlay.play();
            System.out.println("Menu Choosed");
            if(img.equals(info)){
                clearMenu();
                infoUI();
            }else if(img.equals(tutorial)){
                clearMenu();
                tutorialUI();
            }else if(img.equals(credit)){
                clearMenu();
                creditUI();
            }else if(img.equals(quit)){
                clearMenu();
                quitUI();
            }else System.out.println("WTF");
        });
    }
    public void clearMenu(){
        removeImage(gameMenu);
        removeImage(info);
        removeImage(tutorial);
        removeImage(play);
        removeImage(credit);
        removeImage(quit);
    }
    public void redCharUI(){
        redChoose.relocate(6, 10);
        addImage(redChoose);
        knot.relocate(375, 85);
        addImage(knot);
        elf.relocate(650, 85);
        addImage(elf);
        fink.relocate(375, 450);
        addImage(fink);
        poom.relocate(650, 450);
        addImage(poom);
            back.relocate(100, 600);
            addImage(back);
            next.relocate(1020, 600);
            addImage(next);
        knot.setOnMouseMoved(e -> {       
            knot.setEffect(i1);
            elf.setEffect(null);
            fink.setEffect(null);
            poom.setEffect(null);
            charRedClick(knot);
        });
        elf.setOnMouseMoved(e -> {       
            elf.setEffect(i1);
            knot.setEffect(null);
            fink.setEffect(null);
            poom.setEffect(null);
            charRedClick(elf);
        });
        fink.setOnMouseMoved(e -> {       
            fink.setEffect(i1);
            elf.setEffect(null);
            knot.setEffect(null);
            poom.setEffect(null);
            charRedClick(fink);
        });
        poom.setOnMouseMoved(e -> {       
            poom.setEffect(i1);
            elf.setEffect(null);
            fink.setEffect(null);
            knot.setEffect(null);
            charRedClick(poom);
        });
            back.setOnMouseMoved(e -> {       
            back.setEffect(i1);
            next.setEffect(null); poom.setEffect(null); elf.setEffect(null); fink.setEffect(null); knot.setEffect(null);
                back.setOnMouseClicked(ee -> {
                bgmPlayer.stop();    
                clickPlay = new MediaPlayer(click);    
                clickPlay.play();    
                clearCharChoose();    
                gameMenuUI();
                });
            });
            next.setOnMouseMoved(e -> {       
            next.setEffect(i1);
            back.setEffect(null); poom.setEffect(null); elf.setEffect(null); fink.setEffect(null); knot.setEffect(null);
                next.setOnMouseClicked(ee -> {
                clickPlay = new MediaPlayer(click);     
                clickPlay.play();    
                clearCharChoose();
                blueCharUI();
                });
            });
        
    } 
    
    public void blueCharUI(){
        blueChoose.relocate(6, 10);
        addImage(blueChoose);
            removeImage(player[1].image);
            player[1].image.relocate(playerLocaX[1],playerLocaY+15); //ทดสอบการใส่รูปป
            addImage(player[1].image);
        knot.relocate(375, 85);
        addImage(knot);
        elf.relocate(650, 85);
        addImage(elf);
        fink.relocate(375, 450);
        addImage(fink);
        poom.relocate(650, 450);
        addImage(poom);
            back.relocate(100, 600);
            addImage(back);
            next.relocate(1020, 600);
            addImage(next);
        knot.setOnMouseMoved(e -> {       
            knot.setEffect(i1);
            elf.setEffect(null);
            fink.setEffect(null);
            poom.setEffect(null);
            charBlueClick(knot);
        });
        elf.setOnMouseMoved(e -> {       
            elf.setEffect(i1);
            knot.setEffect(null);
            fink.setEffect(null);
            poom.setEffect(null);
            charBlueClick(elf);
        });
        fink.setOnMouseMoved(e -> {       
            fink.setEffect(i1);
            elf.setEffect(null);
            knot.setEffect(null);
            poom.setEffect(null);
            charBlueClick(fink);
        });
        poom.setOnMouseMoved(e -> {       
            poom.setEffect(i1);
            elf.setEffect(null);
            fink.setEffect(null);
            knot.setEffect(null);
            charBlueClick(poom);
        });
            back.setOnMouseMoved(e -> {       
            back.setEffect(i1);
            next.setEffect(null); poom.setEffect(null); elf.setEffect(null); fink.setEffect(null); knot.setEffect(null);
                back.setOnMouseClicked(ee -> {
                clickPlay = new MediaPlayer(click);     
                clickPlay.play();    
                clearCharChoose();    
                redCharUI();
                });
            });
            next.setOnMouseMoved(e -> {       
            next.setEffect(i1);
            back.setEffect(null); poom.setEffect(null); elf.setEffect(null); fink.setEffect(null); knot.setEffect(null);
                next.setOnMouseClicked(ee -> {
                clickPlay = new MediaPlayer(click);     
                clickPlay.play();    
                clearCharChoose();
                whoFirst();
                });
            });
    }    
    public void charBlueClick(ImageView img){
        img.setOnMouseClicked(e -> {
            clickPlay = new MediaPlayer(click); 
            clickPlay.play();
            System.out.println("Blue char Choosed");
            if(img.equals(knot)){
                removeImage(player[0].image);    
                player[0] = new Player(3);//kn
                removeImage(player[0].image);
                player[0].image.relocate(playerLocaX[0],playerLocaY+15); //ทดสอบการใส่รูปป
                addImage(player[0].image);
                System.out.println(player[0].picture);
            }else if(img.equals(elf)){
                removeImage(player[0].image);
                player[0] = new Player(5);//ass
                player[0].image.relocate(playerLocaX[0],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[0].image);
                System.out.println(player[0].picture);
            }else if(img.equals(fink)){
                removeImage(player[0].image);
                player[0] = new Player(4);//wiz
                player[0].image.relocate(playerLocaX[0],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[0].image);
                System.out.println(player[0].picture);
            }else if(img.equals(poom)){
                removeImage(player[0].image);
                player[0] = new Player(6);//cle
                player[0].image.relocate(playerLocaX[0],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[0].image);
                System.out.println(player[0].picture);
            }else System.out.println("WTF");
          
        });
    }
    public void charRedClick(ImageView img){
        img.setOnMouseClicked(e -> {
            clickPlay = new MediaPlayer(click); 
            clickPlay.play();
            System.out.println("Red char Choosed");
            if(img.equals(knot)){
                removeImage(player[1].image);    
                player[1] = new Player(3);//kn
                removeImage(player[1].image);
                player[1].image.relocate(playerLocaX[1],playerLocaY+15); //ทดสอบการใส่รูปป
                addImage(player[1].image);
                System.out.println(player[1].picture);
            }else if(img.equals(elf)){
                removeImage(player[1].image);
                player[1] = new Player(5);//ass
                player[1].image.relocate(playerLocaX[1],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[1].image);
                System.out.println(player[1].picture);
            }else if(img.equals(fink)){
                removeImage(player[1].image);
                player[1] = new Player(4);//wiz
                player[1].image.relocate(playerLocaX[1],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[1].image);
                System.out.println(player[1].picture);
            }else if(img.equals(poom)){
                removeImage(player[1].image);
                player[1] = new Player(6);//cle
                player[1].image.relocate(playerLocaX[1],playerLocaY+10); //ทดสอบการใส่รูปป
                addImage(player[1].image);
                System.out.println(player[1].picture);
            }else System.out.println("WTF");
          
        });
    }
    public void clearCharChoose(){
        removeImage(knot);
        removeImage(elf);
        removeImage(fink);
        removeImage(poom);
        removeImage(back);
        removeImage(next);
        removeImage(blueChoose);
        removeImage(redChoose);
    }
    public void whoFirst(){
        bgmPlayer.stop();
        gameWhoFirst.relocate(6, 10);
        addImage(gameWhoFirst);
        if (!pane.getChildren().contains( Red_Player_BG)) {//มี R BG ยัง
        Red_Player_BG.relocate(playerLocaX[1],playerLocaY);
        pane.getChildren().add( Red_Player_BG);//ไม่มีก็ใส่
        }
        if (!pane.getChildren().contains( Blue_Player_BG)) {//มี B BG ยัง
         Blue_Player_BG.relocate(playerLocaX[0],playerLocaY);
        pane.getChildren().add( Blue_Player_BG);//ไม่มีก็ใส่
        }
        
        removeImage(player[1].image);
        player[1].image.relocate(playerLocaX[1],playerLocaY+10); //ทดสอบการใส่รูปป
        addImage(player[1].image);
                
        removeImage(player[0].image);
        player[0].image.relocate(playerLocaX[0],playerLocaY+10); //ทดสอบการใส่รูปป
        addImage(player[0].image);
        
        int randOrder1 = (int)(Math.random()*2+1);// 1 2
        int randOrder2;
        if(randOrder1 == 1 ){
            randOrder2 = 2;
        }else{randOrder2 = 1;}
        System.out.println(randOrder1+" "+randOrder2);
        cardR = new WhoFirst(randOrder1);
        if (!pane.getChildren().contains(cardR.image)) {//มีรูปนี้ยัง
            cardR.image.relocate(369,100);//เปลี่ยนตำแหน่งรูป
            pane.getChildren().add(cardR.image);//ไม่มีก็ใส่   
        }
        cardB = new WhoFirst(randOrder2);
        if (!pane.getChildren().contains(cardB.image)) {//มีรูปนี้ยัง
            cardB.image.relocate(660,100);//เปลี่ยนตำแหน่งรูป
            pane.getChildren().add(cardB.image);//ไม่มีก็ใส่   
        }
        
        if(randOrder1 == 1 ){
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), cardR.image);
            scaleTransition.setToX(1.15);
            scaleTransition.setToY(1.15);
            scaleTransition.play();
            ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), cardB.image);
            scaleTransition2.setToX(0.8);
            scaleTransition2.setToY(0.8);
            scaleTransition2.play();
            turnCount = 1;
            Red_Player_BG.setEffect(i1);
            System.out.println("1st : is Red"+turnCount);
        }else{
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), cardB.image);
            scaleTransition.setToX(1.15);
            scaleTransition.setToY(1.15);
            scaleTransition.play();
            ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), cardR.image);
            scaleTransition2.setToX(0.8);
            scaleTransition2.setToY(0.8);
            scaleTransition2.play();
            turnCount = 2;
            Blue_Player_BG.setEffect(i1);
            System.out.println("1st : is Blue"+turnCount);
        }
        
        scene.setOnKeyPressed((KeyEvent event) -> {
        if (event.getCode() == KeyCode.SPACE){
            pane.getChildren().remove(cardR.image);
            pane.getChildren().remove(cardB.image); 
            pane.getChildren().remove(Red_Player_BG);
            pane.getChildren().remove(Blue_Player_BG);
            scene.setOnKeyPressed(null);
            gameStart();
        }    
        });
        
  }            
    DestCard thatDestA = null;
    DestCard thatDestB = null;
    boolean Choose = false;
    Card thatCardA = null;
    Card thatCardB = null;
    boolean[] select = new boolean[2];
    int cardCount = 0 ;
    int cardMatchCount = 0;
    int count = 0; 
    
    public void gameStart(){
        bgmPlayer = new MediaPlayer(battleSound);
        bgmPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                bgmPlayer.seek(Duration.ZERO);
            }
        });
        bgmPlayer.setVolume(0.1);
        bgmPlayer.play();
        gameplay.relocate(6, 10);
        addImage(gameplay);
        
        cardCount = 0 ;
        cardMatchCount = 0;
        count = 0; 
        
        hpBar[1] = new Rectangle(25,5,550, 40);//ขีดเลือด A 25-575
        hpBar[0] = new Rectangle(705,5,550, 40);//ขีดเลือด B        
        System.out.println("HP A :"+Hp[1]);
        System.out.println("HP B :"+Hp[0]);
        
        hpBar[1].setFill(Paint.valueOf("Red"));//ขีดเลือด
        hpBar[0].setFill(Paint.valueOf("Blue"));//ขีดเลือด
        snailTime.setFill(Color.web(("0x000000")));//////////////+++++++++++++++++++++++++++++++++++++++++++++++ เปลี่ยนสี เด๊ะ
        if (!pane.getChildren().contains(hpBar[1])) {//มีขีดเลือดยัง
            pane.getChildren().add(hpBar[1]);//ไม่มีก็ใส่
        }
        if (!pane.getChildren().contains(hpBar[0])) {//มีขีดเลือดยัง
            pane.getChildren().add(hpBar[0]);//ไม่มีก็ใส่
        }
        if (!pane.getChildren().contains(snailTime)) {//มีขีดเลือดยัง
            pane.getChildren().add(snailTime);//ไม่มีก็ใส่
        }
        if (!pane.getChildren().contains( Red_Player_BG)) {//มี R BG ยัง
            Red_Player_BG.relocate(playerLocaX[1],playerLocaY);
            pane.getChildren().add( Red_Player_BG);//ไม่มีก็ใส่
        }
        if (!pane.getChildren().contains( Blue_Player_BG)) {//มี B BG ยัง
            Blue_Player_BG.relocate(playerLocaX[0],playerLocaY);
            pane.getChildren().add( Blue_Player_BG);//ไม่มีก็ใส่
        }

        timeCounter.setFont(Font.font("Arial", FontWeight.BOLD, 50));   
        timeCounter.setTextFill(Color.YELLOW);
        timeCounter.relocate(625, 5);
        if (!pane.getChildren().contains(timeCounter)) {
            pane.getChildren().add(timeCounter);
        }
        
        removeImage(player[1].image);
        player[1].image.relocate(playerLocaX[1],playerLocaY+10); //ทดสอบการใส่รูปป
        addImage(player[1].image);
                
        removeImage(player[0].image);
        player[0].image.relocate(playerLocaX[0],playerLocaY+10); //ทดสอบการใส่รูปป
        addImage(player[0].image);
        
        // Random MatchCard
        RandomMatchCard();
        shuffleCard(randCard);
        // print MatchCard 
        setCard();
        // Random DestCard
        RandomDestCard();
        setDest();   
        
        System.out.println("This Turn = "+turnCount);
        
        timeCount = 5;
        
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(new KeyFrame(Duration.millis(200),new EventHandler() {
            @Override
            public void handle(Event event) {
                if(turnCount%2 == 0){
                    removeImage(snailPicA);
                    removeImage(snailPicB);
                    snailPicB.relocate(611,5);
                    addImage(snailPicB);
                    if (pane.getChildren().contains(timeCounter)) {
                    pane.getChildren().remove(timeCounter);
                    }
                    setTimeCount();
                    i1 = turnABShow();// turn A effect
                    Red_Player_BG.setEffect(null);// turn A effect
                    Blue_Player_BG.setEffect(null);// turn A effect
                    Blue_Player_BG.setEffect(i1);// turn A effect  
                    destCard[0][0].image.setVisible(true);
                    destCard[1][0].image.setVisible(true);
                    destCard[2][0].image.setVisible(false);
                    destCard[3][0].image.setVisible(false);
                    if(Choose == true){
                        destCard[0][0].image.setVisible(false);
                        destCard[1][0].image.setVisible(false);
                    }
                }else if(turnCount%2 == 1){
                    removeImage(snailPicA);
                    removeImage(snailPicB);
                    snailPicA.relocate(575,5);
                    addImage(snailPicA);
                    if (pane.getChildren().contains(timeCounter)) {
                    pane.getChildren().remove(timeCounter);
                    }
                    setTimeCount();
                    i1 = turnABShow();// turn B effect
                    Blue_Player_BG.setEffect(null);// turn B effect
                    Red_Player_BG.setEffect(null);// turn B effect
                    Red_Player_BG.setEffect(i1);// turn B effect 
                    destCard[0][0].image.setVisible(false);
                    destCard[1][0].image.setVisible(false);
                    destCard[2][0].image.setVisible(true);
                    destCard[3][0].image.setVisible(true);
                    if(Choose == true){
                        destCard[2][0].image.setVisible(false);
                        destCard[3][0].image.setVisible(false);
                    }
                }
            }
        }));
        time.playFromStart();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),new EventHandler() {

                    @Override
                    public void handle(Event event) {
                        if (timeCount > 0) {
                        timeCounter.setText(String.valueOf(timeCount));    
                        } else if (timeCount <= 3) {
                        timeCounter.setText(String.valueOf(timeCount));//เพิ่มเสียง    
                        }else {
                        timeCount = 5 ;
                        }
                        if (timeCount == 0) {
                            if(turnCount%2 == 1){
                                if(thatCardA == null){
                                    System.out.println("A Don't Choose MATCH");
                                    if(thatCardB != null){
                                        System.out.println("A Don't Choose MATCH but B choosed : thatCardA = thatCardB");
                                        thatCardA = matchCard[thatCardB.getCardX()][thatCardB.getCardY()];
                                        select[0] = true;
                                        thatCardB = null;
                                    }else{System.out.println("turn A No one choose MATCH");}
                                }
                            }else if(turnCount%2 == 0){
                                if(thatCardB == null){
                                     System.out.println("B Don't Choose MATCH");
                                    if(thatCardA != null){
                                        System.out.println("B Don't Choose MATCH but A choosed : thatCardB = thatCardA");
                                        thatCardB = matchCard[thatCardA.getCardX()][thatCardA.getCardY()];
                                        select[1] = true;
                                        thatCardA = null;
                                    }else{System.out.println("turn B No one choose MATCH");}
                                }    
                            }
                            System.out.println("time is up **************************************");    
                            turnCount++;
                            Choose = false;
                            System.out.println(turnCount);
                            timeCount = 5 ;
                            timeCounter.setText(String.valueOf(timeCount));
                        }
                        timeCount--;
                    }
                }));
        timeline.playFromStart();
        
        // Match Card && Dest Card
        scene.setOnKeyPressed((KeyEvent event) -> {
        MediaPlayer cardPlay = new MediaPlayer(card);
        cardPlay.play();
        //Dest Card //////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        if(/**/ turnCount%2 == 1 && /**/(Hp[0] > 0 && Hp[1] > 0 ) ){//Red Turn
            System.out.println("****** this B Dest turn");
            
            if(Choose == false){
            thatDestB = DestKeyClick(event,turnCount);
            if(thatDestB != null){
                DestCardWork(thatDestB.getDestX());
                Choose = true;
                DestEffect(thatDestB.getDestX(),turnCount);
                destTrans(thatDestB.getDestX());
                if(Hp[0]<= 0 || Hp[1] <= 0){
                    clearData();
                    clearSnail();
                    clearCard();
                    clearDest();
                    time.stop();
                    timeline.stop();
                    pane.getChildren().remove(hpBar[0]);
                    pane.getChildren().remove(hpBar[1]);
                    pane.getChildren().remove(timeCounter);
                    System.out.println("***************************************");
                    System.out.println("************* some one die ************");
                    System.out.println("***************************************");
                    scene.setOnKeyPressed(null);
                    endUI();
                }
            }else if(thatDestB == null){System.out.println("B Don't choose");}
            }else{System.out.println("B had choose");}
        }else if(/**/turnCount%2 == 0 && /**/(Hp[0] > 0 && Hp[1] > 0 )){//Blue Turn
            System.out.println("****** this A Dest turn");
            
            if(Choose == false){
            thatDestA = DestKeyClick(event,turnCount);
            if(thatDestA != null){
                DestCardWork(thatDestA.getDestX());
                Choose = true;
                DestEffect(thatDestA.getDestX(),turnCount);
                destTrans(thatDestA.getDestX());
                if(Hp[0]<= 0 || Hp[1] <= 0){
                    clearData();
                    clearSnail();
                    clearCard();
                    clearDest();
                    time.stop();
                    timeline.stop();
                    pane.getChildren().remove(hpBar[0]);
                    pane.getChildren().remove(hpBar[1]);
                    pane.getChildren().remove(timeCounter);
                    System.out.println("***************************************");
                    System.out.println("************* some one die ************");
                    System.out.println("***************************************");
                    scene.setOnKeyPressed(null);
                    endUI();
                }
            }else if(thatDestA == null){System.out.println("A Don't choose");}
            }else{System.out.println("A had choose");}
        } else {System.out.println("WTFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");} 


        // Match Card ////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        if(cardMatchCount<10 && cardCount > 0 && (Hp[0] > 0 && Hp[1] > 0 )){//ยังอยู่ ในเกม 
        if(/**/ turnCount%2 == 1 && /**/(Hp[0] > 0 && Hp[1] > 0 ) ){//Red Turn
           System.out.println("****** this A turn");
           
           thatCardA = MatchKeyClick(event,thatCardB);//ตัวกด แล้วทำให้เปลี่ยน turn
           if(thatCardA != null){
           MatchCardWork(thatCardA.getCardX(),thatCardA.getCardY(),thatCardA.getCardType());
           select[0] = true;
           //
           System.out.println("select A : "+select[0] );
           System.out.println("select B : "+select[1] );
           removeImage(snailPicA);
           removeImage(snailPicB);
           snailPicB.relocate(611,5);
           addImage(snailPicB);
           if (pane.getChildren().contains(timeCounter)) {
                    pane.getChildren().remove(timeCounter);
           }
           setTimeCount();
           i1 = turnABShow();// turn A effect
           Red_Player_BG.setEffect(null);// turn A effect
           Blue_Player_BG.setEffect(null);// turn A effect
           Blue_Player_BG.setEffect(i1);// turn A effect
           destCard[0][0].image.setVisible(true);
           destCard[1][0].image.setVisible(true);
           destCard[2][0].image.setVisible(false);
           destCard[3][0].image.setVisible(false);
           if(select[0] && select[1]){//B เลือกแล้ว A เลือกแล้ว
               if(thatCardA.getCardType() == thatCardB.getCardType()){//B เลือก แล้วA เลือกตรง A จับคู่ได้
                   matchCard[thatCardA.getCardX()][thatCardA.getCardY()].isMatched();
                   matchCard[thatCardB.getCardX()][thatCardB.getCardY()].isMatched();
                   //Effect******************************************************************************

                   CardEffect(thatCardA.getCardX(),thatCardA.getCardY(),turnCount);

                   //Effect******************************************************************************
                   turnCount++;
                   Choose = false;
                   timeCount = 5;
                   timeCounter.setText(String.valueOf(timeCount));
                   cardMatchCount++;
                   cardCount -= 2 ;
                   select[0] = false; thatCardA = null;
                   select[1] = false; thatCardB = null;
                   System.out.println("A Get CARD");
                   System.out.println("cMC : "+cardMatchCount+" cC : "+cardCount);
                        if(cardMatchCount == 10){
                            System.out.println("Card is all OUT");
                            clearData();
                            clearCard();
                            // Random MatchCard
                            RandomMatchCard();
                            shuffleCard(randCard);
                            // print MatchCard
                            setCard();
                        }
                        if(Hp[0]<= 0 || Hp[1] <= 0){
                            clearData();
                            clearCard();
                            clearDest();
                            clearSnail();
                            time.stop();
                            timeline.stop();
                            pane.getChildren().remove(hpBar[0]);
                            pane.getChildren().remove(hpBar[1]);
                            pane.getChildren().remove(timeCounter);
                            System.out.println("***************************************");
                            System.out.println("************* some one die ************");
                            System.out.println("***************************************");
                            scene.setOnKeyPressed(null);
                            endUI();
                        }
               }else{//B เลือก แล้วA เลือกไม่ตรง การ์ด B หมอบ แล้วเป็นตาของ B ดังนั้น การ์ด A ยังเปิดและ ยัง ถูกเลือกอยู่
                   MatchCardHide(thatCardB.getCardX(),thatCardB.getCardY(),thatCardB.getCardType());
                   turnCount++;
                   Choose = false;
                   timeCount = 5;
                   timeCounter.setText(String.valueOf(timeCount));
                   select[1] = false; thatCardB = null;
                   System.out.println("A false Not Match");
                   System.out.println("cMC : "+cardMatchCount+" cC : "+cardCount);

               }
           }else{ // A เลือก B ยังไม่เลือก เปลี่ยนรอบ

               turnCount++; 
               Choose = false;
               timeCount = 5;
               timeCounter.setText(String.valueOf(timeCount));
           }
           }
        }else if(/**/turnCount%2 == 0 && /**/(Hp[0] > 0 && Hp[1] > 0 )){//Blue Turn
           System.out.println("****** this B turn");
           thatCardB = MatchKeyClick(event,thatCardA);
           if(thatCardB != null){
           MatchCardWork(thatCardB.getCardX(),thatCardB.getCardY(),thatCardB.getCardType());
           select[1] = true;
           //
           System.out.println("select A : "+select[0] );
           System.out.println("select B : "+select[1] );
           removeImage(snailPicA);
           removeImage(snailPicB);
           snailPicA.relocate(575,5);
           addImage(snailPicA);
           if (pane.getChildren().contains(timeCounter)) {
                    pane.getChildren().remove(timeCounter);
           }
           setTimeCount();
           i1 = turnABShow();// turn B effect
           Blue_Player_BG.setEffect(null);// turn B effect
           Red_Player_BG.setEffect(null);// turn B effect
           Red_Player_BG.setEffect(i1);// turn B effect
            destCard[0][0].image.setVisible(false);
            destCard[1][0].image.setVisible(false);
            destCard[2][0].image.setVisible(true);
            destCard[3][0].image.setVisible(true);
           if(select[0] && select[1]){//A เลือกแล้ว B เลือกแล้ว
               if(thatCardA.getCardType() == thatCardB.getCardType()){//A เลือก แล้วB เลือกตรง B จับคู่ได้
                   matchCard[thatCardA.getCardX()][thatCardA.getCardY()].isMatched();
                   matchCard[thatCardB.getCardX()][thatCardB.getCardY()].isMatched();
                   //Effect******************************************************************************

                   CardEffect(thatCardB.getCardX(),thatCardB.getCardY(),turnCount);

                   //Effect******************************************************************************
                   turnCount++;
                   Choose = false;
                   timeCount = 5;
                   timeCounter.setText(String.valueOf(timeCount));
                   cardMatchCount++;
                   cardCount -= 2 ;
                   select[0] = false; thatCardA = null;
                   select[1] = false; thatCardB = null;
                   System.out.println("B Get CARD");
                   System.out.println("cMC : "+cardMatchCount+" cC : "+cardCount);
                   if(cardMatchCount == 10){
                        System.out.println("Card is all OUT");
                        clearData();
                        clearCard();
                        // Random MatchCard
                        RandomMatchCard();
                        shuffleCard(randCard);
                        // print MatchCard
                        setCard();
                   }
                    if(Hp[0]<= 0 || Hp[1] <= 0){
                        clearData();
                        clearSnail();
                        clearCard();
                        clearDest();
                        time.stop();
                        timeline.stop();
                        pane.getChildren().remove(hpBar[0]);
                        pane.getChildren().remove(hpBar[1]);
                        pane.getChildren().remove(timeCounter);
                        System.out.println("***************************************");
                        System.out.println("************* some one die ************");
                        System.out.println("***************************************");
                        scene.setOnKeyPressed(null);
                        endUI();
                        }
               }else{//A เลือก แล้วB เลือกไม่ตรง การ์ด A หมอบ แล้วเป็นตาของ A ดังนั้น การ์ด B ยังเปิดและ ยัง ถูกเลือกอยู่
                   MatchCardHide(thatCardA.getCardX(),thatCardA.getCardY(),thatCardA.getCardType());

                   turnCount++;
                   Choose = false;
                   timeCount = 5;
                   timeCounter.setText(String.valueOf(timeCount));
                   select[0] = false; thatCardA = null;
                   System.out.println("B false Not Match");
                   System.out.println("cMC : "+cardMatchCount+" cC : "+cardCount);
               }
           }else{// B เลือก A ยังไม่เลือก เปลี่ยนรอบ

               turnCount++;
               Choose = false;
               timeCount = 5;
               timeCounter.setText(String.valueOf(timeCount));
           }
           }
        }else   {System.out.println("WTFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");}
        }else   {System.out.println("Someone die || Card หมด");}
        });  
     }
    
    public void endUI(){
        Hp[1] = 50;// RED
        Hp[0] = 0;// Blue
        System.out.println("END ----------------------------");
        bgmPlayer.stop();
        MediaPlayer winPlay = new MediaPlayer(win);
        winPlay.play();
        removeImage(Red_Player_BG);
        removeImage(Blue_Player_BG);
        if(pane.getChildren().contains(timeCounter)){
        pane.getChildren().remove(timeCounter);
        }
        if(Hp[0] > 0){
            removeImage(player[1].image);
            blueWin.relocate(320, 10);
            addImage(blueWin);
            revenge.relocate(430, 450);
            addImage(revenge);
            goMenu.relocate(430, 620);
            addImage(goMenu);
            removeImage(player[1].image);
        }else if(Hp[1] > 0){
            removeImage(player[0].image);
            redWin.relocate(-310, 10);
            addImage(redWin);
            revenge.relocate(430, 450);
            addImage(revenge);
            goMenu.relocate(430, 620);
            addImage(goMenu);
            removeImage(player[0].image);
        }else System.out.println("Die two !!! WTF");
        revenge.setOnMouseMoved(e -> {       
        revenge.setEffect(i1);
        goMenu.setEffect(null);
            endClick(revenge);
        });
        goMenu.setOnMouseMoved( e -> {       
        goMenu.setEffect(i1);
        revenge.setEffect(null);
             endClick(goMenu);
        
        });  
    }
    public void endClick(ImageView img){
      img.setOnMouseClicked(e -> {
          clickPlay = new MediaPlayer(click);
          clickPlay.play();
          System.out.println("End Choosed");
          if(img.equals(revenge)){
              pane.getChildren().remove(hpBar[1]);
              pane.getChildren().remove(hpBar[0]);
              removeImage(gameplay);
              removeImage(player[1].image);
              removeImage(player[0].image);
              removeImage(blueWin);
              removeImage(redWin);
              removeImage(revenge);
              removeImage(goMenu);
              Hp[1] = 22;//re RED
              Hp[0] = 22;//re Blue
              whoFirst();
          }else if(img.equals(goMenu)){
              pane.getChildren().remove(hpBar[1]);
              pane.getChildren().remove(hpBar[0]);
              removeImage(gameplay);
              removeImage(player[1].image);
              removeImage(player[0].image);
              removeImage(blueWin);
              removeImage(redWin);
              removeImage(revenge);
              removeImage(goMenu);
              Hp[1] = 22;//re RED
              Hp[0] = 22;//re Blue
              gameMenuUI();
          }else System.out.println("WTF...END");
          
    });
  }
    
    ///////////////////////////////// pack of CardAction //////////////////////////////////
    public Card MatchKeyClick(KeyEvent event,Card c){
        Card thatCard = null;
        boolean wrongKey = true;
                    switch (event.getCode()) {
                        //////////////////// Row 1 //////////////////////
                        case NUMBER_SIGN:
                        case DIGIT3:
                        case NUMPAD3:
                            thatCard = matchCard[0][0];
                            break;
                        case DOLLAR:
                        case DIGIT4: 
                        case NUMPAD4:    
                            thatCard = matchCard[1][0];
                            break;
                        case DIGIT5:
                        case NUMPAD5:    
                            thatCard = matchCard[2][0];;
                            break;
                        case CIRCUMFLEX:
                        case DIGIT6:
                        case NUMPAD6:    
                            thatCard = matchCard[3][0];
                            break;
                        case DIGIT7:
                        case NUMPAD7:    
                            thatCard = matchCard[4][0];
                            break;
                            //////////////////// Row 2 //////////////////////
                        case E:
                            thatCard = matchCard[0][1];
                            break;
                        case R:
                            thatCard = matchCard[1][1];
                            break;
                        case T:
                            thatCard = matchCard[2][1];
                            break;
                        case Y:
                            thatCard = matchCard[3][1];
                            break;
                        case U:
                            thatCard = matchCard[4][1];
                            break;
                            //////////////////// Row 3 //////////////////////
                        case D:
                            thatCard = matchCard[0][2];
                            break;
                        case F:
                            thatCard = matchCard[1][2];
                            break;
                        case G:
                            thatCard = matchCard[2][2];
                            break;
                        case H:
                            thatCard = matchCard[3][2];
                            break;
                        case J:
                            thatCard = matchCard[4][2];
                            break;
                            //////////////////// Row 4 //////////////////////
                        case C:
                            thatCard = matchCard[0][3];
                            break;
                        case V:
                            thatCard = matchCard[1][3];
                            break;
                        case B:
                            thatCard = matchCard[2][3];
                            break;
                        case N:
                            thatCard = matchCard[3][3];
                            break;
                        case M:
                            thatCard = matchCard[4][3];
                            break;
                        default:
                            thatCard = null;
                            wrongKey = false;
                            break;
                    }
        if(thatCard != null){           
        if( c == null){
            if(wrongKey == false){
            return null;    
            }else if(wrongKey == true){
            System.out.println(thatCard.getCardX()+""+thatCard.getCardY()+" : "+null+""+null);
            System.out.println("thatCardMatch : "+thatCard.isMatch());
            System.out.println("cardType : "+ thatCard.getCardType());
            System.out.println("turnCount : "+turnCount+" thisturn : "+(turnCount%2));
            return thatCard;
            }else {System.out.println("WTF++++++++++"); return null;}
        }else if (c != null){
            if(thatCard.isMatch() == false && (thatCard.getCardX() != c.getCardX() || thatCard.getCardY() != c.getCardY())){
            System.out.println(thatCard.getCardX()+""+thatCard.getCardY()+" : "+c.getCardX()+""+c.getCardY());    
            System.out.println("thatCardMatch : "+thatCard.isMatch());
            System.out.println("cardType : "+ thatCard.getCardType());
            System.out.println("turnCount : "+turnCount+" thisturn : "+(turnCount%2));
            return thatCard;
            }else{
                System.out.println(thatCard.getCardX()+""+thatCard.getCardY()+" : "+c.getCardX()+""+c.getCardY());
                System.out.println("can't get because it ซ้ำ");
                System.out.println("thatCardMatch : "+thatCard.isMatch());
                System.out.println("cardType : "+ thatCard.getCardType());
                System.out.println("turnCount : "+turnCount+" thisturn : "+(turnCount%2));
                return null;
            }           
        }else {System.out.println("WTF++++++++++"); return null;}
        }else return null; 
    }
    
    public DestCard DestKeyClick(KeyEvent event,int turnCount){
        //red                       //blue
         //destCard[1][0] = // A     //destCard[0][0] = // K
         //destCard[1][1] = // S     //destCard[0][1] = // L

        DestCard thatDest = null;
        if(turnCount%2 == 0){
            switch (event.getCode()) {
                case A:
                thatDest = destCard[0][0];
                break;
                case S:
                thatDest = destCard[1][0];
                break;
                default:
                thatDest = null;
                break;
            }
        }else if(turnCount%2 == 1){
            switch (event.getCode()) {
                case K:
                thatDest = destCard[2][0];
                break;
                case L:
                thatDest = destCard[3][0];
                break;
                default:
                thatDest = null;
                break;
        }
    }else {System.out.println("WTF-----------------------");} 
    if(thatDest != null){
     System.out.println("DestCard"+thatDest.picture);
     return thatDest;
    }else {System.out.println("null");
     return null;
    }
    }
    public void MatchCardWork(int x , int y , int cardType){
        pane.getChildren().remove(matchCard[x][y].image);
        matchCard[x][y] = new Card(x,y,true,cardType);
        if (!pane.getChildren().contains(matchCard[x][y].image)) {//มีรูปนี้ยัง
                    matchCard[x][y].image.relocate(340+(x*120),75+(y*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[x][y].image);//ไม่มีก็ใส่
                }else{
                    matchCard[x][y].image.relocate(340+(x*120),75+(y*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[x][y].image);//ไม่มีก็ใส่
                }
    }
    public void DestCardWork(int x){
        pane.getChildren().remove(destCard[x][0].image);
        destCard[x][0] = new DestCard(x,destCardNum[x],true);
        if (!pane.getChildren().contains(destCard[x][0].image)) {//มีรูปนี้ยัง
                    destCard[x][0].image.relocate(destLocaX[x],destLocaY);//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( destCard[x][0].image);//ไม่มีก็ใส่
                }else{
                    destCard[x][0].image.relocate(destLocaX[x],destLocaY);//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( destCard[x][0].image);//ไม่มีก็ใส่
                }
    }
        
    public void MatchClickCheck(){
        Card thatCard;
        scene.setOnMouseClicked((MouseEvent) -> {
            int LocaX = (int)  MouseEvent.getX() ;
            int LocaY = (int)  MouseEvent.getY() ;
            System.out.println(LocaX+" "+LocaY);
        });
    }

     public void MatchCardHide(int x , int y , int cardType){
        pane.getChildren().remove(matchCard[x][y].image);
        matchCard[x][y] = new Card(x,y,false,cardType);
        if (!pane.getChildren().contains(matchCard[x][y].image)) {//มีรูปนี้ยัง
                    matchCard[x][y].image.relocate(340+(x*120),75+(y*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[x][y].image);//ไม่มีก็ใส่
                }else{
                    matchCard[x][y].image.relocate(340+(x*120),75+(y*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[x][y].image);//ไม่มีก็ใส่
                }
    }
    ///////////////////////////////// pack of CardAction //////////////////////////////////
    ///////////////////////////////// pack of RandCARD //////////////////////////////////
     public void RandomMatchCard(){
        for (int i = 0; i < randCard.length/2; i++) {//ครึ่งเดียวเพราะเป็นคู่ๆ
            randCard[2*i] = randCard[2*i+1] = (int)(Math.random()* 6 +1);//1-6
            System.out.println(randCard[2*i]+" "+randCard[2*i+1]); 
//            System.out.println(""+matchCard[i][i].picture);
        }
    }
     public void RandomDestCard(){
        for (int i = 0; i < destCardNum.length; i++) {
            destCardNum[i] = (int)(Math.random()*15 +1);//1-15
            System.out.println("Dest Card "+destCardNum[i]); 
        }
    }
    public void shuffleCard(int[] s){
        for (int i = 0; i < 15; i++) {
            int j = randomCard( i+1 , 15-1 );
            swap(s,i,j); 
        }
    }
    
     public int randomCard(int a, int b){
         return a + (int)(Math.random()*(b-a+1));
     }
     
     public void swap(int[] s,int a, int b) { //สลับไอเทม รับต่า a b เข้ามา
        int temp = s[a]; //สลับไอเทมในลิส
        s[a] = s[b];
        s[b] = temp;
    }
    
    ///////////////////////////////// pack of RandCARD ////////////////////////////////// 
    ///////////////////////////////// pack of Effect ////////////////////////////////////  
    public InnerShadow turnABShow(){
        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.WHITE);
        is1.setRadius(50);
        is1.setChoke(0.6);
        System.out.println("++++++++++++ InnerShadow has Created");
        return is1;   
    }
    public void atkEffect(ImageView img){
        ScaleTransition destTrans = new ScaleTransition(Duration.millis(300),img);
            destTrans.setToX(1.15);
            destTrans.setToY(1.15);
        ScaleTransition destTrans2 = new ScaleTransition(Duration.millis(100),img);
            destTrans2.setToX(1.0);
            destTrans2.setToY(1.0);
            destTrans.play();
            destTrans2.play();
        destTrans.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                destTrans2.stop();
                destTrans.stop();
                removeImage(img);
                    removeImage(player[1].image);
                    player[1].setPicture(player[1].getCharname(), 1);
                    player[1].image.relocate(playerLocaX[1],playerLocaY+10);
                    addImage(player[1].image);
                    removeImage(player[0].image);
                    player[0].setPicture(player[0].getCharname(), 1);
                    player[0].image.relocate(playerLocaX[0],playerLocaY+10);
                    addImage(player[0].image);
        }});
        
        destTrans2.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            destTrans2.stop();
        }});
    }
    public void healEffect(ImageView img){
        TranslateTransition effect = new TranslateTransition(Duration.millis(350),img);
        effect.setFromY(playerLocaY-50);
        effect.setToY(playerLocaY-80);
        effect.play();
        effect.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                effect.stop();
                removeImage(img);
        }});
    }
    public void otherEffect(ImageView img){
        ScaleTransition destTrans = new ScaleTransition(Duration.millis(350),img);
            destTrans.setToX(1.15);
            destTrans.setToY(1.15);
            destTrans.play();
            destTrans.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                destTrans.stop();
                removeImage(img);
        }});
    }
    public void destTrans(int destX){
        ScaleTransition destTrans = new ScaleTransition(Duration.millis(100),destCard[destX][0].image);
            destTrans.setToX(1.15);
            destTrans.setToY(1.15);
        ScaleTransition destTrans2 = new ScaleTransition(Duration.millis(200),destCard[destX][0].image);
            destTrans2.setToX(1.0);
            destTrans2.setToY(1.0);
            destTrans.play();
            
        destTrans.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                destTrans2.play();
                destTrans.stop();
        }});
        
        destTrans2.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("New Dest Card created");
            clearDest();
            RandomDestCard();
            destCard[0][0].image.setVisible(false);
            destCard[1][0].image.setVisible(false);
            destCard[2][0].image.setVisible(false);
            destCard[3][0].image.setVisible(false);
            setDest();
            destCard[0][0].image.setVisible(false);
            destCard[1][0].image.setVisible(false);
            destCard[2][0].image.setVisible(false);
            destCard[3][0].image.setVisible(false);
            destTrans2.stop();
        }});
    }
    public void CardEffect(int x , int y,int turnCount){
        switch(matchCard[x][y].getCardType()){
            case 1: //ATK
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                Hp[(turnCount+1)%2] -= matchCard[x][y].getDamage();
                //Char
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 2);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                // Effect
                MediaPlayer atkPlay = new MediaPlayer(atk);
                atkPlay.play();
                addImage(atk2);
                atk2.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                atkEffect(atk2);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 2: //HEAL
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                Hp[(turnCount)%2] += matchCard[x][y].getHpPlus();
                if(Hp[(turnCount)%2] > 22 ){ Hp[(turnCount)%2] = 22;}
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 3);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer healPlay = new MediaPlayer(heal);
                healPlay.play();
                addImage(heal3);
                heal3.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                healEffect(heal3);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 3: //KNI
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                Hp[(turnCount+1)%2] -= matchCard[x][y].getDamage();
                //Char
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 2);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                // Effect
                MediaPlayer atkKPlay = new MediaPlayer(atk);
                atkKPlay.play();
                addImage(atk3);
                atk3.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                atkEffect(atk3);
                Hp[(turnCount)%2] += matchCard[x][y].getHpPlus();
                if(Hp[(turnCount)%2] > 22 ){ Hp[(turnCount)%2] = 22;}
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 3);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer healKPlay = new MediaPlayer(heal);
                healKPlay.play();
                addImage(heal2);
                heal2.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                healEffect(heal2);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 4: //WIZ
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                this.turnCount += matchCard[x][y].getTurnEffect();//it work
                // Effect
                MediaPlayer sleepPlay = new MediaPlayer(wizSleep);
                sleepPlay.play();
                addImage(sleep);
                sleep.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                otherEffect(sleep);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
//                updateHPBar();
                break;    
            case 5: //ASS
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                Hp[(turnCount+1)%2] -= matchCard[x][y].getDamage();
                //Char
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 2);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                // Effect
                MediaPlayer criPlay = new MediaPlayer(atk);
                criPlay.play();
                addImage(cri);
                cri.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                atkEffect(cri);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                 break;
            case 6: //CLE
                System.out.println("type :"+matchCard[x][y].getCardType());
                System.out.println(matchCard[x][y].getAnnouce().toString());
                Hp[(turnCount)%2] += matchCard[x][y].getHpPlus();
                if(Hp[(turnCount)%2] > 22 ){ Hp[(turnCount)%2] = 22;}
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 3);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer heallPlay = new MediaPlayer(heal);
                heallPlay.play();
                addImage(heal5);
                heal5.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                healEffect(heal5);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break; 
            
        }
    }
    public void DestEffect(int x,int turnCount){
        switch(destCard[x][0].getDest()){
            case 1://Bomb
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                Hp[(turnCount)%2] -= destCard[x][0].getDamage();
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 2);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer bombPlay = new MediaPlayer(bomb);
                bombPlay.play();
                addImage(atk5);
                atk5.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                otherEffect(atk5);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 2://Bomb me
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                Hp[(turnCount+1)%2] -= destCard[x][0].getDamage();
                //Char
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 2);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                // Effect
                MediaPlayer bombMePlay = new MediaPlayer(bomb);
                bombMePlay.play();
                addImage(atk5);
                atk5.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                otherEffect(atk5);
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 3:
            case 4://HealEn
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                Hp[(turnCount)%2] += destCard[x][0].getHpPlus();
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 3);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer healPlay = new MediaPlayer(heal);
                healPlay.play();
                addImage(heal2);
                heal2.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                healEffect(heal2);
                if(Hp[(turnCount)%2] > 22 ){ Hp[(turnCount)%2] = 22;}
                System.out.println("HP "+((turnCount)%2)+" : "+Hp[(turnCount)%2]);
                System.out.println("HP "+((turnCount+1)%2)+" : "+Hp[(turnCount+1)%2]);
                updateHPBar();
                break;
            case 5:    
            case 6:
            case 7://TimeDE
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                timeCount -= destCard[x][0].getTimeEffect();
                timeCounter.setText(String.valueOf(timeCount));
                //Char
                removeImage(player[(turnCount)%2].image);
                player[(turnCount)%2].setPicture(player[(turnCount)%2].getCharname(), 2);
                player[(turnCount)%2].image.relocate(playerLocaX[(turnCount)%2],playerLocaY+10);
                addImage(player[(turnCount)%2].image);
                atkEffect(player[(turnCount)%2].image);
                // Effect
                MediaPlayer timeDPlay = new MediaPlayer(timeChange);
                timeDPlay.play();
                addImage(timeD);
                timeD.relocate(playerLocaX[(turnCount)%2],playerLocaY);
                otherEffect(timeD);
                System.out.println(timeCount);
                break;
            case 8:
            case 9:
            case 10://TimeIN
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                timeCount += destCard[x][0].getTimeEffect();
                timeCounter.setText(String.valueOf(timeCount));
                //Char
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 3);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                // Effect
                MediaPlayer timeIPlay = new MediaPlayer(timeChange);
                timeIPlay.play();
                addImage(timeI);
                timeI.relocate(playerLocaX[(turnCount+1)%2],playerLocaY);
                otherEffect(timeI);
                System.out.println(timeCount);
                break;
            case 11:
            case 12:    
            case 13:
            case 14:
            case 15://No
                removeImage(player[(turnCount+1)%2].image);
                player[(turnCount+1)%2].setPicture(player[(turnCount+1)%2].getCharname(), 2);
                player[(turnCount+1)%2].image.relocate(playerLocaX[(turnCount+1)%2],playerLocaY+10);
                addImage(player[(turnCount+1)%2].image);
                atkEffect(player[(turnCount+1)%2].image);
                System.out.println("type :"+destCard[x][0].getDest());
                System.out.println(destCard[x][0].getAnnouce().toString());
                break;
    }
    }                  
    public void updateHPBar(){
        //witdh = 550 hp = 22 -> 25 pixel/1p
        //(turnCount+1)%2 = enemy
        //(turnCount)%2 = yourself
        
        int newWidthA = Hp[1]*25;
        int newWidthB = Hp[0]*25;
        int HpBMove = 705+((22-Hp[0])*25);
        //RED 1
        pane.getChildren().remove(hpBar[1]);
        hpBar[1] = new Rectangle(25,5,newWidthA, 40);//ที่เดิมขีดน้อยลง
        hpBar[1].setFill(Paint.valueOf("Red"));//ขีดเลือด
        if (!pane.getChildren().contains(hpBar[1])) {//มีขีดเลือดยัง
            pane.getChildren().add(hpBar[1]);//ไม่มีก็ใส่
        }else { pane.getChildren().add(hpBar[1]);}
        //Blue 0
        pane.getChildren().remove(hpBar[0]);
        hpBar[0] = new Rectangle(HpBMove,5,newWidthB, 40);//ขีดลด + ย้ายที่
        hpBar[0].setFill(Paint.valueOf("Blue"));//ขีดเลือด
        if (!pane.getChildren().contains(hpBar[0])) {//มีขีดเลือดยัง
            pane.getChildren().add(hpBar[0]);//ไม่มีก็ใส่
        }else { pane.getChildren().add(hpBar[0]);}
        
        
    }
    ///////////////////////////////// pack of Effect //////////////////////////////////// 
    ///////////////////////////////// pack of add or remove /////////////////////////////////
    public void clearData(){
         thatCardA = null;//เคลียร์ A ให้ยังไม่เลือกการ์ด
         thatCardB = null;//เคลียร์ B ให้ยังไม่เลือกการ์ด
         cardCount = 0 ; // การ์ดทั้งหมดที่เรียง ให้ สร้างใหม่
         cardMatchCount = 0; // เคลียร์การ์ดที่จับคู่ได้
         count = 0;  // ตัวนับสร้างการ์ด ลิ้งกับ cardCount 
     }
    public void setCard(){
         if(count < 20){
                for (int cardX = 0; cardX < 5; cardX++) {
                    for (int cardY = 0 ; cardY < 4; cardY++) {
                    matchCard[cardX][cardY] = new Card(cardX,cardY,false,randCard[count]);
                    count++;
                    System.out.println("i = "+count);
                    if (!pane.getChildren().contains(matchCard[cardX][cardY].image)) {//มีรูปนี้ยัง
                    System.out.println("Card"+cardX+" "+cardY+"Open? : "+matchCard[cardX][cardY].isOpen());
                    matchCard[cardX][cardY].image.relocate(340+(cardX*120),75+(cardY*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[cardX][cardY].image);//ไม่มีก็ใส่
                    cardCount++;
                    System.out.println("cardCount = "+cardCount);
                    }else{
                    matchCard[cardX][cardY].image.relocate(340+(cardX*120),75+(cardY*170));//เปลี่ยนตำแหน่งรูป
                    pane.getChildren().add( matchCard[cardX][cardY].image);//ไม่มีก็ใส่
                    cardCount++;
                    System.out.println("cardCount = "+cardCount);
                    }
                }  
            }
        }
     }
    public void setDest(){
         //red
         destCard[0][0] = new DestCard(0,destCardNum[0],false);// A
         destCard[1][0] = new DestCard(1,destCardNum[1],false);// S
         //blue
         destCard[2][0] = new DestCard(2,destCardNum[2],false);// K
         destCard[3][0] = new DestCard(3,destCardNum[3],false);// L
         System.out.println(destCard[0][0].picture);
         System.out.println(destCard[1][0].picture);
         System.out.println(destCard[2][0].picture);
         System.out.println(destCard[3][0].picture);
         //
         addImage(destCard[0][0].image);
         destCard[0][0].image.relocate(45 ,560);
         addImage(destCard[1][0].image);
         destCard[1][0].image.relocate(175,560);
         addImage(destCard[2][0].image);
         destCard[2][0].image.relocate(975,560);
         addImage(destCard[3][0].image);
         destCard[3][0].image.relocate(1105,560);
         
     }

    public void clearDest(){
          removeImage(destCard[0][0].image);
          removeImage(destCard[1][0].image);
          removeImage(destCard[2][0].image);
          removeImage(destCard[3][0].image);
     }
    public void clearCard(){
         pane.getChildren().remove( matchCard[0][0].image);
         pane.getChildren().remove( matchCard[0][1].image);
         pane.getChildren().remove( matchCard[0][2].image);
         pane.getChildren().remove( matchCard[0][3].image);
         pane.getChildren().remove( matchCard[1][0].image);
         pane.getChildren().remove( matchCard[1][1].image);
         pane.getChildren().remove( matchCard[1][2].image);
         pane.getChildren().remove( matchCard[1][3].image);
         pane.getChildren().remove( matchCard[2][0].image);
         pane.getChildren().remove( matchCard[2][1].image);
         pane.getChildren().remove( matchCard[2][2].image);
         pane.getChildren().remove( matchCard[2][3].image);
         pane.getChildren().remove( matchCard[3][0].image);
         pane.getChildren().remove( matchCard[3][1].image);
         pane.getChildren().remove( matchCard[3][2].image);
         pane.getChildren().remove( matchCard[3][3].image);
         pane.getChildren().remove( matchCard[4][0].image);
         pane.getChildren().remove( matchCard[4][1].image);
         pane.getChildren().remove( matchCard[4][2].image);
         pane.getChildren().remove( matchCard[4][3].image);
         System.out.println("Card is Clear");
     }
    public void clearSnail(){
        removeImage(snailPicA);
        removeImage(snailPicB);
        pane.getChildren().remove(snailTime);
    }
    public void removeImage(ImageView img){
         if (pane.getChildren().contains(img)) {//มีรูปนี้ยัง
            pane.getChildren().remove(img);//มีก็ลบออก
        } 
     }
    public void addImage(ImageView img){
         if (!pane.getChildren().contains(img)) {//มีรูปนี้ยัง
            pane.getChildren().add(img);//ไม่มีก็ใส่
        }
//        setTimeCount();
     }
    public void setTimeCount(){
         pane.getChildren().remove(timeCounter);
         timeCounter.relocate(625, 5);
         timeCounter.setTextFill(Color.YELLOW);
         pane.getChildren().add(timeCounter);
     } 
    ///////////////////////////////// pack of add or remove /////////////////////////////////

     
     
    public static void main(String[] args) {
        launch(args);
    }

}
