package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyPressed;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyReleased;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.MouseClick;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob.Zombie;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.ListObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.vueTerrain;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueCraft;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueObjet;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    Timeline gameLoop = new Timeline();
    private vueTerrain terrain;
    private Player player;
    private VueObjet objet;
    private VueCraft craft;

    private ListRecipe listRecipe = new ListRecipe();
    private ListObjet listObjet = new ListObjet();

    @FXML private TilePane panneauJeu;
    @FXML private Pane panneauEntity;
    @FXML private GridPane paneInv;

    @FXML private Pane slot1;
    @FXML private Pane slot2;
    @FXML private Pane slot3;
    @FXML private Pane slot4;
    @FXML private Pane slot5;
    @FXML private Pane slot6;
    @FXML private Pane slot7;

    @FXML private Pane slotS1;
    @FXML private Pane slotS2;
    @FXML private Pane slotS3;
    @FXML private Pane slotS4;
    @FXML private Pane slotS5;
    @FXML private Pane slotS6;
    @FXML private Pane slotS7;
    @FXML private Pane slotS8;
    @FXML private Pane slotS9;
    @FXML private Pane slotS10;
    @FXML private Pane slotS11;
    @FXML private Pane slotS12;
    @FXML private Pane slotS13;
    @FXML private Pane slotS14;

    @FXML private ScrollPane craftPane;
    @FXML private ProgressBar pvBar;
    @FXML private AnchorPane craftScrolling;
    @FXML private VBox craftList;

    @FXML private Pane paneMenu;
    @FXML private Button boutonMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boutonMenu.setOnAction(e -> {
                    paneMenu.setVisible(false);
                    boutonMenu.setVisible(false);
        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...
        this.env = new Environnement(Param.width*Param.scale,Param.height*Param.scale);
        this.terrain = new vueTerrain(panneauJeu,env);
        this.player = new Player(500,460,1,env,this.terrain,100);
        this.env.addentities(player);
        Zombie test = new Zombie(50,env);
        this.env.entities.add(test);

        this.objet = new VueObjet(paneInv,this.player);
        objet.setSlotsInventairePrimaire(Arrays.asList(
                slot1, slot2, slot3, slot4, slot5, slot6, slot7
        ));
        objet.setSlotsInvSecondaire(Arrays.asList(
                slotS1, slotS2, slotS3, slotS4, slotS5, slotS6, slotS7,
                slotS8, slotS9, slotS10, slotS11, slotS12, slotS13, slotS14
        ));
        this.craft = new VueCraft(craftPane, craftScrolling, this);
        craft.ajoutListe(craftList, listRecipe, listObjet);

        craftScrolling.setOnMouseClicked(ev -> {
            panneauEntity.requestFocus();
        });

        craftPane.setOnKeyPressed(ev -> {
            e.consume();
        });

        KeyPressed keyPressed = new KeyPressed(player, this, objet, craft);
        KeyReleased keyReleased = new KeyReleased(player);
        MouseClick mouseClick = new MouseClick(player);
        pvBar.progressProperty().bind(this.player.getpvPropProperty());
        panneauEntity.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        panneauEntity.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        panneauEntity.setOnMouseClicked(mouseClick);
        panneauEntity.translateXProperty().bind(this.player.getXProperty().multiply(-1).add(Param.scaledWidth/4));
        panneauEntity.translateYProperty().bind(this.player.getYProperty().multiply(-1).add(Param.scaledHeight/2));

        // demarre l'animation
        initAnimation();
        gameLoop.play();
        });
    }

    private void gererSprite() {
        for(Entity e : env.entities) {
            Circle r = null;
            if(e.getPv()>0){
            if (!e.getasprite()){
                if (e instanceof Player) {

                    e.gotasprite();

                    Player p = (Player) e;
                    ImageView joueurImage = p.getimage();
                    joueurImage.setId(p.getId());
                    joueurImage.setTranslateX(p.getX());
                    joueurImage.setTranslateY(p.getY());
                    panneauEntity.getChildren().add(joueurImage);

                    joueurImage.translateXProperty().bind(p.getXProperty());
                    joueurImage.translateYProperty().bind(p.getYProperty());

                    p.gotasprite();
                } else if (e instanceof Zombie) {
                    Zombie z = (Zombie) e;
                    ImageView zombieImage = z.getimage();
                    zombieImage.setId(z.getId());
                    zombieImage.setTranslateX(z.getX());
                    zombieImage.setTranslateY(z.getY());
                    panneauEntity.getChildren().add(zombieImage);

                    zombieImage.translateXProperty().bind(z.getXProperty());
                    zombieImage.translateYProperty().bind(z.getYProperty());

                    z.gotasprite();
                }
                else

                r =new Circle(3,Color.BLACK);
                r.setId(e.getId());
                e.gotasprite();
                System.out.println(e.getId());
                // ils ont le meme identifiant

                r.setId(e.getId());
                r.setTranslateX(e.getX());
                r.setTranslateY(e.getY());
                panneauEntity.getChildren().add(r);
                r.translateXProperty().bind(e.getXProperty());
                r.translateYProperty().bind(e.getYProperty());
            }
            }else{panneauEntity.getChildren().remove(e);}
        }
    }
    private void initAnimation() {

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    update();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    public void update() {
        //System.out.println(pvBar.progressProperty());
        gererSprite();
        this.player.getInventaire().affiche();
         for(Entity e :env.entities) {
            e.seDeplace();
        }
    }

    public Pane getPanneauEntity() {
        return panneauEntity;
    }

    public void lancer(Button button){
        button.setOnAction(e -> {
            paneMenu.setVisible(false);
            boutonMenu.setVisible(false);
        });
    }

}