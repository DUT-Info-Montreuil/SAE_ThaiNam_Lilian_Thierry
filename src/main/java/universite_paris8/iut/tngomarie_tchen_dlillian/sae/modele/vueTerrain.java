
package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class vueTerrain {
   private TilePane decors;
  private Image[] imageTerrain;
  private Environnement env;
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

    public vueTerrain(TilePane tuilesFond, Environnement terrain) {
        this.decors=tuilesFond;
        this.env=terrain;
        vueTerraintableau();
        dessinerTerrain(env.getMap1());
    }




    public void dessinerTerrain (int[][] terrain) {
        decors.setMaxSize(40*16, 23*16); // largeur * taille tuile hauteur * nb tuiles pour pas que la fenetre quand on l'agrandit change
        decors.getChildren().clear(); // on clean le tilePane si jamais
        ImageView images ;

        for(int cases = 0; cases < terrain.length ; cases++) {
            for (int cases2 = 0; cases2 < terrain[cases].length; cases2++) {
                switch (terrain[cases][cases2]) {
                    case 0:
                        images = new ImageView(imageTerrain[0]);
                        break;

                    case 1:
                        images = new ImageView(imageTerrain[1]);
                        break;

                    case 2:
                        images = new ImageView(imageTerrain[2]);
                        break;

                    case 3:
                        images = new ImageView(imageTerrain[3]);
                        break;

                    case 4:
                        images = new ImageView(imageTerrain[4]);
                        break;
                    default:
                        images = null;
                        break;
                }
                decors.getChildren().add(images); //ajoute les images dans le tilePane
            }
        }
    }
//    public void changementTuileMinage(int numeroTuile , Environnement env, int blocChanger) {
//        if(env.getMap1()[numeroTuile] == blocChanger) {
//            ImageView img = new ImageView();
//            img.setImage(imageTerrain[blocChanger]); // utilisation des images qui sont dans le tableua créer une seul fois plus haut
//            decors.getChildren().set(numeroTuile, img); // on change l'image a la position souhaiter par la nouvelle
//        }
//    }
}