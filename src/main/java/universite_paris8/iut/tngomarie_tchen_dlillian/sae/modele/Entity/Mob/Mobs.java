package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Mobs extends Entity {
    private double co;
    private int degat;
    private double posInitX;
    private double posInitY;

    public Mobs(double x, double y, double v, int maxPv, int degat) {
        super(x, y, v, maxPv);
        this.posInitX = x;
        this.posInitY = y;
        this.degat = degat;
    }


    @Override
    public void agit(double sourisX, double sourisY) {
        for (Entity mob : this.env.getEntities()) {
            if (mob instanceof Player) {
                if (mob.getX() - this.getX() < 10 && mob.getY() - this.getY() < 5 && mob.getY() - this.getY() < -5 || mob.getX() - this.getX() < -10 && mob.getY() - this.getY() < 5 && mob.getY() - this.getY() < -5) {
                    mob.decrementerPv(this.degat);
                }
            }
        }
    }

private int frameCounter = 0;
    private final int frameInterval = 10;
    private final int tailleTuile = 32;
    private final int porteePoursuite = 15;  // en tuiles
    private final int porteeRetour = 25;     // en tuiles

    @Override
    public void seDeplace() {
        gravité();
        colision();
        setY(getY() + getGravite());

    }
}