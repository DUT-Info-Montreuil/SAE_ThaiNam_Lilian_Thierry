package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyPressed;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyReleased;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.MouseClick;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.ListObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.vueTerrain;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueCraft;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueObjet;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    Timeline gameLoop = new Timeline();
    private vueTerrain terrain;
    private Player player;
    private VueObjet objet;
    private VueCraft craft;

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
    @FXML private AnchorPane craftScrolling;
    @FXML private VBox craftList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.env = new Environnement(256*16,64*16);
        this.player = new Player(500,460,1,env,60);
        this.env.addentities(player);
        this.terrain = new vueTerrain(panneauJeu,env);
        this.objet = new VueObjet(paneInv,this.player);
        objet.setSlotsInventairePrimaire(Arrays.asList(
                slot1, slot2, slot3, slot4, slot5, slot6, slot7
        ));
        objet.setSlotsInvSecondaire(Arrays.asList(
                slotS1, slotS2, slotS3, slotS4, slotS5, slotS6, slotS7,
                slotS8, slotS9, slotS10, slotS11, slotS12, slotS13, slotS14
        ));
        this.craft = new VueCraft(craftPane, craftScrolling);
        craft.ajouteListe(craftList);
        craftPane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if( event.getTarget() == craftScrolling){
                event.consume();
            }
        });
        KeyPressed keyPressed = new KeyPressed(player, this, objet);
        KeyReleased keyReleased = new KeyReleased(player);
        MouseClick mouseClick = new MouseClick(player);
        panneauEntity.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        panneauEntity.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        panneauEntity.setOnMouseClicked(mouseClick);
        panneauEntity.translateXProperty().bind(this.player.getXProperty().multiply(-1).add(300));
        panneauEntity.translateYProperty().bind(this.player.getYProperty().multiply(-1).add(300));







        // demarre l'animation
        initAnimation();
        gameLoop.play();



    }

    private void creerSprite() {
        for(Entity e : env.entities) {
            //System.out.println("ajouter sprite");
            Circle r;
            if (!e.getasprite()){
                if(e instanceof Player){
                    r =new Circle(4, Color.RED);
                    e.gotasprite();
                    System.out.println("player");
                }else
                    r =new Circle(3,Color.WHITE);
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
        creerSprite();
        this.player.getInventaire().affiche();
         for(Entity e :env.entities) {
            e.seDeplace();
        }
    }

    public Pane getPanneauEntity() {
        return panneauEntity;
    }

    public void afficherCraft(){craftPane.setVisible(true);}
    public void dissimilerCraft(){craftPane.setVisible(false);}
}