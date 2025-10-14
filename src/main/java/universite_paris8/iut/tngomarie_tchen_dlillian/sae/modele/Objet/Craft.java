package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface.ListRecipe;

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
                int idObjet = listeDeRecette[i][0];
                int quantiteRequise = listeDeRecette[i][1];
                System.out.println("Suppression objet ID " + idObjet + " quantité " + quantiteRequise);
                this.inventaire.supprimerObjet(idObjet, quantiteRequise);
            }

            // Add crafted items to inventory
            int[][] resultatRecette = this.listRecipe.getList(clef).getResulat();
            System.out.println("Ajout des objets craftés...");
            for (int i = 0; i < resultatRecette.length; i++) {
                int idObjetResultat = resultatRecette[i][0];
                int quantiteResultat = resultatRecette[i][1];
                System.out.println("Ajout objet ID " + idObjetResultat + " quantité " + quantiteResultat);
                
                for (int j = 0; j < quantiteResultat; j++) {
                    this.inventaire.ajoutObjet(this.inventaire.getListObjet().getItem(idObjetResultat));
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
        int nbingredient = listeDeRecette.length;

        for (int j = 0; j < nbingredient; j++) {
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
}
