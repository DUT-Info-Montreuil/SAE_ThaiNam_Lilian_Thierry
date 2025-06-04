package universite_paris8.iut.tngomarie_tchen_dlillian.sae;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;
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

    @FXML private TilePane panneauJeu;
    @FXML private Pane panneauEntity;

    @FXML private GridPane paneInv;

    @FXML private ScrollPane craftPane;

    @FXML
    private AnchorPane craftScrolling;

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

    private List<Pane> slotsInventairePrimaire;
    private List<Pane> slotsInvSecondaire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slotsInventairePrimaire = Arrays.asList(slot1, slot2, slot3, slot4, slot5, slot6, slot7);
        slotsInvSecondaire = Arrays.asList(slotS1,slotS2,slotS3,slotS4,slotS5,slotS6,slotS7,slotS8,slotS9,slotS10,slotS11,slotS12,slotS13,slotS14);
        this.env = new Environnement(256*16,64*16);
        this.player = new Player(220,100,1,env,60);
        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...

        this.terrain = new vueTerrain(panneauJeu,env);

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
        panneauEntity.setOnMouseClicked(mouseClick);

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

    public void afficherInv(){paneInv.setVisible(true);}
    public void dissimilerInv() {paneInv.setVisible(false);}

    public void afficherCraft(){craftPane.setVisible(true);}
    public void dissimilerCraft(){craftPane.setVisible(false);}

    public void afficherCaseInv(Pane paneau){
        for (Pane slot : slotsInventairePrimaire) {
            if (slot == paneau) {
                slot.setStyle("-fx-border-color: red; -fx-border-width: 3; -fx-background-color: gray;");
            } else {
                slot.setStyle("-fx-border-color: transparent; -fx-border-width: 1; -fx-background-color: gray;");
            }
        }
    }

    public void mettreObjetVue(){
        ImageView imageView = this.player.getInventaire().objetEnMain().getimage();
        Pane slotVide = getSlotDepuisIndice(verifInvPaneCase(this.player.getInventaire()));
        imageView.setLayoutX(12);
        imageView.setLayoutY(5);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        slotVide.getChildren().add(imageView);
    }

    public int verifInvPaneCase(Inventaire inv){
        // Vérifie dans les slots secondaires (index 0 → 6)
        for (int i = 0; i < slotsInventairePrimaire.size(); i++) {
            if (slotsInventairePrimaire.get(i).getChildren().isEmpty()) {
                return i + 1;
            }
        }

        // Vérifie dans les slots secondaires (index 6 → 13)
        for (int i = 0; i < slotsInvSecondaire.size(); i++) {
            if (slotsInvSecondaire.get(i).getChildren().isEmpty()) {
                return i + 8;
            }
        }

        return -1;
    }

    public Pane getSlotDepuisIndice(int indice) {
        if (indice >= 1 && indice <= 7) {
            return slotsInventairePrimaire.get(indice - 1);
        } else if (indice >= 8 && indice <= 21) {
            return slotsInvSecondaire.get(indice - 8);
        } else {
            return null;
        }
    }
}