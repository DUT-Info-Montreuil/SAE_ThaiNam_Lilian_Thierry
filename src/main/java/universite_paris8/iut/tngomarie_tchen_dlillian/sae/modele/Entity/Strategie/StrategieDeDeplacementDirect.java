package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

/**
 * Stratégie de déplacement direct - se dirige directement vers la cible
 * sans éviter les obstacles (mouvement simple)
 */
public class StrategieDeDeplacementDirect implements StrategieDeDeplacementInterface {
    
    // Distance maximale de détection par défaut (basée sur la taille de l'écran)
    private static final double DISTANCE_DETECTION_RATIO = 0.10; // 10% de la largeur d'écran
    
    @Override
    public double[] calculerMouvement(Entity entity, Entity target, Environnement env, int tailleTuile) {
        // Calculer le vecteur direct vers la cible
        double deltaX = target.getX() - entity.getX();
        double deltaY = target.getY() - entity.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        
        // Normaliser le mouvement selon la vitesse de l'entité
        if (distance > 0.5) {
            double vitesse = entity.getV();
            deltaX = (deltaX / distance) * vitesse;
            deltaY = (deltaY / distance) * vitesse;
        } else {
            deltaX = 0;
            deltaY = 0;
        }
        
        return new double[]{deltaX, deltaY};
    }
    
    @Override
    public boolean peutEtreAppliquee(Entity entity, Entity target, Environnement env) {
        // Vérifier si la cible est dans la portée de détection (basée sur la taille d'écran)
        double distanceDetection = Param.getDistanceFromScreenRatio(DISTANCE_DETECTION_RATIO);
        double distance = Math.sqrt(
            Math.pow(entity.getX() - target.getX(), 2) + 
            Math.pow(entity.getY() - target.getY(), 2)
        );
        return distance <= distanceDetection;
    }
}