package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Arc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.ArrayList;

public class Player extends Entity {
    private Inventaire inventaire;
    private double direction = 1; // 1 = droite, -1 = gauche
    private boolean Droite = false;
    private boolean Gauche = false;
    private boolean afficherInv;
    public Player(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
        this.inventaire = new Inventaire();
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
        System.out.println("Direction actuelle : " + direction);

        if (this.inventaire.objetEnMain() instanceof Arc) {
            Fleche f = new Fleche(this.getX(), this.getY(), (int) (20 * this.direction), this.getEnv(), 1);
            this.env.addentities(f);
        }

        else if(this.inventaire.objetEnMain() instanceof Epee){
            Epee epee = (Epee) this.inventaire.objetEnMain();
            System.out.println("utiliser epee");
            for(Entity mob : env.getEntities()){
                if(mob.getX() - this.getX() < 20 && mob.getY() - this.getX() <20){
                    mob.decrementerPv(((Epee) this.inventaire.objetEnMain()).getDegat(epee));
                }
            }
        }
        else{
            System.out.println("rien dans la main");
        }

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
            this.setV(this.getV()+0.5);
        }
    }

    public void aGauche() {
        if(Gauche){
            this.setV(this.getV()-0.5 );
        }
    }

    public void saute() { this.setGravité(-3);}
 }
