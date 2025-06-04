package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Arc extends Objet {

    public Arc(int id){
        super(id);
    }

    public ImageView getimage(){
        Image image = new Image("Arc Bois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }


}
