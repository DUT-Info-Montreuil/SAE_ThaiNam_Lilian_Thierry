package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Pierre extends BlocInv {
    public Pierre(int nb){
        super(2,nb);
    }

    @Override
    public void agit(Player player,double SourisX,double SourisY) {
            int x=Math.toIntExact(Math.round(SourisX/ Param.scale));
            int y=Math.toIntExact(Math.round(SourisY/ Param.scale));
            if(player.getEnv().getMap1()[y][x]==0) {
                player.getEnv().changeBlock(y, x, this.getIdObjet());
                player.getVueTerrain().changementTuileMinage(x, y, this.getIdObjet());
            }
    }

    @Override
    public ImageView getimage() {
        Image image = new Image("Texture de pierre.png");
        ImageView imageView = new ImageView(image);

        return imageView;
    }

}
