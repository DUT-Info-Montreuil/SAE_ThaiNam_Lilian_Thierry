package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Pierre extends BlocInv {
    public Pierre(int nb){
        super(1,nb);
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("default.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }

}
