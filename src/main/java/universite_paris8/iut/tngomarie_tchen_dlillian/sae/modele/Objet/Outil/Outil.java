package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public abstract class Outil extends Objet {

    public Outil(int id, int nb) {
        super(id, nb);
    }

    @Override
    public ImageView getimage() {
        return new ImageView("default.png");
    }

    @Override
    public abstract void agit(Player player,double SourisX, double SourisY); {

    }
}
