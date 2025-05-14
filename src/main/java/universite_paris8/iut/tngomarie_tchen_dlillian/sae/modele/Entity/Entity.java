package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.Entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele.environement.Environnement;

import java.util.Random;


public abstract class Entity {

	private DoubleProperty x,y;
	private int v; // vitesse de deplacement
	protected Environnement env;
	public static int compteur=0;
	private String id;
	private int pv;
	private boolean asaptrite;
	public Entity(double x, double y, int v, Environnement env,int pv) {
		this.pv=pv;
		this.x= new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		this.asaptrite=false;
		compteur++;
	}

	public Entity( int v, Environnement env, int pv) {
		this.pv=pv;
		Random random=new Random();
		int x = random.nextInt(env.getWidth()-1);
		int y=random.nextInt(env.getHeight()-1);
		this.x=new SimpleDoubleProperty(x);
		this.y =new SimpleDoubleProperty(y);
		this.v = v;
		this.env=env;	
		this.id="A"+compteur;
		compteur++;
		//System.out.println("y" + y + "x" +x);
	}	
	public void gotasprite(){this.asaptrite=true;}
	public boolean getasprite(){return this.asaptrite;}
	public  double getX() {
		return x.getValue();
	}
	public DoubleProperty getXProperty() {
		return x;
	}

	public  void setX(double n){
		x.setValue(n);
	}

	public  double getY() {
		return y.getValue();
	}
	public DoubleProperty getYProperty() {
		return y;
	}
	public  void setY(double n){
		y.setValue(n);
	}

	public String getId() {
		return id;
	}

	public void decrementerPv(int n) {
		this.pv-=n;	
	}

	public void incrementerPv(int n) {
		this.pv+=n;	
	}




	public boolean estVivant() {
		return this.pv>0;
	}

	public void meurt(){
		this.pv=0;
	}


	//permet de savoir si une action probabiliste se réalise 
	public static boolean reussitProba(double pourcent){
		double x= Math.random();
		double pp=pourcent/100;
		return (x<=pp);
	}


	public abstract void seDeplace();


    public abstract void agit();

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y + ", id=" + id ;
	}


}
