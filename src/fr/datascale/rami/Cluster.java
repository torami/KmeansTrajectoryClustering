package fr.datascale.rami;
 
import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
	
	public List<Trajectory> trajectories;
	public Trajectory centroid;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.trajectories = new ArrayList();
		this.centroid = null;
	}

	
	public void addTrajectory(Trajectory t) {
		trajectories.add(t);
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		trajectories.clear();
	}
	
	public void plotCluster() {
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Centroid: " + centroid + "]");
		System.out.println("[Trajectoires: "+trajectories.size()+" \n");
		for(Trajectory t : trajectories) {
			System.out.println(t);
		}
		System.out.println("]");
	}


	public List<Trajectory> getTrajectories() {
		return trajectories;
	}


	public void setTrajectories(List<Trajectory> trajectories) {
		this.trajectories = trajectories;
	}


	public Trajectory getCentroid() {
		return centroid;
	}


	public void setCentroid(Trajectory centroid) {
		this.centroid = centroid;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
 
}

