package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Sac extends Outil {
    public Sac(){
        super(27,1);
    }

    @Override
    public int getIdObjet() {
        return super.getIdObjet();
    }

    @Override
    public ImageView getimage() {
        return null;
    }

    @Override
    public void agit(Player player,double SourisX, double SourisY) {
    }
}
