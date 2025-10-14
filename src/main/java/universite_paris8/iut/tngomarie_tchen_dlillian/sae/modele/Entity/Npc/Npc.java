package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Npc;


import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementInterface;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementBFS;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.StrategieDeDeplacementDirect;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

public class Npc extends Entity {
    private int forwardbackward=1;
    public double co;
    
    // Stratégies de déplacement disponibles
    private StrategieDeDeplacementInterface strategieBFS;
    private StrategieDeDeplacementInterface strategieDirect;
    private StrategieDeDeplacementInterface strategieActuelle;
    
    public Npc(double x, double y, int v, int pv) {
        super(x, y, v, pv);
        this.co = x;
        
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

        if (joueur != null) {
            // Utiliser la taille de tuile depuis Param
            int tailleTuile = Param.scale;
            
            // Limite horizontale de la zone de déplacement (en pixels)
            double minX = (co - 20) * tailleTuile;
            double maxX = (co + 20) * tailleTuile;

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
            
            // Choisir la stratégie appropriée
            choisirStrategie(joueur);
            
            // Appliquer la stratégie si elle peut être utilisée
            if (strategieActuelle.peutEtreAppliquee(this, joueur, env)) {
                double[] mouvement = strategieActuelle.calculerMouvement(this, joueur, env, tailleTuile);
                
                // Appliquer le mouvement en respectant les limites de zone
                double newX = getX() + mouvement[0];
                double newY = getY() + mouvement[1];
                
                // Vérifier que le nouvel X reste dans la zone autorisée
                if (newX >= minX && newX <= maxX) {
                    setX(newX);
                }
                setY(newY);
            }
        }
    }
    
    /**
     * Choisit la stratégie de déplacement appropriée selon le contexte
     * Les NPCs utilisent principalement BFS pour un mouvement intelligent
     */
    private void choisirStrategie(Player joueur) {
        double distance = Math.sqrt(
            Math.pow(getX() - joueur.getX(), 2) + 
            Math.pow(getY() - joueur.getY(), 2)
        );
        
        // Les NPCs utilisent toujours BFS pour un mouvement précis
        // (contrairement aux mobs qui peuvent utiliser le mouvement direct)
        strategieActuelle = strategieBFS;
    }

    @Override
    public ImageView getimage() {
        return null;
    }

    @Override
    public void agit(double souriX, double souriY) {
    }
}
