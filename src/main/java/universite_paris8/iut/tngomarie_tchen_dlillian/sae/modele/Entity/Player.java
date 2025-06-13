package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.FlecheObjet;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Player extends Entity {
    private Inventaire inventaire;
    private double direction = 1; // 1 = droite, -1 = gauche
    private boolean Droite = false;
    private boolean Gauche = false;
    private boolean afficherInv;
    public Player(double x, double y, double v, Environnement env, int pv) {
        super(x, y, v, env, pv);
        this.inventaire = new Inventaire();
    }

    @Override
    /**
     *gere la physqiue du joueur
     */
    public void seDeplace() {
        gravité();
        deceleration(0.8);
        aDroite();
        aGauche();
        colision();
        System.out.println(this.getGravite());
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public Environnement getEnv(){
        return this.env;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public double getDirection() {
        return this.direction;
    }


    @Override
    public void agit() {
        this.getInventaire().objetEnMain().agit(this);
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
 }
