package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

/**
 * Interface représentant une stratégie de déplacement pour les entités
 */
public interface StrategieDeDéplacement {
    
    /**
     * Calcule le prochain mouvement pour une entité
     * 
     * @param entity L'entité qui se déplace
     * @param target L'entité cible (généralement le joueur)
     * @param env L'environnement de jeu
     * @param tailleTuile La taille d'une tuile en pixels
     * @return Un tableau [deltaX, deltaY] représentant le déplacement à effectuer
     */
    double[] calculerMouvement(Entity entity, Entity target, Environnement env, int tailleTuile);
    
    /**
     * Vérifie si cette stratégie peut être utilisée dans le contexte actuel
     * 
     * @param entity L'entité qui se déplace
     * @param target L'entité cible
     * @param env L'environnement de jeu
     * @return true si la stratégie peut être appliquée
     */
    boolean peutEtreAppliquee(Entity entity, Entity target, Environnement env);
}
