package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class BlocInv extends Objet {
    public int nbBloc;

    public BlocInv(int id, int nb){
        super(id,nb);
    }

    @Override
    public ImageView getimage() {
        return null;
    }

    @Override
    public void agit(Player player,double sourisX, double sourisY) {
        int x=Math.toIntExact(Math.round(sourisX))/ Param.scale;
        int y=Math.toIntExact(Math.round(sourisY))/ Param.scale;
        player.getEnv().changeBlock(x,y,getIdObjet());
        player.getVueTerrain().changementTuileMinage(x,y,getIdObjet());
    }
}
