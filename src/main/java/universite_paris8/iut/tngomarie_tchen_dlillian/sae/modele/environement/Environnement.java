package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement;

import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity.Entity;

import java.util.ArrayList;
import java.io.File;
public class Environnement {

	private int width,height;
	public ArrayList<Entity> entities;
	public int[][] map1 =new int[256][64];
	public File file=new File("environnement.txt");


	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.entities=new ArrayList<Entity>();
	}
	public void addentities(Entity e){
		this.entities.add(e);
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public boolean dansTerrain(double x, double y){
		return (0 <= x && x<this.width && 0<=y && y< this.height);
	}

}
