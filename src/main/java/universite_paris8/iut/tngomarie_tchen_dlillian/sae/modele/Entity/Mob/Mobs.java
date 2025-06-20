package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Mobs extends Entity {
    private double co;
    private int degat;
    private double posInitX;
    private double posInitY;

    public Mobs(double x, double y, double v, Environnement env, int maxPv, int degat) {
        super(x, y, v, env, maxPv);
        this.posInitX = x;
        this.posInitY = y;
        this.degat = degat;
    }


    @Override
    public void agit(double sourisX, double sourisY) {
        for (Entity mob : this.env.getEntities()) {
            if (mob instanceof Player) {
                if (mob.getX() - this.getX() < 10 && mob.getY() - this.getY() < 5 && mob.getY() - this.getY() < -5 || mob.getX() - this.getX() < -10 && mob.getY() - this.getY() < 5 && mob.getY() - this.getY() < -5) {
                    mob.decrementerPv(this.degat);
                }
            }
        }
    }

//    @Override
//    public void seDeplace() {
//        gravité();  // Applique la gravité sur le Mob
//        colision(); // Gère les collisions avec l'environnement
//        setY(getY() + getGravite()); // Met à jour la position verticale selon la gravité actuelle
//
//        Player joueur = null;
//
//        // Cherche le joueur parmi toutes les entités
//        for (Entity e : env.getEntities()) {
//            if (e instanceof Player) {
//                joueur = (Player) e;
//                break;
//            }
//        }
//
//        if (joueur == null) return; // Pas de joueur trouvé → ne rien faire
//
//        int tailleTuile = 32; // Taille d'un bloc
//
//        // Limite horizontale de la zone de déplacement (en pixels)
//        double minX = (co - 30) * tailleTuile;
//        double maxX = (co + 30) * tailleTuile;
//
//        // Si le joueur est à portée horizontale
//        if (Math.abs(getX() - joueur.getX()) < 15 * tailleTuile) {
//            // Coordonnées du mob dans la grille
//            int mobX = (int) (getX() / tailleTuile);
//            int mobY = (int) (getY() / tailleTuile);
//
//            // Coordonnées du joueur dans la grille
//            int joueurX = (int) (joueur.getX() / tailleTuile);
//            int joueurY = (int) (joueur.getY() / tailleTuile);
//
//            int largeur = env.getWidth();
//            int hauteur = env.getHeight();
//
//            int[][] distance = new int[hauteur][largeur];
//            for (int i = 0; i < hauteur; i++) {
//                for (int j = 0; j < largeur; j++) {
//                    distance[i][j] = -1;
//                }
//            }
//
//            java.util.Queue<int[]> file = new java.util.LinkedList<>();
//            file.add(new int[]{joueurX, joueurY});
//            distance[joueurY][joueurX] = 0;
//
//            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//
//            while (!file.isEmpty()) {
//                int[] actuel = file.poll();
//
//                for (int[] dir : directions) {
//                    int nx = actuel[0] + dir[0];
//                    int ny = actuel[1] + dir[1];
//
//                    if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur &&
//                            env.isWalkable(nx, ny) && distance[ny][nx] == -1) {
//                        distance[ny][nx] = distance[actuel[1]][actuel[0]] + 1;
//                        file.add(new int[]{nx, ny});
//                    }
//                }
//            }
//
//            int cibleX = mobX;
//            int cibleY = mobY;
//            int minDist = distance[mobY][mobX];
//
//            for (int[] dir : directions) {
//                int nx = mobX + dir[0];
//                int ny = mobY + dir[1];
//
//                if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur &&
//                        distance[ny][nx] != -1 && distance[ny][nx] < minDist) {
//                    cibleX = nx;
//                    cibleY = ny;
//                    minDist = distance[ny][nx];
//                }
//            }
//
//            double px = cibleX * tailleTuile + tailleTuile / 2.0;
//            double py = cibleY * tailleTuile + tailleTuile / 2.0;
//
//            double dx = px - getX();
//            double dy = py - getY();
//            double dist = Math.sqrt(dx * dx + dy * dy);
//
//            if (dist > 0.5) {
//                double v = getV();
//                setX(getX() + (dx / dist) * v);
//                setY(getY() + (dy / dist) * v);
//            }
//        }
//    }
private int frameCounter = 0;
    private final int frameInterval = 10;
    private final int tailleTuile = 32;
    private final int porteePoursuite = 15;  // en tuiles
    private final int porteeRetour = 25;     // en tuiles

    @Override
    public void seDeplace() {
        gravité();
        colision();
        setY(getY() + getGravite());

        Player joueur = null;
        for (Entity e : env.getEntities()) {
            if (e instanceof Player) {
                joueur = (Player) e;
                break;
            }
        }
        if (joueur == null) return;

        double distJoueur = Math.abs(getX() - joueur.getX());

        frameCounter++;
        if (frameCounter < frameInterval) {
            return;  // Calculer chemin toutes les frameInterval frames
        }
        frameCounter = 0;

        int mobX = (int) (getX() / tailleTuile);
        int mobY = (int) (getY() / tailleTuile);
        int cibleX, cibleY;

        if (distJoueur < porteePoursuite * tailleTuile) {
            int joueurX = (int) (joueur.getX() / tailleTuile);
            int joueurY = (int) (joueur.getY() / tailleTuile);
            int[] cible = calculerCaseCibleVers(joueurX, joueurY, mobX, mobY);
            cibleX = cible[0];
            cibleY = cible[1];
        } else if (distJoueur > porteeRetour * tailleTuile) {
            int initX = (int) (posInitX / tailleTuile);
            int initY = (int) (posInitY / tailleTuile);
            if (mobX == initX && mobY == initY) return;
            int[] cible = calculerCaseCibleVers(initX, initY, mobX, mobY);
            cibleX = cible[0];
            cibleY = cible[1];
        } else {
            return;
        }

        double px = cibleX * tailleTuile + tailleTuile / 2.0;
        double py = cibleY * tailleTuile + tailleTuile / 2.0;

        double dx = px - getX();
        double dy = py - getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist > 0.5) {
            double v = getV() * 2;

            // Calcul du pas
            double stepX = (dx / dist) * v;
            double stepY = (dy / dist) * v;

            // -- Test déplacement X --
            double newX = getX() + stepX;
            int blockX = (int) (newX / tailleTuile);
            int blockY = (int) (getY() / tailleTuile);

            if (env.getListB().getBlock(env.getMap1()[blockY][blockX]).isTraversable()) {
                setX(newX);
            } else {
                // Ajuste la position hors du mur si coincé dedans
                if (!env.getListB().getBlock(env.getMap1()[blockY][(int)(getX() / tailleTuile)]).isTraversable()) {
                    // Si coincé dans le mur, recule juste à la limite
                    double edgeX = blockX * tailleTuile + (stepX > 0 ? 0 : tailleTuile);
                    setX(edgeX);
                }
            }

            // -- Test déplacement Y --
            double newY = getY() + stepY;
            blockX = (int) (getX() / tailleTuile);
            blockY = (int) (newY / tailleTuile);

            if (env.getListB().getBlock(env.getMap1()[blockY][blockX]).isTraversable()) {
                setY(newY);
            } else {
                // Ajuste la position hors du mur si coincé dedans verticalement
                if (!env.getListB().getBlock(env.getMap1()[(int)(getY() / tailleTuile)][blockX]).isTraversable()) {
                    double edgeY = blockY * tailleTuile + (stepY > 0 ? 0 : tailleTuile);
                    setY(edgeY);
                }
            }
        }
    }

    private int[] calculerCaseCibleVers(int cibleX, int cibleY, int mobX, int mobY) {
        int largeur = env.getWidth();
        int hauteur = env.getHeight();
        int[][] distance = new int[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            Arrays.fill(distance[i], -1);
        }

        Queue<int[]> file = new LinkedList<>();
        file.add(new int[]{cibleX, cibleY});
        distance[cibleY][cibleX] = 0;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!file.isEmpty()) {
            int[] actuel = file.poll();
            for (int[] dir : directions) {
                int nx = actuel[0] + dir[0];
                int ny = actuel[1] + dir[1];
                if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur &&
                        env.isWalkable(nx, ny) && distance[ny][nx] == -1) {
                    distance[ny][nx] = distance[actuel[1]][actuel[0]] + 1;
                    file.add(new int[]{nx, ny});
                }
            }
        }

        int cibleCaseX = mobX;
        int cibleCaseY = mobY;
        int minDist = distance[mobY][mobX];

        for (int[] dir : directions) {
            int nx = mobX + dir[0];
            int ny = mobY + dir[1];
            if (nx >= 0 && ny >= 0 && nx < largeur && ny < hauteur &&
                    distance[ny][nx] != -1 && distance[ny][nx] < minDist) {
                cibleCaseX = nx;
                cibleCaseY = ny;
                minDist = distance[ny][nx];
            }
        }

        return new int[]{cibleCaseX, cibleCaseY};
    }

}