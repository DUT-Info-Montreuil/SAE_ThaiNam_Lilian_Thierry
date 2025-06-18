package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.ArrayList;

public class Fleche extends Entity {
    private double dir;
    public Fleche(double x, double y, int v, Environnement env, int pv,double dir) {
        super(x, y, v, env, pv);
        this.dir = dir;
    }

    public void seDeplace() {
        gravité();
        deceleration(0.975);
        colision();
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
    }

    public void agit(double SourisX, double SourisY){

    }


}
