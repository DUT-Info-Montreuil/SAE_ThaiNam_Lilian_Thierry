package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Player extends Entity {
    public Player(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }

    @Override
    public void seDeplace() {
        gravité();
        this.setY(getY()+this.getGravite());
        System.out.println(this.getGravite());
    }

    @Override
    public void agit() {

    }

    public void aDroite() {
        this.setX(this.getX() + 10);
    }

    public void aGauche() {
        this.setX(this.getX() - 10);
    }
 }
