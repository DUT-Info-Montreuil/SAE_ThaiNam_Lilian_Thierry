package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Param;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block.Block;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Block.ListBlock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Environnement {

	private int width,height;
	private ListBlock list;
	public ObservableList<Entity> entities;
	public int[][] map1;

	private static Environnement uniqueInstance = null;

	public static Environnement getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Environnement();
		}
		return uniqueInstance;
	}

	public Environnement() {
		super();
		this.width = Param.width*Param.scale;
		this.height = Param.height*Param.scale;
		this.entities= FXCollections.observableArrayList();
		this.list = new ListBlock();
		this.entities.addListener(new ListChangeListener() {
			@Override
			public void onChanged(Change change) {

			}
		});
		try{
			this.map1= chargerMapFichier("src/main/java/universite_paris8/iut/tngomarie_tchen_dlillian/sae/modele/environement/environnement.txt");
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void changeBlock(int x, int y, int block) {
		this.map1[x][y]=block;
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
	public ListBlock getListB(){return this.list;}

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
	// AJOUT : pour savoir si une case est marchable (utile au BFS)
	public boolean isWalkable(int x, int y) {
		if (y < 0 || y >= map1.length || x < 0 || x >= map1[0].length) {
			return false;
		}
		int blockId = map1[y][x];
		Block b = this.list.getBlock(blockId);
		return b != null && b.getName().equals("AIR");// ou !b.isSolide(), selon ta logique
	}


}
