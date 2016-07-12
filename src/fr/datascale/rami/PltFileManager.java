package fr.datascale.rami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PltFileManager {
	
	
	private int filecount = 0;
    private int dircount = 0;
    private List<String> pltFilesPaths = new ArrayList<String>();
    private boolean recursivePath = true;
    private List<Trajectory> trajectories = new ArrayList<Trajectory>();
	
	 public void listDirectory(String dir) {
	        File file = new File(dir);
	        File[] files = file.listFiles();
	        if (files != null) {
	            for (int i = 0; i < files.length; i++) {
	                if (files[i].isDirectory() == false) {
	                	pltFilesPaths.add(files[i].getAbsolutePath());
	                	//System.out.println(files[i].getAbsolutePath());
	                }
	                if (files[i].isDirectory() == true && recursivePath == true) {
	                    this.listDirectory(files[i].getAbsolutePath());
	                }
	            }
	        }
	    }    

	    
	    public void readPltFiles()
	    {
	    	
	    	for(int i = 0; i<pltFilesPaths.size(); i++)
	    	{
	    		String csvFile = pltFilesPaths.get(i);
	    		BufferedReader br = null;
		    	String line = "";
		    	String cvsSplitBy = ",";


		    		try {
						br = new BufferedReader(new FileReader(csvFile));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		try {
		    			Trajectory t = new Trajectory();
						Point p = new Point(0.0,0.0);
						while ((line = br.readLine()) != null) {

						        // use comma as separator
							
							String[] trajectoryLine = line.split(cvsSplitBy);
							
							if(trajectoryLine.length == 7)
							{
								p = new Point(0.0,0.0);
								p.setX(Double.parseDouble(trajectoryLine[0]));
								p.setY(Double.parseDouble(trajectoryLine[1]));
								t.getList().add(p);
							}
						}
						
						trajectories.add(t);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		
	    	}
	    	
	    	
	    	

	    }
	    
	    
	    
	    public int getFilecount() {
			return filecount;
		}


		public void setFilecount(int filecount) {
			this.filecount = filecount;
		}


		public int getDircount() {
			return dircount;
		}


		public void setDircount(int dircount) {
			this.dircount = dircount;
		}


		public List<String> getPltFilesPaths() {
			return pltFilesPaths;
		}


		public void setPltFilesPaths(List<String> pltFilesPaths) {
			this.pltFilesPaths = pltFilesPaths;
		}


		public boolean isRecursivePath() {
			return recursivePath;
		}


		public void setRecursivePath(boolean recursivePath) {
			this.recursivePath = recursivePath;
		}


		public List<Trajectory> getTrajectories() {
			return trajectories;
		}


		public void setTrajectories(List<Trajectory> trajectories) {
			this.trajectories = trajectories;
		}


		public static void main(String[] args) {
	    	
	    	PltFileManager p = new PltFileManager();
	    	p.listDirectory("C:\\Users\\rami\\Desktop\\KMeans\\Data");
	    	p.readPltFiles();
	    	
	    }
	    
}
