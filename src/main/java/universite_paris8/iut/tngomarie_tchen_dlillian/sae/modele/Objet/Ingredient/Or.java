package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Or extends Ingredient {
    public Or(int nb){
        super(3,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Or.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }
}
