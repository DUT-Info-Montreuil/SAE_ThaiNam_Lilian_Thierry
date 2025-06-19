package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.projectile;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

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

    @Override
    public ImageView getimage() {
        return null;
    }

    public void agit(double SourisX, double SourisY){

    }


}
