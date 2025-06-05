package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement;

import javafx.collections.ObservableList;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Environnement {

	private int width,height;
	public ObservableList<Entity> entities;
	public int[][] map1;
	public HashMap<Integer, Block> listeBlock;


	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.entities= new ObservableList<Entity>();
		this.listeBlock=new HashMap<>();
		creerliste();
		try{
			this.map1= chargerMapFichier("src/main/java/universite_paris8/iut/tngomarie_tchen_dlillian/sae/modele/environement/environnement.txt");
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	private void creerliste() {
		listeBlock.put(0,new  Block(true,false,"AIR"));
		listeBlock.put(1,new Block(false,true,"DIRT"));
		listeBlock.put(2,new Block(false,true,"STONE"));
		listeBlock.put(3,new Block(false,true,"GRASS"));
	}
	public Block getBlock(int id) {
		return listeBlock.get(id);
	}

	public void addentities(Entity e){
		this.entities.add(e);
	}
	public ObservableList<Entity> getEntities(){return this.entities;}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public int[][] getMap1(){return map1;}


	public boolean dansTerrain(double x, double y){
		return (0 <= x && x<this.width && 0<=y && y< this.height);
	}

	public int[][] chargerMapFichier(String chemin) throws IOException {
		List<int[]> lignes = new ArrayList<>();
		try (BufferedReader lecture = new BufferedReader(new FileReader(chemin))){
			String ligne;
			while ((ligne = lecture.readLine()) != null){
				ligne = ligne.replaceAll("[\\{\\}]", "").trim();
				if (ligne.isEmpty()) continue;
				String[] valeurs = ligne.split(",");

				int[] ligneEntiers = new int[valeurs.length];

				for (int i = 0; i < valeurs.length; i++) {
					ligneEntiers[i] = Integer.parseInt(valeurs[i].trim());
				}

				lignes.add(ligneEntiers);
			}
		}
		int[][] terrain = new int[lignes.size()][];
		for (int i = 0; i < lignes.size(); i++) {
			terrain[i] = lignes.get(i);
		}
		return terrain;
	}

}
