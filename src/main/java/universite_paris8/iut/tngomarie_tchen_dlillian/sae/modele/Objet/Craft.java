package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Bloc.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Ingredient.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Epee.*;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.Outil.Pioche.*;

public class Craft {
    private ListRecipe listRecipe ;
    private Inventaire inventaire;

    public Craft(Inventaire inventaire){
        this.listRecipe= new ListRecipe();
        this.inventaire = inventaire;
    }


    public void crafting(int clef){
        System.out.println("Tentative de craft avec clef: " + clef);
        boolean peutCrafter = verif(clef);
        System.out.println("Peut crafter: " + peutCrafter);
        
        if(peutCrafter){
            // Remove ingredients from inventory
            int[][] listeDeRecette = this.listRecipe.getList(clef).getRecette();
            System.out.println("Suppression des ingrédients...");
            for (int i = 0; i < listeDeRecette.length; i++) {
                if (listeDeRecette[i] == null || listeDeRecette[i].length < 2) {
                    System.err.println("Erreur: Données d'ingrédient invalides à l'index " + i);
                    continue;
                }
                
                int idObjet = listeDeRecette[i][0];
                int quantiteRequise = listeDeRecette[i][1];
                System.out.println("Suppression objet ID " + idObjet + " quantité " + quantiteRequise);
                this.inventaire.supprimerObjet(idObjet, quantiteRequise);
            }

            // Add crafted items to inventory
            int[][] resultatRecette = this.listRecipe.getList(clef).getResulat();
            System.out.println("Ajout des objets craftés...");
            for (int i = 0; i < resultatRecette.length; i++) {
                if (resultatRecette[i] == null || resultatRecette[i].length < 2) {
                    System.err.println("Erreur: Données de résultat invalides à l'index " + i);
                    continue;
                }
                
                int idObjetResultat = resultatRecette[i][0];
                int quantiteResultat = resultatRecette[i][1];
                System.out.println("Ajout objet ID " + idObjetResultat + " quantité " + quantiteResultat);
                
                // Créer un nouvel objet avec la bonne quantité en utilisant l'ID directement
                Objet nouvelObjet = creerObjetParId(idObjetResultat, quantiteResultat);
                if (nouvelObjet != null) {
                    this.inventaire.ajoutObjet(nouvelObjet);
                    System.out.println("Objet ajouté avec succès: " + nouvelObjet.getClass().getSimpleName() + " x" + quantiteResultat);
                } else {
                    System.err.println("Erreur: Impossible de créer l'objet résultat ID " + idObjetResultat);
                }
            }
            System.out.println("Crafting terminé avec succès!");
        } else {
            System.out.println("Crafting impossible - ingrédients insuffisants.");
        }
    }

    private boolean verif(int i) {
        int valid = 0;
        int[][] listeDeRecette = listRecipe.getList(i).getRecette();
        
        if (listeDeRecette == null) {
            System.err.println("Erreur: Recette null pour l'ID " + i);
            return false;
        }
        
        int nbingredient = listeDeRecette.length;

        for (int j = 0; j < nbingredient; j++) {
            if (listeDeRecette[j] == null || listeDeRecette[j].length < 2) {
                System.err.println("Erreur: Données d'ingrédient invalides à l'index " + j + " pour la recette " + i);
                continue; // Passer cet ingrédient invalide
            }
            
            int idRequis = listeDeRecette[j][0];
            int quantiteRequise = listeDeRecette[j][1];
            
            for (Objet obj : this.inventaire.getInventaire()) {
                if ((idRequis == obj.getIdObjet())
                        && (obj.getNb() >= quantiteRequise)) {
                    valid++;
                    break;
                }
            }
        }

        System.out.println("Vérification craft - Ingrédients validés: " + valid + "/" + nbingredient);
        return valid == nbingredient;
    }
    
    /**
     * Crée un nouvel objet basé sur un ID avec la quantité spécifiée
     * @param idObjet L'ID de l'objet à créer
     * @param quantite La quantité désirée
     * @return Un nouvel objet avec la quantité spécifiée
     */
    private Objet creerObjetParId(int idObjet, int quantite) {
        switch (idObjet) {
            case 0: return new Bois(quantite);
            case 1: return new Pierre(quantite);
            case 2: return new Fer(quantite);
            case 3: return new Or(quantite);
            case 4: return new Charbon(quantite);
            case 7: return new Plante(quantite);
            case 8: return new Arc();
            case 9: return new FlecheObjet(quantite);
            case 10: return new EpeeBois();
            case 11: return new EpeeFer();
            case 12: return new EpeeMeteorite();
            case 13: return new EpeeOr();
            case 14: return new PiocheEnBois();
            case 28: return new Baton(quantite);
            case 29: return new Tissu(quantite);
            case 32: return new Fils(quantite);
            default:
                System.err.println("ID d'objet non reconnu pour la création: " + idObjet);
                return null;
        }
    }
    
    /**
     * Crée un nouvel objet basé sur un objet modèle avec la quantité spécifiée
     * @param objetModele L'objet modèle à copier
     * @param quantite La quantité désirée
     * @return Un nouvel objet avec la quantité spécifiée
     */
    private Objet creerNouvelObjetAvecQuantite(Objet objetModele, int quantite) {
        try {
            // Utiliser le nom de classe pour créer une nouvelle instance
            String className = objetModele.getClass().getName();
            Class<?> clazz = Class.forName(className);
            
            // Essayer de créer avec un constructeur qui prend un int (quantité)
            try {
                return (Objet) clazz.getConstructor(int.class).newInstance(quantite);
            } catch (Exception e1) {
                // Si ça ne marche pas, essayer le constructeur par défaut
                try {
                    Objet nouvelObjet = (Objet) clazz.getConstructor().newInstance();
                    // Ajuster la quantité après création (nb est un champ public)
                    nouvelObjet.nb = quantite;
                    return nouvelObjet;
                } catch (Exception e2) {
                    System.err.println("Erreur lors de la création de l'objet " + className + ": " + e2.getMessage());
                    return null;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trouvée: " + e.getMessage());
            return null;
        }
    }
}
