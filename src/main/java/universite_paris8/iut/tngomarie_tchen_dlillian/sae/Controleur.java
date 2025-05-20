package universite_paris8.iut.tngomarie_tchen_dlillian.sae;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Fleche;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.vueTerrain;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    Timeline gameLoop = new Timeline();
    private vueTerrain terrain;
    private Player player;

    @FXML
    private TilePane panneauJeu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env=new Environnement(256*16,64*16);
        this.player = new Player(128,32,1,env,60);
        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...

        this.terrain =new vueTerrain(panneauJeu,env);
        this.env.addentities(player);

        geréeSprite();//a supprimer
        initAnimation();
        // demarre l'animation
        gameLoop.play();

        KeyPressed keyPressed = new KeyPressed(player);
        KeyReleased keyReleased = new KeyReleased(player);
        MouseClick mouseClick = new MouseClick(player);
        panneauJeu.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        panneauJeu.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        panneauJeu.setOnMouseClicked(mouseClick);

    }

    private void geréeSprite() {
        for(Entity e :env.entities) {
            //System.out.println("ajouter sprite");
            ImageView r;
            if (!e.getasprite()){
                if(e instanceof Player){
                    r = new ImageView("Petit Bonhomme.png");
                    e.gotasprite();
                    System.out.println("player");
                } else if (e instanceof Fleche) {
                    r = new ImageView("Petit Bonhomme.png");
                }else r = new ImageView("Petit Bonhomme.png");
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
        geréeSprite();
        for(Entity e :env.entities) {
            e.seDeplace();
        }
    }
}