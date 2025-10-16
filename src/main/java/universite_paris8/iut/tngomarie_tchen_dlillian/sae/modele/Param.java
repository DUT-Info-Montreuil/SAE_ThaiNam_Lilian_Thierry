package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

import javafx.geometry.Rectangle2D;

public class Param {
    public static int width =256;
    public static int height=64;
    public static int scale=16;
    public static int scaledWidth=width*scale;
    public static int scaledHeight=height*scale;
    
    // Dimensions de l'écran pour l'affichage
    public static double screenWidth = 1920; // Valeur par défaut
    public static double screenHeight = 1080; // Valeur par défaut
    
    /**
     * Met à jour les paramètres en fonction de la taille de l'écran
     */
    public static void updateForScreen(Rectangle2D screenBounds) {
        screenWidth = screenBounds.getWidth();
        screenHeight = screenBounds.getHeight();
        
        System.out.println("Écran détecté: " + screenWidth + "x" + screenHeight);
    }
    
    /**
     * Calcule une distance basée sur un pourcentage de la largeur d'écran
     * @param ratio Le ratio (0.0 à 1.0) de la largeur d'écran
     * @return La distance en pixels
     */
    public static double getDistanceFromScreenRatio(double ratio) {
        return screenWidth * ratio;
    }
    
    /**
     * Convertit des coordonnées du monde en coordonnées de grille
     * @param worldCoord Coordonnée dans le monde
     * @return Coordonnée de grille correspondante
     */
    public static int worldToGrid(double worldCoord) {
        return (int) (worldCoord / scale);
    }
    
    /**
     * Convertit des coordonnées de grille en coordonnées du monde (centre de la tuile)
     * @param gridCoord Coordonnée de grille
     * @return Coordonnée dans le monde au centre de la tuile
     */
    public static double gridToWorldCenter(int gridCoord) {
        return gridCoord * scale + scale / 2.0;
    }
}
