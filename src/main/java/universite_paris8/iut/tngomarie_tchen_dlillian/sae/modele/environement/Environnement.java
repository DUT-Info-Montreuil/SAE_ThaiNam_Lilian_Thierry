package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Environnement {

	private int width,height;
	public ArrayList<Entity> entities;
	public int[][] map1 =new int[4][4];
	public File file=new File("environnement.txt");
	//bonjour


	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.entities=new ArrayList<Entity>();
		map1= new int[][]{{0,0,0,0},
						  {1,1,1,1},
						  {2,2,2,2},
						  {3,3,3,3}};
	}
	public void addentities(Entity e){
		this.entities.add(e);
	}
	public ArrayList<Entity> getEntities(){return this.entities;}
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
