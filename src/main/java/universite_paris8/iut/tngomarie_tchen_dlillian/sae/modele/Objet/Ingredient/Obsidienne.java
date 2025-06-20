package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.BlocInv;

public class Obsidienne extends BlocInv {
    public Obsidienne(int nb){
        super(6,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("obsidienne.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }
}
