package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob.Mobs;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void Stratégie(Environnement env, double mobX,double mobY){
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

    int mobX = (int) (getX() / Param.scale);
    int mobY = (int) (getY() / Param.scale);
    int cibleX, cibleY;

        if (distJoueur < porteePoursuite * Param.scale) {
        int joueurX = (int) (joueur.getX() / Param.scale);
        int joueurY = (int) (joueur.getY() / Param.scale);
        int[] cible = calculerCaseCibleVers(joueurX, joueurY, mobX, mobY);
        cibleX = cible[0];
        cibleY = cible[1];
    } else if (distJoueur > porteeRetour * Param.scale) {
        int initX = (int) (posInitX / Param.scale);
        int initY = (int) (posInitY / Param.scale);
        if (mobX == initX && mobY == initY) return;
        int[] cible = calculerCaseCibleVers(initX, initY, mobX, mobY);
        cibleX = cible[0];
        cibleY = cible[1];
    } else {
        return;
    }

    double px = cibleX * Param.scale + Param.scale / 2.0;
    double py = cibleY * Param.scale + Param.scale / 2.0;

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
        int blockX = (int) (newX / Param.scale);
        int blockY = (int) (getY() / Param.scale);

        if (env.getListB().getBlock(env.getMap1()[blockY][blockX]).isTraversable()) {
            setX(newX);
        } else {
            // Ajuste la position hors du mur si coincé dedans
            if (!env.getListB().getBlock(env.getMap1()[blockY][(int)(getX() / Param.scale)]).isTraversable()) {
                // Si coincé dans le mur, recule juste à la limite
                double edgeX = blockX * Param.scale + (stepX > 0 ? 0 : Param.scale);
                setX(edgeX);
            }
        }

        // -- Test déplacement Y --
        double newY = getY() + stepY;
        blockX = (int) (getX() / Param.scale);
        blockY = (int) (newY / Param.scale);

        if (env.getListB().getBlock(env.getMap1()[blockY][blockX]).isTraversable()) {
            setY(newY);
        } else {
            // Ajuste la position hors du mur si coincé dedans verticalement
            if (!env.getListB().getBlock(env.getMap1()[(int)(getY() / Param.scale)][blockX]).isTraversable()) {
                double edgeY = blockY * Param.scale + (stepY > 0 ? 0 : Param.scale);
                setY(edgeY);
            }
        }
    }
}

private int[] calculerCaseCibleVers(Environnement env,int cibleX, int cibleY, int mobX, int mobY) {
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
