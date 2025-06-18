package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc;


import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Npc extends Entity {
    private int forwardbackward=1;
    public Npc(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
    }

    @Override
    public void seDeplace() {
        gravité();
        colision();
        setY(getY()+this.getGravite());
        double co = this.getX();
        Player player = null;
        for(int i=0 ; i<this.env.entities.size() ; i++){
            if(this.env.entities.get(i) instanceof Player){
                player = (Player) this.env.entities.get(i);
            }
        }
        if (this.getX() < co-50){
            this.setX(getX()+this.getV()+0.5);
        }
        else{
            this.setX(getX()+this.getV()-0.5);
        }
        if((this.getX() - player.getX())< 50 || (this.getX() - player.getX())> -50){
            System.out.println("joueur");
            int direction;
            if(this.getX() < player.getX()){
                direction = -1;
            }
            else{
                direction = 1;
            }
            this.setX(getX()+this.getV()+(0.5*direction));
        }
    }

    @Override
    public void agit(double souriX, double souriY) {
    }
}
