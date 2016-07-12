package fr.datascale.rami;

import java.util.ArrayList;
import java.util.Collections;

public class Distance {
	
	private Trajectory t1;
	private Trajectory t2;
	private double[][] dist_matrix;
	private long time;
	
	public Distance(Trajectory t1, Trajectory t2){
		this.t1 = t1;
		this.t2 = t2;
		dist_matrix = new double[t2.size()][t1.size()];
	}

	public double calculate(){
		long start = System.currentTimeMillis();
		double result = 0.0;
		ArrayList<Double> minDistances1 = new ArrayList<Double>();
		ArrayList<Double> minDistances2 = new ArrayList<Double>();
		for(int i = 0; i< dist_matrix.length; i++){
			for(int j = 0; j < dist_matrix[0].length; j++){
				dist_matrix[i][j] = d(t1.getP(j), t2.getP(i));
			}
		}
		// Minimale Distanzen t1
		for(int i = 0; i< dist_matrix.length; i++){
			double min = Double.POSITIVE_INFINITY;
			for(int j = 0; j < dist_matrix[0].length; j++){
				if(dist_matrix[i][j] <= min){
					min = dist_matrix[i][j];
				}
			}
			minDistances1.add(min);
		}
		// Minimale Distanzen t2
		for(int i = 0; i< dist_matrix[0].length; i++){
			double min = Double.POSITIVE_INFINITY;
			for(int j = 0; j < dist_matrix.length; j++){
				if(dist_matrix[j][i] <= min){
					min = dist_matrix[j][i];
				}
			}
			minDistances2.add(min);
		}
		Collections.sort(minDistances1);
		Collections.sort(minDistances2);
		double value1 = minDistances1.get(minDistances1.size()-1);
		double value2 = minDistances2.get(minDistances2.size()-1);
		result = Math.max(value1, value2);
		long end = System.currentTimeMillis();
		time = end-start;
		//System.out.println(time);
		
		return result;
	}
	
	private static double d(Point p1, Point p2){
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}

	public Trajectory getT1() {
		return t1;
	}

	public void setT1(Trajectory t1) {
		this.t1 = t1;
	}

	public Trajectory getT2() {
		return t2;
	}

	public void setT2(Trajectory t2) {
		this.t2 = t2;
	}

	public double[][] getDist_matrix() {
		return dist_matrix;
	}

	public void setDist_matrix(double[][] dist_matrix) {
		this.dist_matrix = dist_matrix;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}
