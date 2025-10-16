package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

/**
 * Classe de test pour valider les méthodes utilitaires de Param
 * Utilisée pour tester les conversions et calculs de distance
 */
public class ParamTest {
    
    public static void testParamUtilities() {
        System.out.println("=== Test des méthodes utilitaires de Param ===");
        
        // Test des constantes de base
        System.out.println("Largeur écran: " + Param.screenWidth);
        System.out.println("Hauteur écran: " + Param.screenHeight);
        System.out.println("Échelle: " + Param.scale);
        
        // Test de calcul de distance basée sur ratio
        double distance10Pct = Param.getDistanceFromScreenRatio(0.10);
        double distance15Pct = Param.getDistanceFromScreenRatio(0.15);
        System.out.println("Distance 10% écran: " + distance10Pct);
        System.out.println("Distance 15% écran: " + distance15Pct);
        
        // Test de conversion monde <-> grille
        double[] worldCoords = {100.0, 200.0, 320.0, 480.0};
        System.out.println("\n=== Conversions monde -> grille -> monde ===");
        for (double worldCoord : worldCoords) {
            int gridCoord = Param.worldToGrid(worldCoord);
            double backToWorld = Param.gridToWorldCenter(gridCoord);
            System.out.printf("Monde: %.1f -> Grille: %d -> Centre monde: %.1f%n", 
                worldCoord, gridCoord, backToWorld);
        }
        
        System.out.println("\n=== Test terminé ===");
    }
    
    public static void main(String[] args) {
        testParamUtilities();
    }
}