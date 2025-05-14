package universite_paris8.iut.tngomarie_tchen_dlillian.sae;
/* CORRECTION DU TP2 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.BoucleDeJeux;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc.Npc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    private Player player = new Player(128,32,10,env,60);
    private Npc npc = new Npc(140,50,10,env,100);

    @FXML
    private Pane panneauJeu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env=new Environnement(256,64);

        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...
        this.panneauJeu.setMaxWidth(305); // 5== largeur de l'image ou du rectangle.
        this.panneauJeu.setMaxHeight(305);
        this.env.addentities(player);
        this.env.addentities(npc);
        creerSprite();


        panneauJeu.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(player));
    }

    private void creerSprite() {
        for(Entity e :env.entities) {
            //System.out.println("ajouter sprite");
            Circle r;
            if (!e.getasprite()){
                if(e instanceof Player){
                    r =new Circle(4, Color.RED);
                }else
                    r =new Circle(3,Color.WHITE);
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
        Timeline gameLoop = new Timeline();
        int temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                        System.out.println("un tour");


                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void aGauche(){
        player.setX(player.getX() - 10);
    }

    public void aDroite(){
        player.setX(player.getX() + 10);
    }


    public void rafraichirPanneauJeu(){}

}