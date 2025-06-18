package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Zombie extends Mobs{

    public Zombie(int x, Environnement env){
        super(x,100,1,env,20,2);
    }

    @Override
    public void agit() {}

    public ImageView getimage(){
        Image image = new Image("zombie.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutY(-30);
        return imageView;
    }

}
