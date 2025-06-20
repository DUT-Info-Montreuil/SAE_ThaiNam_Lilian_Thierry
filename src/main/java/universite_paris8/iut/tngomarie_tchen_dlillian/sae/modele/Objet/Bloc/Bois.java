package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.Ingredient;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Bois extends BlocInv   {
    public Bois(int nb) {
        super(0, nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Bois.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }
}
