package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Player extends Entity {
    public Player(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }

    @Override
    public void seDeplace() {
        gravité();
        deceleration();
        this.setY(getY()+this.getGravite());
        this.setX(getX()+this.getV());
        System.out.println(this.getGravite());
    }

    @Override
    public void agit() {

    }

    public void aDroite() {
        this.setV(this.getV()+1);
    }

    public void aGauche() {
        this.setV(this.getV()-1 );
    }
 }
