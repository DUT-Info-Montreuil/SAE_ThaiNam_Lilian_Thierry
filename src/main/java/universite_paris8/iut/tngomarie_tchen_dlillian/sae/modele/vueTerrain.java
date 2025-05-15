package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class vueTerrain {
/*    private Image[] imageTerrain;
    private void vueTerraintableau() {
        this.imageTerrain = new Image[4];{
            imageTerrain[0] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture air.png");
            imageTerrain[1] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture terre.png");
            imageTerrain[2] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture pierre.png");
            imageTerrain[3] = new Image("test/src/main/resources/universite_paris8/iut/tngomarie_tchen_dlillian/sae/blocktexture/Texture herbe.png");

        };

    }

     //@param tuilesFond  Le TilePane au se trouve les Tuiles de la map
     //@param terrain 		Terrain utilise pour la creation de la Vue de celui ci

    public TerrainVue(TilePane tuilesFond, Terrain terrain) {
        this.tuilesFond = tuilesFond;
        tableauImageTerrain();
    }




    public void dessinerTerrain (int[] terrain) {
        //tuilesFond.setMaxSize(40*32, 23*32); // largeur * taille tuile hauteur * nb tuiles pour pas que la fenetre quand on l'agrandit change
        tuilesFond.getChildren().clear(); // on clean le tilePane si jamais
        ImageView images ;

        for(int cases = 0; cases < terrain.length ; cases++) {
            switch(terrain[cases]) {
                case 0 :
                    images = new ImageView(imageTerrain[0]);
                    break;

                case 1 :
                    images = new ImageView(imageTerrain[1]);
                    break;

                case 2 :
                    images = new ImageView(imageTerrain[2]);
                    break;

                case 3 :
                    images = new ImageView(imageTerrain[3]);
                    break;

                case 4 :
                    images = new ImageView(imageTerrain[4]);
                    break;

                case 5 :
                    images = new ImageView(imageTerrain[5]);
                    break;

                case 6 :
                    images = new ImageView(imageTerrain[6]);
                    break;

                case 7 :
                    images = new ImageView(imageTerrain[7]);
                    break;

                case 8 :
                    images = new ImageView(imageTerrain[8]);
                    break;

                default :
                    images = null;
                    break;
            }
            tuilesFond.getChildren().add(images); //ajoute les images dans le tilePane
        }
    }
    public void changementTuileMinage(int numeroTuile , Environnement env, int blocChanger) {
        if(env.getTabTerrain()[numeroTuile] == blocChanger) {
            ImageView img = new ImageView();
            img.setImage(imageTerrain[blocChanger]); // utilisation des images qui sont dans le tableua créer une seul fois plus haut
            tuilesFond.getChildren().set(numeroTuile, img); // on change l'image a la position souhaiter par la nouvelle
        }
    }

}
}
