package fr.datascale.rami;


import java.util.LinkedList;



public class Trajectory {
	private LinkedList<Point> list;
	private String name;
	 private int cluster_number = 0;
	
	
	public Trajectory(){
		list = new LinkedList<Point>();
	}
	
	public Trajectory(LinkedList<Point> l){
		this.list = l;
	}

	/**
	 * Ajouter un point à la trajectoire
	 * @param p
	 */
	public void addP(Point p){
		list.add(p);
	}
	
	/**
	 * Récupérer un point de la trajectoire
	 * @param i index
	 */
	public Point getP(int i){
		return list.get(i);
	}
	
	/**
	 * 
	 * @return la taille de la trajectoire
	 */
	public int size(){
		return list.size();
	}

	
	/**
	 * @return Nom de la trajectoire
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Modifier le nom de la trajectoire
	 * @param name Nom de la trajectoire
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return Retourne le premier point
	 */
	public Point getFirstP(){
		return list.getFirst();
	}
	
	/**
	 * 
	 * @return Dernier point de la trajectoire
	 */
	public Point getLastP() {
		return list.getLast();
	}
	
	/**
	 * Nom et nombre de points de la trajectoire
	 */
	public void printDetails(){
		System.out.println("Name: " + name + "\nPoints: " + size());
	}

	/**
	 * 
	 * @return Valeur X minimale de la trajectoire
	 */
	public double getMinX(){
		double[] x_data = new double[size()];
		for(int i = 0; i< size(); i++){
			x_data[i] = getP(i).getX();
		}
		double x_min= Double.POSITIVE_INFINITY;
		for(int i=0; i< x_data.length; i++){
			if(x_data[i] < x_min){
				x_min = x_data[i];
			}
		}
		return x_min;
	}
	
	/**
	 * 
	 * @return Valeur Y minimale de la trajectoire
	 */
	public double getMinY(){
		double[] y_data = new double[size()];
		for(int i = 0; i< size(); i++){
			y_data[i] = getP(i).getY();
		}
		double y_min= Double.POSITIVE_INFINITY;
		for(int i=0; i< y_data.length; i++){
			if(y_data[i] < y_min){
				y_min = y_data[i];
			}
		}
		return y_min;
	}
	/**
	 * 
	 * @return Valeur X maximale de la trajectoire
	 */
	public double getMaxX(){
		double[] x_data = new double[size()];
		for(int i = 0; i< size(); i++){
			x_data[i] = getP(i).getX();
		}
		double x_max= Double.NEGATIVE_INFINITY;
		for(int i=0; i< x_data.length; i++){
			if(x_data[i] > x_max){
				x_max = x_data[i];
			}
		}
		return x_max;
	}
	/**
	 * 
	 * @return Valeur Y maximale de la trajectoire
	 */
	public double getMaxY(){
		double[] y_data = new double[size()];
		for(int i = 0; i< size(); i++){
			y_data[i] = getP(i).getY();
		}
		double y_max= Double.NEGATIVE_INFINITY;
		for(int i=0; i< y_data.length; i++){
			if(y_data[i] > y_max){
				y_max = y_data[i];
			}
		}
		return y_max;
	}

	public LinkedList<Point> getList() {
		return list;
	}

	public void setList(LinkedList<Point> list) {
		this.list = list;
	}

	public int getCluster_number() {
		return cluster_number;
	}

	public void setCluster_number(int cluster_number) {
		this.cluster_number = cluster_number;
	}
	
	 public String toString() {
	    	
		 String t = "";
		 int i = 0;
		 for(Point p : list)
		 {
			 t+=i+":("+p.getX()+","+p.getY()+") ";
			 i++;
		 }
		 return t;
		 
	    }

	
}
