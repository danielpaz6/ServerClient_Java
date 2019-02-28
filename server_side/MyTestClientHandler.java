package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;


public class MyTestClientHandler<Problem, Solution> implements ClientHandler<Problem, Solution> {//, Solver<Problem, Solution> {
	private Solver solver;
	private CacheManager cm;
	
	public MyTestClientHandler(Solver solver, CacheManager cm) {
		this.solver = solver;
		this.cm = cm;
	}

	@Override
	public void handleClient(InputStream in, OutputStream out, String exitStr) throws Exception {
		// Read from the user
		BufferedReader userInput = new BufferedReader(
				new InputStreamReader(in));
		
		// Write to the user
		PrintWriter userOutput = new PrintWriter(out);
		
		// We'll read each line from the client:
		String line;
		while(!(line=userInput.readLine()).equals(exitStr))
		{
			// Checks if the problem is already solved
			if(cm.isSolutionCached(line) == true)
			{
				userOutput.println(cm.getSolution(line));
			}
			else
			{
				// Getting the solution
				Solution s = (Solution) solver.solve(line);
				
				// Saving it
				cm.saveSolution(line, s);
				
				// Printing it back to the client
				userOutput.println(s);
			}
			
			userOutput.flush();
		}
	}

	/*@Override
	public Solution solve(Problem p) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
