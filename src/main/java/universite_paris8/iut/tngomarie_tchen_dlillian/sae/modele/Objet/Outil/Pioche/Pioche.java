package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Outil;

public class Pioche extends Outil {
    private int durabilité;
    private String type;

    public Pioche(int id, int dur, String type) {
        super(id, 1);
        this.durabilité = dur;
        this.type = type;
    }

    public ImageView getimage(){
        Image image = new Image("Pioche en bois.png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public void agit(Player player, double SourisX, double SourisY) {
        int x=Math.toIntExact(Math.round(SourisX)/ Param.scale);
        int y=Math.toIntExact(Math.round(SourisY)/ Param.scale);
            player.getEnv().changeBlock(y, x, 0);
            player.getVueTerrain().changementTuileMinage(x, y, 0);
    }

}
