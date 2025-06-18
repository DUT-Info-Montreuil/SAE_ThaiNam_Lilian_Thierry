package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.Epee;

public class PiocheEnBois extends Pioche {


    public PiocheEnBois() {
        super(10, 50, "bois");
    }




    @Override
    public void agit(Player player, double SourisX, double SourisY) {
        int x=Math.toIntExact(Math.round(SourisX)/ Param.scale);
        int y=Math.toIntExact(Math.round(SourisY)/ Param.scale);
        player.getEnv().changeBlock(y, x, 0);
        player.getVueTerrain().changementTuileMinage(x, y, 0);
    }
}