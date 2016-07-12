package fr.datascale.rami;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.datascale.aloulen.Point;
 
public class KMeans {
 
	//Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;    
    
    
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;
    
    private List<Trajectory> trajectories;
    private List<Cluster> clusters;

    
    
   
    
    
    
    public KMeans() {
    	this.trajectories = new ArrayList();
    	this.clusters = new ArrayList();    	
    }
    
    public static void main(String[] args) {
    	
    	KMeans kmeans = new KMeans();
    	kmeans.init();
    	kmeans.calculate();
    }
    
    //Initializes the process
    public void init() {


    	//Get trajectories
    	PltFileManager p = new PltFileManager();
    	p.listDirectory("C:\\Users\\rami\\Desktop\\KMeans\\Data\\181");
    	p.readPltFiles();
    	trajectories = p.getTrajectories();
    	System.out.println(trajectories.size());
    	Random rand = new Random();
    	int range = (trajectories.size()-1) + 1;
    	int randomNum =  rand.nextInt(range) + 0;
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i=0; i <NUM_CLUSTERS; i++)
    	{
    		Cluster cluster = new Cluster(i);
    		Trajectory centroid = trajectories.get(randomNum);
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);
    		randomNum =  rand.nextInt(range) + 0;
    	}
    	
    	System.out.println(clusters.size());
    	
    	//Print Initial state
    	plotClusters();
    	System.out.println(clusters.size());
    }
 
	private void plotClusters() {
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster c = clusters.get(i);
    		c.plotCluster();
    	}
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	System.out.println("testttt");
        	List<Trajectory> lastCentroids = getCentroids();
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	System.out.println("test2");
        	iteration++;
        	
        	List<Trajectory> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	Distance d = null;
        	
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		d = new Distance(lastCentroids.get(i), currentCentroids.get(i));
        		distance += d.calculate();
        	}
        	System.out.println(distance+"#################");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	plotClusters();
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
    }
    
    private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
    
    private List<Trajectory> getCentroids() {
    	List centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Trajectory aux = cluster.getCentroid();
    		Trajectory t = new Trajectory(aux.getList());
    		centroids.add(t);
    	}
    	return centroids;
    }
    
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        Distance distance;
        
        double distanceCalculated = 0.0; 
        
        
        for(Trajectory t : trajectories) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
            	distance = new Distance(t, c.getCentroid());
            	distanceCalculated = distance.calculate();
                if(distanceCalculated < min){
                    min = distanceCalculated;
                    cluster = i;
                }
            }
            t.setCluster_number(cluster);
            clusters.get(cluster).addTrajectory(t);
        }
    }
    
    
    private void calculateCentroids() {
    	System.out.println("test 3");
        for(Cluster cluster : clusters) {
        	Distance d;
            double aux = 0;
            ArrayList<Double> distances = new ArrayList<Double>();
            ArrayList<Double> sommeDistances = new ArrayList<Double>();
           
            	if(cluster.getTrajectories().size() == 0)
            		break;
            	if(cluster.getTrajectories().size() == 1)
            	{
            		System.out.println("test");
            		cluster.setCentroid(cluster.getTrajectories().get(0));
            		break;
            	}
            		
            	if(cluster.getTrajectories().size() == 2)
            	{
            		Random dd = new Random();
            		int x= dd.nextInt(1); 
            		cluster.setCentroid(cluster.getTrajectories().get(x));
            		return;
            	}
            	
            		
            	for(int i = 0 ; i< cluster.getTrajectories().size()-1; i++)
            	{
            		
            		for(int j = i+1 ; i< cluster.getTrajectories().size(); i++)
                	{
            			if(i != j)
            			{
            				System.out.println(j);
            				d = new Distance(cluster.getTrajectories().get(i), cluster.getTrajectories().get(j));
            				distances.add(d.calculate());
            				
            			}
            			
                	}
            		
            	}
            
            
            
            for(int i=0; i<distances.size(); i++)
            {
            	aux = 0;
            	for (int j = 0; j<distances.size(); j++)
            	{
            		if(i!=j)
            		{
            			aux += distances.get(j);
            		}
            	}
            	sommeDistances.add(aux);
            }
            System.out.println(sommeDistances.size());
            
            double sommeMin = sommeDistances.get(0);
            int indiceDistanceMin = 0;
            for(int k = 1; k<sommeDistances.size(); k++)
            {
            	if(sommeDistances.get(k) < sommeMin)
            	{
            		sommeMin = sommeDistances.get(k);
            		indiceDistanceMin = k;
            	}
            }
            cluster.setCentroid(cluster.getTrajectories().get(indiceDistanceMin));
        }
    }
}
