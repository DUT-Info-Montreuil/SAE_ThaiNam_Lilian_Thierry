package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient;

import javafx.scene.image.ImageView;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Player;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Inventaire;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Objet;

public class Ingredient extends Objet {
    private int nbObjet;

    public Ingredient(int id,int nb){
        super(id,nb);
        this.nbObjet = nb; // Synchroniser nbObjet avec nb
    }

    @Override
    public ImageView getimage() {
        return new ImageView("default.png");
    }

    public void ajouterIngredient(){
        this.nbObjet++;
        this.nb++; // Synchroniser avec le champ parent
    }

    public void decrementIngredient(int quantite){
        this.nbObjet = Math.max(0, this.nbObjet - quantite);
        this.nb = Math.max(0, this.nb - quantite); // Synchroniser avec le champ parent
        
        // Si l'item n'a plus de quantité, le retirer de l'inventaire
        if (this.nb <= 0) {
            Inventaire.getInstance().supprimerObjet(this.getIdObjet(), this.nb + quantite); // Supprimer complètement
        } else {
            // Forcer la mise à jour de l'interface pour refléter le nouveau nombre
            Inventaire.getInstance().forceUpdate();
        }
    }

    public int getNbObjet(){
        return this.nbObjet;
    }
    
    @Override
    public int getNb() {
        // S'assurer que les deux champs sont synchronisés
        return Math.max(super.getNb(), this.nbObjet);
    }

    public void agit(Player player,double SourisX, double SourisY){for(Entity mob : player.getEnv().getEntities()){
        if(mob.getX() - player.getX() < 20 && mob.getY() - player.getX() <20){
            mob.decrementerPv(1);
        }
    }}
}
