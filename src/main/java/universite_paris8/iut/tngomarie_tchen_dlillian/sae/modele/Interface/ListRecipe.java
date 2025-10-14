package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Interface;

import java.util.HashMap;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Objet.ListObjet;

public class ListRecipe {
    HashMap<Integer, Recipe> list = new HashMap<Integer, Recipe>();
    public ListRecipe(){
        créeRecipe();
    }

    public Recipe getList(int i) {
        return list.get(i);
    }

    private void créeRecipe() {
    //Arc (3 batons + 3 fils = 1 arc)
        int[][] n1 = new int[][] {{28,32},{3,3}};
        int[][] o1 = new int[][] {{8},{1}};
        list.put(0, new Recipe(n1, o1));
    //Fleche (2 batons + 1 pierre = 3 fleches)
        int[][] n2 = new int[][] {{28,1},{2,1}};
        int[][] o2 = new int[][] {{9},{3}};
        list.put(1, new Recipe(n2, o2));
    //Epee Bois (1 baton + 2 bois = 1 épée bois)
        int[][] n3 = new int[][] {{28,0},{1,2}};
        int[][] o3 = new int[][] {{10},{1}};
        list.put(2, new Recipe(n3, o3));

    //Pioche Bois (2 batons + 3 bois = 1 pioche bois)
        int[][] n4 = new int[][] {{28,0},{2,3}};
        int[][] o4 = new int[][] {{14},{1}};
        list.put(3, new Recipe(n4, o4));
    //Hache Bois (2 batons + 3 bois = 1 hache bois) - ID 18 n'existe pas dans ListObjet
        // int[][] n5 = new int[][] {{28,0},{2,3}};
        // int[][] o5 = new int[][] {{18},{1}};
        // list.put(4, new Recipe(n5, o5));
    //Baton (1 bois = 4 batons)
        int[][] n6 = new int[][] {{0,1}}; // {ID_bois, quantité_requise}
        int[][] o6 = new int[][]{{28,4}};  // {ID_baton, quantité_produite}
        list.put(5, new Recipe(n6, o6));
    //Tissu (2 fils = 1 tissu)
        int[][] n7 = new int[][] {{32,2}}; // {ID_fils, quantité_requise}
        int[][] o7 = new int[][]{{29,1}};  // {ID_tissu, quantité_produite}
        list.put(6,new Recipe(n7,o7));
    }

    public HashMap<Integer, Recipe> getList() {
        return list;
    }
    
    /**
     * Méthode de diagnostic pour vérifier l'état des recettes
     */
    public void diagnostiquerRecettes() {
        System.out.println("=== Diagnostic des recettes ===");
        System.out.println("Nombre total de recettes: " + list.size());
        
        for (Integer key : list.keySet()) {
            Recipe recipe = list.get(key);
            if (recipe == null) {
                System.err.println("ERREUR: Recette null à la clé " + key);
            } else {
                System.out.println("Clé " + key + ": Recette OK");
                
                // Vérifier les données de la recette
                if (recipe.getRecette() == null) {
                    System.err.println("  - ERREUR: getRecette() retourne null");
                } else {
                    // Vérifier la structure des ingrédients
                    int[][] ingredients = recipe.getRecette();
                    for (int i = 0; i < ingredients.length; i++) {
                        if (ingredients[i] == null) {
                            System.err.println("  - ERREUR: Ingrédient " + i + " est null");
                        } else if (ingredients[i].length < 2) {
                            System.err.println("  - ERREUR: Ingrédient " + i + " n'a que " + ingredients[i].length + " élément(s) au lieu de 2 (ID, quantité)");
                        }
                    }
                }
                
                if (recipe.getResulat() == null) {
                    System.err.println("  - ERREUR: getResulat() retourne null");
                } else {
                    // Vérifier la structure des résultats
                    int[][] resultats = recipe.getResulat();
                    for (int i = 0; i < resultats.length; i++) {
                        if (resultats[i] == null) {
                            System.err.println("  - ERREUR: Résultat " + i + " est null");
                        } else if (resultats[i].length < 2) {
                            System.err.println("  - ERREUR: Résultat " + i + " n'a que " + resultats[i].length + " élément(s) au lieu de 2 (ID, quantité)");
                        }
                    }
                }
            }
        }
        System.out.println("=== Fin du diagnostic ===");
    }
    
