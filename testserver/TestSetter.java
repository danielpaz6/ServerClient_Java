package testserver;

import algorithms.BestFirstSearch;
import algorithms.MatrixProblem;
import algorithms.Position;
import server_side.FileCacheManager;
import server_side.MySerialServer;
import server_side.SearchableClientHandler;
import server_side.SearcherSolver;
import server_side.Server;

public class TestSetter {

	static Server s; 
	
	public static void runServer(int port) {
		// put the code here that runs your server
		s = new MySerialServer(port);
		
		try {
			SearchableClientHandler<String, Position> ch = new SearchableClientHandler<>(
					new SearcherSolver<MatrixProblem, String, Position>(new BestFirstSearch<Position>()),
					new FileCacheManager<MatrixProblem, String>("./maze.xml")
			);
			
			System.out.println("Server is running...!");
			s.start(ch, "end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stopServer() {
		s.stop();
	}
	

}
