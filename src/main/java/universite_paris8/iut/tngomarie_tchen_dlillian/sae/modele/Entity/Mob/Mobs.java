package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Mob;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Strategie.StrategieDeDeplacementInterface;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieAvancee;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;
public abstract class Mobs extends Entity {
    private double co;
    private int degat;
    private double posInitX;
    private double posInitY;
    
    // Stratégie de déplacement unique
    private StrategieDeDeplacementInterface strategie;

    public Mobs(double x, double y, double v, int maxPv, int degat) {
        super(x, y, v, maxPv);
        this.posInitX = x;
        this.posInitY = y;
        this.degat = degat;
        
        // Initialiser avec la stratégie avancée (qui gère automatiquement le choix BFS/Direct)
        this.strategie = new StrategieAvancee();
    }
    
    /**
     * Change la stratégie de déplacement
     */
    public void setStrategieDeplacement(StrategieDeDeplacementInterface strategie) {
        this.strategie = strategie;
    }
    
    /**
     * Obtient la stratégie de déplacement actuelle
     */
    public StrategieDeDeplacementInterface getStrategieDeplacement() {
        return this.strategie;
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
    // Utiliser les constantes de Param pour la taille des tuiles
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
            // Appliquer la stratégie si elle peut être utilisée
            // La StrategieAvancee gère automatiquement le choix BFS/Direct
            if (strategie.peutEtreAppliquee(this, joueur, env)) {
                double[] mouvement = strategie.calculerMouvement(this, joueur, env, Param.scale);
                
                // Appliquer le mouvement
                setX(getX() + mouvement[0]);
                setY(getY() + mouvement[1]);
            }
        }
    }
    
}