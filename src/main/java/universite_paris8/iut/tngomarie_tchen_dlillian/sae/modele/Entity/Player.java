package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue.vueTerrain;

public class Player extends Entity {
    private Inventaire inventaire;
    private vueTerrain vueTerrain;
    private DoubleProperty pvProp;
    private double direction = 1; // 1 = droite, -1 = gauche
    private boolean Droite = false;
    private boolean Gauche = false;
    private boolean afficherInv;
    public Player(double x, double y, double v, Environnement env,vueTerrain vue, int pv) {
        super(x, y, v, env, pv);
        double temp=pv/100;
        this.pvProp = new SimpleDoubleProperty(temp);
        this.inventaire = new Inventaire();
        this.vueTerrain = vue;
    }
    public DoubleProperty getpvPropProperty() {return this.pvProp;}
    public void degatjoueur(int i){
        decrementerPv(i);
        double temp=this.getPv()/100;
        this.pvProp.setValue(temp);

    }
    @Override
    /**
     *gere la physqiue du joueur
     */
    public void seDeplace() {
        gravité();
        deceleration(0.9675);
        aDroite();
        aGauche();
        colision();
        //System.out.println(this.getGravite());
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public Environnement getEnv(){
        return this.env;
    }
    public vueTerrain getVueTerrain() {return this.vueTerrain;}

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public double getDirection() {
        return this.direction;
    }


    @Override
    public void agit(double sourisX,double sourisY) {
        if(this.getInventaire().objetEnMain()!=null){this.getInventaire().objetEnMain().agit(this,sourisX,sourisY);}
    }

    public void activeDroite(){
        this.Droite = true;
    }
    public void deactiveDroite(){
        this.Droite = false;
    }
    public void activeGauche(){
        this.Gauche = true;
    }
    public void deactiveGauche(){
        this.Gauche = false;
    }

    public void aDroite() {
        if(Droite){
            this.setV(this.getV()+1);
        }
    }

    public void aGauche() {
        if(Gauche){
            this.setV(this.getV()-1 );
        }
    }

    public void saute() { this.setGravité(-3);}

    public ImageView getimage(){
        Image image = new Image("joueur.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setLayoutY(-37);
        imageView.setLayoutX(-25);
        return imageView;
    }
 }
