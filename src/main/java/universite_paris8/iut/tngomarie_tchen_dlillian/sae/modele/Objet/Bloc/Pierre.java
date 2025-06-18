package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Pierre extends BlocInv {
    public Pierre(int nb){
        super(1,nb);
    }

    @Override
    public void agit(Player player,double SourisX,double SourisY) {
            int x=Math.toIntExact(Math.round(SourisX)/ Param.scale);
            int y=Math.toIntExact(Math.round(SourisY)/ Param.scale);
            player.getEnv().changeBlock(x,y,0);
            player.getVueTerrain().changementTuileMinage(x,y,0);
    }
}
