
package universite_paris8.iut.tngomarie_tchen_dlillian.sae.vue;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class vueTerrain {
    private TilePane decors;
    private Image[] imageTerrain;
    private Environnement env;
    private void vueTerraintableau() {
        this.imageTerrain = new Image[5];{
            imageTerrain[0] = new Image("Texture vide.png");
            imageTerrain[1] = new Image("Texture terre.png");
            imageTerrain[2] = new Image("Texture de pierre.png");
            imageTerrain[3] = new Image("Texture herbe.png");
            imageTerrain[4] = new Image("Bois.png");

        }

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

        decors.setPrefSize(Param.scaledWidth, Param.scaledHeight); // largeur * taille tuile hauteur * nb tuiles pour pas que la fenetre quand on l'agrandit change
// largeur * taille tuile hauteur * nb tuiles pour pas que la fenetre quand on l'agrandit change
        decors.getChildren().clear(); // on clean le tilePane si jamais

        ImageView images ;

        for(int cases = 0; cases < terrain.length ; cases++) {
            for (int cases2 = 0; cases2 < terrain[cases].length; cases2++) {
                images = switch (terrain[cases][cases2]) {
                    case 0 -> new ImageView(imageTerrain[0]);
                    case 1 -> new ImageView(imageTerrain[1]);
                    case 2 -> new ImageView(imageTerrain[2]);
                    case 3 -> new ImageView(imageTerrain[3]);
                    case 4 -> new ImageView(imageTerrain[4]);
                    default -> null;
                };
                decors.getChildren().add(images); //ajoute les images dans le tilePane
            }
        }
    }

    public void changementTuileMinage(int x, int y , int blocChanger) {
            ImageView img = new ImageView();
           img.setImage(this.imageTerrain[blocChanger]); // utilisation des images qui sont dans le tableua créer une seul fois plus haut
            decors.getChildren().set((y*Param.width)+x,img); // on change l'image a la position souhaiter par la nouvelle
    }
}