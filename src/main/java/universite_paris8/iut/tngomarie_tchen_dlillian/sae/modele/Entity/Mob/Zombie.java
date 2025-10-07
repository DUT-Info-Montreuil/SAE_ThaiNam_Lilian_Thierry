package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Zombie extends Mobs{

    public Zombie(int x){
        super(x,100,1,20,2);
    }

    @Override
    public void agit(double SourisX, double SourisY) {}


    public ImageView getimage(){
        Image image = new Image("zombie.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutY(-29);
        imageView.setLayoutX(-19);
        return imageView;
    }
}
