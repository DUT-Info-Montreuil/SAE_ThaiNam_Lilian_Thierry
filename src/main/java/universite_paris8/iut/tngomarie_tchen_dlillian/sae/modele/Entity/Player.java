package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<Items> inventaire;
    private boolean Droite = false;
    private boolean Gauche = false;
    private boolean afficherInv;
    public Player(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
        this.inventaire = new ArrayList<Items>();
    }

    @Override
    /**
     *gere la physqiue du joueur
     */
    public void seDeplace() {
        gravité();
        deceleration();
        aDroite();
        aGauche();
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
    }

    public ArrayList<Items> getInventaire() {
        return inventaire;
    }

    public Environnement getEnv(){
        return this.env;
    }

    @Override
    public void agit() {
        Fleche f = new Fleche(this.getX(),this.getY(),5,this.getEnv(),1);
        this.env.addentities(f);
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
