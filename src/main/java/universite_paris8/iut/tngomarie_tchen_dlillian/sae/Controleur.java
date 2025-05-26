package universite_paris8.iut.tngomarie_tchen_dlillian.sae;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.vueTerrain;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    Timeline gameLoop = new Timeline();
    private vueTerrain terrain;
    private Player player;

    @FXML
    private TilePane panneauJeu;

    @FXML
    private GridPane paneInv;

    @FXML
    private Pane slot1;
    @FXML
    private Pane slot2;
    @FXML
    private Pane slot3;
    @FXML
    private Pane slot4;
    @FXML
    private Pane slot5;
    @FXML
    private Pane slot6;
    @FXML
    private Pane slot7;

    private List<Pane> slotsInventaire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slotsInventaire = Arrays.asList(slot1, slot2, slot3, slot4, slot5, slot6, slot7);
        this.env=new Environnement(256*16,64*16);

        this.player = new Player(220,-550,1,env,60);
        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...

        this.terrain =new vueTerrain(panneauJeu,env);

        this.env.addentities(player);

        creerSprite();//a supprimer
        initAnimation();
        // demarre l'animation
        gameLoop.play();

        KeyPressed keyPressed = new KeyPressed(player, this, slot1, slot2, slot3, slot4, slot5, slot6, slot7);
        KeyReleased keyReleased = new KeyReleased(player);
        MouseClick mouseClick = new MouseClick(player);
        panneauJeu.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        panneauJeu.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        panneauJeu.setOnMouseClicked(mouseClick);

    }

    private void creerSprite() {
        for(Entity e :env.entities) {
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
                panneauJeu.getChildren().add(r);
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
        for(Entity e :env.entities) {
            e.seDeplace();
        }
    }

    public void afficherInv(){
        paneInv.setVisible(true);
    }
    public void deactiverInv() {
        paneInv.setVisible(false);


    }

    public void afficherCaseInv(Pane paneau){
        slotsInventaire.forEach(p -> p.setVisible(p == paneau));
    }
}