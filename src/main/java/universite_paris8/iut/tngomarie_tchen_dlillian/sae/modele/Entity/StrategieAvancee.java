package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie.StrategieDeDeplacementInterface;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie.StrategieDeDeplacementBFS;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie.StrategieDeDeplacementDirect;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

/**
 * Stratégie avancée qui choisit dynamiquement entre BFS et mouvement direct
 * selon la distance à la cible. Cette stratégie reproduit le comportement
 * intelligent des mobs qui utilisent BFS quand ils sont proches (pour éviter
 * les obstacles) et le mouvement direct quand ils sont loin (pour la rapidité).
 */
public class StrategieAvancee implements StrategieDeDeplacementInterface {
    
    // Stratégies disponibles
    private final StrategieDeDeplacementInterface strategieBFS;
    private final StrategieDeDeplacementInterface strategieDirect;
    
    // Distance de basculement (ratio de la largeur d'écran)
    private static final double RATIO_DISTANCE_BASCULEMENT = 0.10; // 10% de la largeur d'écran
    
    /**
     * Constructeur de la stratégie avancée
     */
    public StrategieAvancee() {
        this.strategieBFS = new StrategieDeDeplacementBFS();
        this.strategieDirect = new StrategieDeDeplacementDirect();
    }
    
    @Override
    public double[] calculerMouvement(Entity entity, Entity target, Environnement env, int tailleTuile) {
        // Choisir la stratégie appropriée selon la distance
        StrategieDeDeplacementInterface strategieChoisie = choisirStrategie(entity, target);
        
        // Déléguer le calcul à la stratégie choisie
        return strategieChoisie.calculerMouvement(entity, target, env, tailleTuile);
    }
    
    @Override
    public boolean peutEtreAppliquee(Entity entity, Entity target, Environnement env) {
        // La stratégie avancée peut être appliquée si au moins une des deux stratégies peut l'être
        return strategieBFS.peutEtreAppliquee(entity, target, env) || 
               strategieDirect.peutEtreAppliquee(entity, target, env);
    }
    
    /**
     * Choisit la stratégie appropriée selon le contexte
     * @param entity L'entité qui se déplace
     * @param target L'entité cible
     * @return La stratégie la plus appropriée
     */
    private StrategieDeDeplacementInterface choisirStrategie(Entity entity, Entity target) {
        // Calculer la distance entre l'entité et la cible
        double distance = Math.sqrt(
            Math.pow(entity.getX() - target.getX(), 2) + 
            Math.pow(entity.getY() - target.getY(), 2)
        );
        
        // Distance de basculement basée sur la taille de l'écran
        double distanceBasculement = Param.getDistanceFromScreenRatio(RATIO_DISTANCE_BASCULEMENT);
        
        // Si le joueur est loin, utiliser la stratégie directe (plus rapide)
        // Si le joueur est proche, utiliser BFS pour éviter les obstacles
        if (distance > distanceBasculement) {
            return strategieDirect;
        } else {
            return strategieBFS;
        }
    }
    
    /**
     * Permet de connaître la stratégie actuellement utilisée (pour debug)
     * @param entity L'entité qui se déplace
     * @param target L'entité cible
     * @return Le nom de la stratégie utilisée
     */
    public String getStrategieActuelle(Entity entity, Entity target) {
        StrategieDeDeplacementInterface strategie = choisirStrategie(entity, target);
        if (strategie instanceof StrategieDeDeplacementBFS) {
            return "BFS";
        } else if (strategie instanceof StrategieDeDeplacementDirect) {
            return "Direct";
        } else {
            return "Inconnue";
        }
    }
}