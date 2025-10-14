package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementInterface;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementBFS;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementDirect;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Mobs extends Entity {
    private double co;
    private int degat;
    private double posInitX;
    private double posInitY;
    
    // Stratégies de déplacement disponibles
    private StrategieDeDeplacementInterface strategieBFS;
    private StrategieDeDeplacementInterface strategieDirect;
    private StrategieDeDeplacementInterface strategieActuelle;

    public Mobs(double x, double y, double v, int maxPv, int degat) {
        super(x, y, v, maxPv);
        this.posInitX = x;
        this.posInitY = y;
        this.degat = degat;
        
        // Initialiser les stratégies
        this.strategieBFS = new StrategieDeDeplacementBFS();
        this.strategieDirect = new StrategieDeDeplacementDirect();
        this.strategieActuelle = strategieBFS; // Par défaut, utiliser BFS
    }
    
    /**
     * Change la stratégie de déplacement
     */
    public void setStrategieDeplacement(StrategieDeDeplacementInterface strategie) {
        this.strategieActuelle = strategie;
    }
    
    /**
     * Obtient la stratégie de déplacement actuelle
     */
    public StrategieDeDeplacementInterface getStrategieDeplacement() {
        return this.strategieActuelle;
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
        
        // Trouver le joueur
        Player joueur = null;
        for (Entity e : env.getEntities()) {
            if (e instanceof Player) {
                joueur = (Player) e;
                break;
            }
        }
        
        if (joueur != null) {
            // Choisir la stratégie appropriée
            choisirStrategie(joueur);
            
            // Appliquer la stratégie si elle peut être utilisée
            if (strategieActuelle.peutEtreAppliquee(this, joueur, env)) {
                double[] mouvement = strategieActuelle.calculerMouvement(this, joueur, env, tailleTuile);
                
                // Appliquer le mouvement
                setX(getX() + mouvement[0]);
                setY(getY() + mouvement[1]);
            }
        }
    }
    
    /**
     * Choisit la stratégie de déplacement appropriée selon le contexte
     */
    protected void choisirStrategie(Player joueur) {
        double distance = Math.sqrt(
            Math.pow(getX() - joueur.getX(), 2) + 
            Math.pow(getY() - joueur.getY(), 2)
        );
        
        // Si le joueur est loin, utiliser la stratégie directe (plus rapide)
        // Si le joueur est proche, utiliser BFS pour éviter les obstacles
        if (distance > 200) {
            strategieActuelle = strategieDirect;
        } else {
            strategieActuelle = strategieBFS;
        }
    }
}