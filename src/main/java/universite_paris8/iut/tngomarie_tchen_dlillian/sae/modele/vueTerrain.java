package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

import javafx.scene.image.Image;

public class vueTerrain {
    private Image[] imageTerrain;
    private void vueTerraintableau() {
        this.imageTerrain = new Image[4];{
            imageTerrain[0] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture air.png");
            imageTerrain[1] =new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture terre.png");
            imageTerrain[2] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture pierre.png");
            imageTerrain[3] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture herbe.png");

        };

    }
}
