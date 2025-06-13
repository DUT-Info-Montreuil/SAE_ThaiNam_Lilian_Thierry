package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc;


import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Npc extends Entity {
    private int forwardbackward=1;
    public double co;
    public Npc(double x, double y, int v, Environnement env, int pv) {
        super(x, y, v, env, pv);
        this.co = x;
    }

    @Override
    public void seDeplace() {
        gravité();  // Applique la gravité sur le NPC
        colision(); // Gère les collisions avec l'environnement
        setY(getY() + getGravite()); // Met à jour la position verticale selon la gravité actuelle

        Player joueur = null;

        // Cherche le joueur parmi toutes les entités
        for (Entity e : env.getEntities()) {
            if (e instanceof Player) {
                joueur = (Player) e;
                break;
            }
        }

        int tailleTuile = 32; // Taille d'un bloc

        // Limite horizontale de la zone de déplacement (en pixels)
        double minX = (co - 50) * tailleTuile; // Limite gauche (50 blocs
        double maxX = (co + 50) * tailleTuile; // Limite droite (50 blocs

        // Si trop à gauche, on déplace vers la droite pour rester dans la zone
        if (getX() < minX) {
            setX(getX() + getV() + 0.5);
            return;
        }
        // Si trop à droite, on déplace vers la gauche
        else if (getX() > maxX) {
            setX(getX() + getV() - 0.5);
            return;
        }

        // Coordonnées du NPC dans la grille (en cases)
        int npcX = (int) (getX() / tailleTuile);
        int npcY = (int) (getY() / tailleTuile);

        // Coordonnées du joueur dans la grille (en cases)
        int joueurX = (int) (joueur.getX() / tailleTuile);
        int joueurY = (int) (joueur.getY() / tailleTuile);

        // Largeur et hauteur du terrain
        int largeur = env.getWidth();
        int hauteur = env.getHeight();

        // Tableau pour stocker la distance minimum depuis le joueur vers chaque case (-1 = non visité)
        int[][] distance = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                distance[i][j] = -1; // Initialisation à -1
            }
        }

        // File pour les cases à visiter pendant le BFS
        java.util.Queue<int[]> file = new java.util.LinkedList<>();
        file.add(new int[]{joueurX, joueurY}); // On part du joueur
        distance[joueurY][joueurX] = 0;         // Distance 0 au point de départ (joueur)

        // Les 4 directions possibles
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS a partir du player
        while (!file.isEmpty()) {
            int[] actuel = file.poll(); // Case actuelle

            for (int[] dir : directions) {
                int nx = actuel[0] + dir[0]; // Coordonnée X voisine
                int ny = actuel[1] + dir[1]; // Coordonnée Y voisine

                // limite du BFS pourpas sortir de la zone
                if (nx < (co - 50) || nx > (co + 50)) continue;

                // Vérifie que la case voisine rempli condition pour marcher
                if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur && env.isWalkable(nx, ny) && distance[ny][nx] == -1) {
                    distance[ny][nx] = distance[actuel[1]][actuel[0]] + 1; // Met à jour la distance
                    file.add(new int[]{nx, ny}); // Ajoute la case à la file pour apres
                }
            }
        }

        //on cherche la meilleure casepour laquelle se déplacer pour se rapprocher du joueur
        int cibleX = npcX; // Coordonnée X de la case cible (par défaut la case actuelle)
        int cibleY = npcY; // Coordonnée Y de la case cible (par défaut la case actuelle)
        int minDist = distance[npcY][npcX]; // Distance actuelle du NPC au joueur

        for (int[] dir : directions) {
            int nx = npcX + dir[0];
            int ny = npcY + dir[1];

            // On choisit la case voisine avec la plus petite distance (plus proche du joueur)
            if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur &&
                    distance[ny][nx] != -1 && distance[ny][nx] < minDist) {
                cibleX = nx;
                cibleY = ny;
                minDist = distance[ny][nx];
            }
        }

        // Calcul de la position exacte (en pixels) de la case cible
        double px = cibleX * tailleTuile + tailleTuile / 2.0;
        double py = cibleY * tailleTuile + tailleTuile / 2.0;

        // Calcul du vecteur déplacement du NPC vers cette case
        double dx = px - getX();
        double dy = py - getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        // Si la distance est significative, on avance vers la case cible à la vitesse du NPC
        if (dist > 0.5) {
            double v = getV(); // Vitesse du NPC
            setX(getX() + (dx / dist) * v);
            setY(getY() + (dy / dist) * v);
        }
    }

    @Override
    public void agit() {
    }
}