    /**
     * Vérifie que tous les objets référencés dans les recettes existent dans ListObjet
     */
    public void validerObjetsRecettes(ListObjet listObjet) {
        System.out.println("=== Validation des objets dans les recettes ===");
        
        for (Integer key : list.keySet()) {
            Recipe recipe = list.get(key);
            if (recipe == null) continue;
            
            System.out.println("Vérification recette " + key + ":");
            
            // Vérifier les ingrédients
            int[][] ingredients = recipe.getRecette();
            if (ingredients != null) {
                for (int i = 0; i < ingredients.length; i++) {
                    if (ingredients[i] != null && ingredients[i].length >= 2) {
                        int idIngredient = ingredients[i][0];
                        if (listObjet.getItem(idIngredient) == null) {
                            System.err.println("  - ERREUR: Ingrédient ID " + idIngredient + " introuvable dans ListObjet");
                        } else {
                            System.out.println("  - Ingrédient ID " + idIngredient + " OK (" + listObjet.getItem(idIngredient).getClass().getSimpleName() + ")");
                        }
                    }
                }
            }
            
            // Vérifier le résultat
            int[][] resultat = recipe.getResulat();
            if (resultat != null && resultat.length > 0 && resultat[0] != null && resultat[0].length > 0) {
                int idResultat = resultat[0][0];
                if (listObjet.getItem(idResultat) == null) {
                    System.err.println("  - ERREUR: Résultat ID " + idResultat + " introuvable dans ListObjet");
                } else {
                    System.out.println("  - Résultat ID " + idResultat + " OK (" + listObjet.getItem(idResultat).getClass().getSimpleName() + ")");
                }
            }
        }
        
        System.out.println("=== Fin de la validation ===");
    }
    
    /**
     * Teste spécifiquement le format des données de recettes
     */
    public void testerFormatRecettes() {
        System.out.println("=== Test du format des recettes ===");
        
        for (Integer key : list.keySet()) {
            Recipe recipe = list.get(key);
            if (recipe == null) {
                System.err.println("Recette " + key + " est null !");
                continue;
            }
            
            System.out.println("Test recette " + key + ":");
            
            // Test des ingrédients
            int[][] ingredients = recipe.getRecette();
            if (ingredients != null) {
                for (int i = 0; i < ingredients.length; i++) {
                    if (ingredients[i] == null) {
                        System.err.println("  Ingrédient " + i + " est null !");
                    } else {
                        System.out.println("  Ingrédient " + i + ": [" + 
                            java.util.Arrays.toString(ingredients[i]) + "] (taille: " + ingredients[i].length + ")");
                        if (ingredients[i].length < 2) {
                            System.err.println("    ERREUR: Taille insuffisante pour [ID, quantité] !");
                        }
                    }
                }
            }
            
            // Test des résultats
            int[][] resultats = recipe.getResulat();
            if (resultats != null) {
                for (int i = 0; i < resultats.length; i++) {
                    if (resultats[i] == null) {
                        System.err.println("  Résultat " + i + " est null !");
                    } else {
                        System.out.println("  Résultat " + i + ": [" + 
                            java.util.Arrays.toString(resultats[i]) + "] (taille: " + resultats[i].length + ")");
                        if (resultats[i].length < 2) {
                            System.err.println("    ERREUR: Taille insuffisante pour [ID, quantité] !");
                        }
                    }
                }
            }
        }
        
        System.out.println("=== Fin du test de format ===");
    }
}
