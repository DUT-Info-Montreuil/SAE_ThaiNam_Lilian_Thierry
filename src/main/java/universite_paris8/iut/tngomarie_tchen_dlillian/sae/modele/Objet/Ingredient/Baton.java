package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Baton extends Ingredient {

    public Baton(){
        super(28,0);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Baton.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }
}
