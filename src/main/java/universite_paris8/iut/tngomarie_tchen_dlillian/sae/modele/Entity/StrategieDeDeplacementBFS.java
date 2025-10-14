package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.*;

/**
 * Stratégie de déplacement utilisant l'algorithme BFS (Breadth-First Search)
 * pour trouver le chemin le plus court vers une cible
 */
public class StrategieDeDeplacementBFS implements StrategieDeDeplacementInterface {
    
    // Distance maximale de détection par défaut (basée sur la taille de l'écran)
    private static final double DISTANCE_DETECTION_RATIO = 0.15; // 15% de la largeur d'écran
    
    // Portée de déplacement par défaut (basée sur la largeur de la carte)
    private static final int PORTEE_DEPLACEMENT_RATIO = 20; // En tuiles, reste constant
    
    @Override
    public double[] calculerMouvement(Entity entity, Entity target, Environnement env, int tailleTuile) {
        // Utiliser la taille de tuile depuis Param si non spécifiée
        int tuileSize = (tailleTuile > 0) ? tailleTuile : Param.scale;
        
        // Convertir les coordonnées du monde en coordonnées de grille
        int entityX = worldToGrid(entity.getX(), tuileSize);
        int entityY = worldToGrid(entity.getY(), tuileSize);
        int targetX = worldToGrid(target.getX(), tuileSize);
        int targetY = worldToGrid(target.getY(), tuileSize);
        
        // Déterminer les limites de déplacement (basées sur la largeur de la carte)
        int minBoundX = Math.max(0, entityX - PORTEE_DEPLACEMENT_RATIO);
        int maxBoundX = Math.min(env.getMap1()[0].length - 1, entityX + PORTEE_DEPLACEMENT_RATIO);
        
        // Utiliser BFS pour trouver le prochain mouvement optimal
        int[] nextMove = findNextMove(env, entityX, entityY, targetX, targetY, minBoundX, maxBoundX);
        
        // Convertir les coordonnées de grille cibles en coordonnées du monde
        double targetWorldX = gridToWorldCenter(nextMove[0], tuileSize);
        double targetWorldY = gridToWorldCenter(nextMove[1], tuileSize);
        
        // Calculer le vecteur de déplacement
        double deltaX = targetWorldX - entity.getX();
        double deltaY = targetWorldY - entity.getY();
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
        double distance = Math.abs(entity.getX() - target.getX());
        return distance <= distanceDetection && env.getMap1() != null;
    }
    
    /**
     * Trouve le prochain mouvement optimal pour se rapprocher d'une cible en utilisant BFS
     * 
     * @param env L'environnement de jeu
     * @param startX Position X de départ (en coordonnées de grille)
     * @param startY Position Y de départ (en coordonnées de grille)
     * @param targetX Position X de la cible (en coordonnées de grille)
     * @param targetY Position Y de la cible (en coordonnées de grille)
     * @param minBoundX Limite gauche de la zone de recherche (en coordonnées de grille)
     * @param maxBoundX Limite droite de la zone de recherche (en coordonnées de grille)
     * @return Un tableau [x, y] représentant les coordonnées de grille du prochain mouvement optimal
     */
    public static int[] findNextMove(Environnement env, int startX, int startY, 
                                     int targetX, int targetY, int minBoundX, int maxBoundX) {
        
        int[][] map = env.getMap1();
        if (map == null) {
            return new int[]{startX, startY}; // Pas de mouvement si pas de carte
        }
        
        int hauteur = map.length;
        int largeur = map[0].length;
        
        // Vérifications des limites
        if (!isValidPosition(startX, startY, largeur, hauteur) || 
            !isValidPosition(targetX, targetY, largeur, hauteur)) {
            return new int[]{startX, startY};
        }
        
        // Si on est déjà à la cible, pas besoin de bouger
        if (startX == targetX && startY == targetY) {
            return new int[]{startX, startY};
        }
        
        // Matrice des distances depuis la cible
        int[][] distance = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            Arrays.fill(distance[i], -1);
        }
        
        // File pour le BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{targetX, targetY});
        distance[targetY][targetX] = 0;
        
        // Directions: droite, gauche, bas, haut
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        // BFS depuis la cible vers toutes les cases accessibles
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            
            for (int[] dir : directions) {
                int nextX = currentX + dir[0];
                int nextY = currentY + dir[1];
                
                // Vérifier les limites et contraintes
                if (isValidMove(nextX, nextY, largeur, hauteur, minBoundX, maxBoundX, env, distance)) {
                    distance[nextY][nextX] = distance[currentY][currentX] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        
        // Trouver la meilleure direction depuis la position de départ
        return findBestDirection(startX, startY, distance, directions, largeur, hauteur, 
                                minBoundX, maxBoundX, env);
    }
    
    /**
     * Vérifie si une position est valide dans la grille
     */
    private static boolean isValidPosition(int x, int y, int largeur, int hauteur) {
        return x >= 0 && y >= 0 && x < largeur && y < hauteur;
    }
    
    /**
     * Vérifie si un mouvement est valide (dans les limites, marchable, non visité)
     */
    private static boolean isValidMove(int x, int y, int largeur, int hauteur, 
                                      int minBoundX, int maxBoundX, Environnement env, 
                                      int[][] distance) {
        // Vérification des limites de la grille
        if (!isValidPosition(x, y, largeur, hauteur)) {
            return false;
        }
        
        // Vérification des limites de la zone de recherche
        if (x < minBoundX || x > maxBoundX) {
            return false;
        }
        
        // Vérification si la case n'a pas déjà été visitée
        if (distance[y][x] != -1) {
            return false;
        }
        
        // Vérification si la case est marchable
        return env.isWalkable(x, y);
    }
    
    /**
     * Trouve la meilleure direction à prendre depuis la position de départ
     */
    private static int[] findBestDirection(int startX, int startY, int[][] distance, 
                                          int[][] directions, int largeur, int hauteur,
                                          int minBoundX, int maxBoundX, Environnement env) {
        
        int bestX = startX;
        int bestY = startY;
        int minDist = Integer.MAX_VALUE;
        
        // Si la position de départ n'est pas accessible à la cible
        if (distance[startY][startX] == -1) {
            return new int[]{startX, startY};
        }
        
        // Examiner toutes les directions possibles
        for (int[] dir : directions) {
            int nextX = startX + dir[0];
            int nextY = startY + dir[1];
            
            // Vérifier si cette direction est valide
            if (isValidPosition(nextX, nextY, largeur, hauteur) && 
                nextX >= minBoundX && nextX <= maxBoundX &&
                env.isWalkable(nextX, nextY) &&
                distance[nextY][nextX] != -1 && 
                distance[nextY][nextX] < minDist) {
                
                minDist = distance[nextY][nextX];
                bestX = nextX;
                bestY = nextY;
            }
        }
        
        return new int[]{bestX, bestY};
    }
    
    /**
     * Convertit les coordonnées du monde en coordonnées de grille
     */
    public static int worldToGrid(double worldCoord, int tileSize) {
        return (int) (worldCoord / tileSize);
    }
    
    /**
     * Convertit les coordonnées de grille en coordonnées du monde (centre de la tuile)
     */
    public static double gridToWorldCenter(int gridCoord, int tileSize) {
        return gridCoord * tileSize + tileSize / 2.0;
    }
}