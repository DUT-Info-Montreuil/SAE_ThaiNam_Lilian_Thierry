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
}
