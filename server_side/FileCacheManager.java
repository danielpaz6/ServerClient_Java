package server_side;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution> {
	private HashMap<Problem, Solution> map;
	private String fileName;
	
	public HashMap<Problem, Solution> getMap() {
		return map;
	}

	public void setMap(HashMap<Problem, Solution> map) {
		this.map = map;
	}

	public FileCacheManager(String fileName) throws Exception {
		if(!fileName.endsWith(".xml"))
			throw new Exception("Error: You must put XML File!");
		
		this.fileName = fileName;
		this.map = new HashMap<>();
		
		XMLDecoder decoder = null;
		try {
			BufferedInputStream newFile = new BufferedInputStream(new FileInputStream(fileName));
			decoder = new XMLDecoder(newFile);
			
			// Checks if the file is empty, if it does we won't try to extract information from it
			if(newFile.available() > 0)
				this.map = (HashMap)decoder.readObject();
			
			decoder.close();
		}
		catch (FileNotFoundException e) {
			//throw new Exception("ERROR: File " + fileName + " not found");
		}
	}

	@Override
	public boolean isSolutionCached(Problem p) {
		return (map.get(p) != null);
	}

	@Override
	public Solution getSolution(Problem p) {
		return map.get(p);
	}

	@Override
	public void saveSolution(Problem p, Solution s) throws Exception {
		if(isSolutionCached(p))
			return;
		
		map.put(p, s);
		
		XMLEncoder encoder = null;
		BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(fileName));
		encoder = new XMLEncoder(bs);
		encoder.writeObject(map); // O(n), we can do it in O(1)
		encoder.close();
		
	}

}
