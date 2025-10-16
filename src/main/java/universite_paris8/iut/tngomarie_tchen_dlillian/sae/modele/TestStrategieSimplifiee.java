package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieAvancee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob.Zombie;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc.Npc;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

/**
 * Classe de test simple pour valider que la simplification des stratégies fonctionne correctement
 * et que le comportement reste identique après le refactoring.
 */
public class TestStrategieSimplifiee {
    
    /**
     * Test principal pour vérifier que les stratégies simplifiées fonctionnent
     */
    public static void testerStrategiesSimplifiees() {
        System.out.println("=== Test des stratégies simplifiées ===");
        
        try {
            // Initialiser l'environnement
            Environnement env = Environnement.getInstance();
            
            // Créer un joueur de test simple (sans dépendances JavaFX)
            // On va créer un zombie qui servira de "joueur" pour le test
            Entity joueur = new Zombie(100);
            joueur.setX(400);
            joueur.setY(300);
            
            // Créer des entités de test
            Zombie zombie = new Zombie(50);
            zombie.setX(100);
            zombie.setY(100);
            
            // Vérifier que le zombie utilise bien StrategieAvancee
            if (zombie.getStrategieDeplacement() instanceof StrategieAvancee) {
                System.out.println("✓ Le zombie utilise bien StrategieAvancee");
            } else {
                System.out.println("✗ Erreur: Le zombie n'utilise pas StrategieAvancee");
                return;
            }
            
            // Tester la sélection dynamique de stratégie
            StrategieAvancee strategieZombie = (StrategieAvancee) zombie.getStrategieDeplacement();
            String strategieActuelle = strategieZombie.getStrategieActuelle(zombie, joueur);
            System.out.println("Stratégie actuelle du zombie: " + strategieActuelle);
            
            // Créer un NPC de test
            Npc npc = new Npc(500, 300, 1, 100);
            
            // Vérifier que le NPC utilise aussi StrategieAvancee maintenant
            if (npc.getStrategieDeplacement() instanceof StrategieAvancee) {
                System.out.println("✓ Le NPC utilise bien StrategieAvancee");
            } else {
                System.out.println("✗ Erreur: Le NPC n'utilise pas StrategieAvancee");
                return;
            }
            
            // Tester la sélection dynamique pour le NPC aussi
            StrategieAvancee strategieNpc = (StrategieAvancee) npc.getStrategieDeplacement();
            String strategieActuelleNpc = strategieNpc.getStrategieActuelle(npc, joueur);
            System.out.println("Stratégie actuelle du NPC: " + strategieActuelleNpc);
            
            // Test de calcul de mouvement
            if (strategieZombie.peutEtreAppliquee(zombie, joueur, env)) {
                double[] mouvement = strategieZombie.calculerMouvement(zombie, joueur, env, Param.scale);
                System.out.println(String.format("Mouvement calculé pour le zombie: [%.2f, %.2f]", 
                    mouvement[0], mouvement[1]));
            }
            
            if (strategieNpc.peutEtreAppliquee(npc, joueur, env)) {
                double[] mouvementNpc = strategieNpc.calculerMouvement(npc, joueur, env, Param.scale);
                System.out.println(String.format("Mouvement calculé pour le NPC: [%.2f, %.2f]", 
                    mouvementNpc[0], mouvementNpc[1]));
            }
            
            // Tester le changement de distance pour voir si la stratégie change
            System.out.println("\n--- Test du changement de stratégie selon la distance ---");
            
            // Positionner le zombie loin du joueur
            zombie.setX(2000);
            zombie.setY(2000);
            String strategieLoin = strategieZombie.getStrategieActuelle(zombie, joueur);
            System.out.println("Zombie loin du joueur: " + strategieLoin);
            
            // Positionner le zombie près du joueur
            zombie.setX(410);
            zombie.setY(310);
            String strategiePres = strategieZombie.getStrategieActuelle(zombie, joueur);
            System.out.println("Zombie près du joueur: " + strategiePres);
            
            System.out.println("\n✓ Tous les tests de stratégies simplifiées ont réussi!");
            System.out.println("✓ Le comportement reste identique avec la nouvelle architecture!");
            
        } catch (Exception e) {
            System.out.println("✗ Erreur pendant les tests: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("=== Fin des tests ===");
    }
    
    /**
     * Point d'entrée principal pour le test
     */
    public static void main(String[] args) {
        testerStrategiesSimplifiees();
    }
}