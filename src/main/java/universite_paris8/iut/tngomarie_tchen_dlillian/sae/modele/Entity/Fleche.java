package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.ArrayList;

public class Fleche extends Entity {
    private Player player;
    public Fleche(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }

    public void seDeplace() {
        gravité();
        deceleration();
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
    }

    public void agit(){

    }


}
