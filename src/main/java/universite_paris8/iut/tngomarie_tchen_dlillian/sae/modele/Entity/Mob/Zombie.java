package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Zombie extends Mobs{

    public Zombie(int x, Environnement env){
        super(x,100,1,env,20,2);
    }

    @Override
    public void agit() {}


}
