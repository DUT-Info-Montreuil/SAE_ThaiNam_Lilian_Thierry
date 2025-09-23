package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;

public class Pierre extends BlocInv {
    public Pierre(int nb){
        super(2,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Texture de pierre.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }

}
