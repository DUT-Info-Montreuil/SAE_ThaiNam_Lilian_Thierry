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

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyPressed;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.KeyReleased;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.listeneur.MouseClick;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob.Zombie;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Craft;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.ListObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueCraft;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.VueObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.vueTerrain;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controleur implements Initializable{
    public static Environnement env;
    Timeline gameLoop = new Timeline();
    private vueTerrain terrain;
    private Player player;
    private VueObjet objet;
    private VueCraft vueCraft;
    private Craft craft;

    private ListRecipe listRecipe = new ListRecipe();
    private ListObjet listObjet = new ListObjet();

    @FXML private TilePane panneauJeu;
    @FXML private Pane panneauEntity;
    @FXML private ScrollPane scrollPane;
    @FXML private GridPane paneInv;
    @FXML private Rectangle pvBar1;

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

        boutonMenu.setOnAction(y -> {
                    paneMenu.setVisible(false);
                    boutonMenu.setVisible(false);

        this.env = Environnement.getInstance();
        this.terrain = new vueTerrain(panneauJeu,env);
        this.player = new Player(500,460,1,this.terrain,100);
        this.env.addentities(player);
        Zombie test = new Zombie(50);
       this.env.entities.add(test);
            this.craft = new Craft(Inventaire.getInstance());
        this.objet = new VueObjet(paneInv,this.player);
        objet.setSlotsInventairePrimaire(Arrays.asList(
                slot1, slot2, slot3, slot4, slot5, slot6, slot7
        ));
        objet.setSlotsInvSecondaire(Arrays.asList(
                slotS1, slotS2, slotS3, slotS4, slotS5, slotS6, slotS7,
                slotS8, slotS9, slotS10, slotS11, slotS12, slotS13, slotS14
        ));
        this.vueCraft = new VueCraft(craftPane, craftScrolling, this, craft);
        vueCraft.ajoutListe(craftList, listRecipe, listObjet);


        craftScrolling.setOnMouseClicked(e -> {
            panneauEntity.requestFocus();
        });
        
        // CORRECTION 2: S'assurer que l'inventaire redonne le focus au jeu
        paneInv.setOnMouseClicked(e -> {
            panneauEntity.requestFocus();
        });

        craftPane.setOnKeyPressed(e -> {
            e.consume();
        });

        KeyPressed keyPressed = new KeyPressed(player, this, objet, vueCraft);
        KeyReleased keyReleased = new KeyReleased(player);
        MouseClick mouseClick = new MouseClick(player);
        panneauEntity.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        panneauEntity.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        double centerX = Param.screenWidth / 2.0;
        double centerY = Param.screenHeight / 2.0;
        panneauEntity.translateXProperty().bind(this.player.getXProperty().multiply(-1).add(centerX));
        panneauEntity.translateYProperty().bind(this.player.getYProperty().multiply(-1).add(centerY));

        scrollPane.setPrefSize(Param.screenWidth, Param.screenHeight);

        panneauEntity.setFocusTraversable(true);
        panneauEntity.requestFocus();

        panneauEntity.setOnMouseClicked(e -> {
            panneauEntity.requestFocus();
            mouseClick.handle(e);
        });

        panneauEntity.getScene().getRoot().setStyle("-fx-background-color: #b8fbff;");
            pvBar1.widthProperty().bind(player.getpvPropProperty().multiply(3));

        // demarre l'animation
        initAnimation();
        gameLoop.play();
        });

    }
    private void gererSprite() {
        for(Entity e : env.entities) {
            ImageView image=null;
            if(e.getPv()>0){
            if (!e.getasprite()){
                if (e instanceof Player) {
                    image= e.getimage();
                    e.gotasprite();
                } else if (e instanceof Zombie) {

                    image = e.getimage();
                    e.gotasprite();
                }
                else

                image = new ImageView("default.png");
                image.setId(e.getId());
                e.gotasprite();
                System.out.println(e.getId());
                // ils ont le meme identifiant

                image.setId(e.getId());
                image.setTranslateX(e.getX());
                image.setTranslateY(e.getY());
                panneauEntity.getChildren().add(image);
                image.translateXProperty().bind(e.getXProperty());
                image.translateYProperty().bind(e.getYProperty());
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