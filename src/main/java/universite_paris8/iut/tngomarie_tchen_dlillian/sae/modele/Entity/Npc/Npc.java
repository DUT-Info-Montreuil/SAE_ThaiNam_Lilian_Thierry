package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc;


import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Npc extends Entity {
    private int forwardbackward=1;
    public Npc(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }

    @Override
    public void seDeplace() {
        gravité();
        setY(getY()+this.getGravite());
        if (forwardbackward<20) {
            setX(getX()+1);
            forwardbackward++;
        }else if (forwardbackward>80) {
            setX(getX() - 1);
        } else if (forwardbackward<100) {
        forwardbackward-=100;
        }else{forwardbackward++;}
    }

    @Override
    public void agit() {
    }
}
