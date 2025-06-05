package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc;


import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Npc extends Entity {
    private int forwardbackward=1;
    public Npc(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }
    double co = this.getX();
    @Override
    public void seDeplace() {
        gravité();
        colision();
        setY(getY()+this.getGravite());

        int rand = (int)(Math.random() * 3);
        switch (rand) {
            case 0:
                if(this.getX()>co - 20 && this.getX()<co + 20){
                    this.setX(getV()+5);
                }
                break;
            case 1:
                if(this.getX()>co - 20 && this.getX()<co + 20){
                    this.setX(getV()-5);
                }
                break;
            case 2:
                this.setX(getV());
                break;
        };
    }

    @Override
    public void agit() {
    }
}
